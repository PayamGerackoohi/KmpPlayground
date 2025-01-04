package com.payam1991gr.kmp.playground.ui.screens.components.dialog

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.data.model.sample.rememberSettingItem
import com.payam1991gr.kmp.playground.ui.LocalTestMode
import com.payam1991gr.kmp.playground.ui.module.SamplePage
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Description
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Header
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Settings
import com.payam1991gr.kmp.playground.ui.module.editor.CodePanel
import com.payam1991gr.kmp.playground.ui.module.editor.rememberCodeEditor
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogScreen.State
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogScreen.State.Event
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

class Dialog : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) {
        SamplePage(
            showCode = state.showCode,
            titleRes = Res.string.components_dialog,
            actions = state.toolbarActions,
            onClick = { state.event(Event.OnToolbarAction(it)) },
            preview = { Preview() },
            code = { Code() },
        )
    }

    @Composable
    fun Preview() {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item { Header("Basic Alert Dialog") }
            item { Description(stringResource(Res.string.alert_dialog)) }
            item { BasicAlertDialogSample() }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BasicAlertDialogSample() = Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(),
    ) {
        val animationDuration = if (LocalTestMode.current) 0 else 300
        var showDialog by remember { mutableStateOf(false) }
        var animateDialog by remember { mutableStateOf(false) }
        val animationSpec = tween<Float>(animationDuration)
        val alpha by animateFloatAsState(
            if (animateDialog) 1f else 0f,
            animationSpec = animationSpec,
        )
        val scope = rememberCoroutineScope()
        val isDismissible by rememberSettingItem(true) {
            if (it) Res.string.dismissible else Res.string.mandatory
        }
        val shouldAnimate by rememberSettingItem(true) {
            if (it) Res.string.animate else Res.string.instant
        }

        fun onClose(): Any = if (shouldAnimate.value) scope.launch {
            animateDialog = false
            delay(animationDuration.toLong())
            showDialog = false
        } else showDialog = false

        Settings(isDismissible, shouldAnimate)
        Button(
            onClick = {
                showDialog = true
                if (shouldAnimate.value) scope.launch {
                    delay(animationDuration.toLong())
                    animateDialog = true
                }
            },
            modifier = Modifier.testTag("BasicAlertDialog.Show")
        ) { Text("Show") }
        if (showDialog) BasicAlertDialog(
            onDismissRequest = { if (isDismissible.value) onClose() },
        ) {
            Surface(
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation,
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight().run {
                        if (shouldAnimate.value) alpha(alpha)
                        else this
                    }
                    .testTag("BasicAlertDialogSample")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "This area typically contains the supportive text " +
                                "which presents the details regarding the Dialog's purpose."
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    TextButton(
                        onClick = { onClose() },
                        modifier = Modifier.align(Alignment.End),
                    ) { Text("Confirm") }
                }
            }
        }
    }

    @Composable
    fun Code() = CodePanel(rememberCodeEditor {
        appendBasicAlertDialogSample()
        line()
    })
}
