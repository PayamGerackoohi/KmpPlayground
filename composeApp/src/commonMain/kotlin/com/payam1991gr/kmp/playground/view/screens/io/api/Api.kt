package com.payam1991gr.kmp.playground.view.screens.io.api

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastRoundToInt
import com.payam1991gr.kmp.playground.data.model.RemoteData
import com.payam1991gr.kmp.playground.data.model.sample.rememberSetting
import com.payam1991gr.kmp.playground.data.string.merge
import com.payam1991gr.kmp.playground.presenter.screens.io.api.Numbers
import com.payam1991gr.kmp.playground.presenter.screens.io.api.sample.*
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.SamplePage.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Header
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Description
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Settings
import com.payam1991gr.kmp.playground.view.module.editor.codes
import com.payam1991gr.kmp.playground.view.screens.io.api.ApiScreen.State
import com.payam1991gr.kmp.playground.view.screens.io.api.ApiScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.io.api.sample.*
import com.slack.circuit.runtime.ui.Ui
import io.ktor.http.HttpMethod
import io.ktor.http.Url
import io.ktor.http.buildUrl
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

typealias FRange = ClosedFloatingPointRange<Float>

class Api : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) = SamplePage(
        showCode = state.showCode,
        titleRes = Res.string.io_api,
        actions = state.toolbarActions,
        onClick = { state.event(Event.OnToolbarAction(it)) },
        preview = { state.Preview() },
        code = { Code() },
    )

    @Composable
    private fun State.Preview() = preview(
        { Description(stringResource(Res.string.io_api_description)) },
        { Header(stringResource(Res.string.io_api_configurations)) },
        { Description(stringResource(Res.string.io_api_configurations_description)) },
        { FakeModeSetting() },
        { HostInput(host, event) },
        { Header(stringResource(Res.string.io_api_requests)) },
        { home.GetHome(host, event) },
        { writtenNumbers.GetWrittenNumbers(host, event) },
    )

    @Composable
    private fun State.FakeModeSetting() = rememberSetting(shouldUseRealApi) {
        if (it) Res.string.io_api_real_server
        else Res.string.io_api_fake_server
    }.apply {
        LaunchedEffect(value) { event(Event.OnServerModeChanged(value)) }
        Settings(this)
    }

    @Composable
    private fun HostInput(host: String, event: (Event) -> Unit) = TextField(
        value = host,
        onValueChange = { event(Event.OnHostChanged(it)) },
        label = { Text(stringResource(Res.string.io_api_base_url)) },
    )

    @Composable
    private fun RequestCard(
        url: String,
        status: Int?,
        method: HttpMethod,
        call: () -> Unit,
        tag: String = "",
        showContent: Boolean = true,
        arguments: @Composable ColumnScope.() -> Unit = {},
        content: @Composable ColumnScope.() -> Unit,
    ) = Card(
        shape = CutCornerShape(topEnd = 24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier.testTag("RequestCard" merge tag)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier
                .background(colorScheme.inversePrimary)
                .padding(12.dp)
        ) {
            Request(url, status, method, call)
            arguments()
        }
        if (showContent) Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            content = content,
            modifier = Modifier
                .padding(12.dp)
                .testTag("Content")
        )
    }

    @Composable
    private fun RemoteData<String>.GetHome(host: String, event: (Event) -> Unit) = RequestCard(
        tag = "GetHome",
        url = remember(host) { buildUrl { this.host = host }.string },
        status = status(),
        method = HttpMethod.Get,
        call = { event(Event.CallHomeApi) },
        showContent = this != RemoteData.Init,
    ) {
        when (this@GetHome) {
            RemoteData.Init -> {}
            RemoteData.Connecting -> ConnectingToServer()
            is RemoteData.Data -> Text(data)
            is RemoteData.Error -> Text(message)
        }
    }

    @Composable
    private fun RemoteData<Numbers>.GetWrittenNumbers(host: String, event: (Event) -> Unit) {
        var range by remember { mutableStateOf(0f..3f) }
        val (from, to) = remember(range) {
            range.start.fastRoundToInt() to range.endInclusive.fastRoundToInt()
        }
        RequestCard(
            tag = "GetWrittenNumbers",
            url = remember(host, range) {
                buildUrl {
                    this.host = host
                    pathSegments = listOf("written-numbers")
                    parameters.apply {
                        append("from", from.toString())
                        append("to", to.toString())
                    }
                }.string
            },
            status = status(),
            method = HttpMethod.Get,
            call = { event(Event.CallWrittenNumbersApi(from, to)) },
            arguments = { RangeSlider("$from..$to", range) { range = it } },
            showContent = this != RemoteData.Init,
        ) {
            when (this@GetWrittenNumbers) {
                RemoteData.Init -> {}
                RemoteData.Connecting -> ConnectingToServer()
                is RemoteData.Error -> Text(message)
                is RemoteData.Data -> data.forEach { (value, string) ->
                    Text("($value, $string)")
                }
            }
        }
    }

    @Composable
    private fun RangeSlider(state: String, range: FRange, onRangeChanged: (FRange) -> Unit) = Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 12.dp)
    ) {
        val stateDescription = stringResource(Res.string.io_api_range_slider_template, state)
        Text(stringResource(Res.string.io_api_range))
        RangeSlider(
            value = range,
            onValueChange = onRangeChanged,
            valueRange = -1f..101f,
            steps = 101,
            colors = SliderDefaults.colors(
                thumbColor = colorScheme.tertiary,
                activeTrackColor = colorScheme.secondary,
                inactiveTrackColor = colorScheme.outlineVariant,
            ),
            modifier = Modifier.semantics { this.stateDescription = stateDescription }
        )
    }

    @Composable
    private fun Request(url: String, status: Int?, method: HttpMethod, call: () -> Unit) = Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = call,
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        ) { Text(method.value) }
        Text(url, Modifier.weight(1f).padding(top = 12.dp))
        status?.let { Status(it) }
    }

    @Composable
    private fun Status(status: Int) = colorScheme.run {
        if (status.isOk) secondaryContainer to onSecondaryContainer
        else tertiaryContainer to onTertiaryContainer
    }.let { (backgroundColor, color) ->
        val contentDescription = stringResource(Res.string.status)
        Text(
            text = status.toString(),
            color = color,
            modifier = Modifier
                .background(backgroundColor, CircleShape)
                .padding(horizontal = 24.dp, vertical = 12.dp)
                .semantics { this.contentDescription = contentDescription }
        )
    }

    private inline val Int.isOk get() = this in 200..<300

    @Composable
    private fun ConnectingToServer() = Text(
        stringResource(Res.string.io_api_connecting_to_the_server)
    )

    private val Url.string get() = toString().replaceFirst("http://", "")

    @Composable
    fun Code() = codes(
        Res.string.shared to {
            appendNumbers()
            appendRemoteData()
            appendWrittenNumber()
            appendApi()
            appendApiImpl()
        },
        Res.string.state to { appendState() },
        Res.string.presenter to { appendPresenter() },
        Res.string.ui to { appendUI() },
    )
}
