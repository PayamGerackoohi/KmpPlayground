package com.payam1991gr.kmp.playground.view.screens.graphics.icons

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.data.model.IconData
import com.payam1991gr.kmp.playground.data.model.LazyData
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Header
import com.payam1991gr.kmp.playground.view.module.editor.CodePanel
import com.payam1991gr.kmp.playground.view.module.editor.rememberCodeEditor
import com.payam1991gr.kmp.playground.view.nothing
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State.Effect
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State.Event
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

class Icons : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) = SamplePage(
        showCode = state.showCode,
        titleRes = Res.string.graphic_icons,
        actions = state.toolbarActions,
        onClick = { state.event(Event.OnToolbarAction(it)) },
        code = { Code() },
        preview = { Preview(state) }
    )

    @Composable
    fun Preview(state: State) = state.apply {
        val spacing = 2.dp
        val snackbarState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        var job: Job? = remember { null }
        fun showMessage(text: String) {
            job?.cancel()
            job = scope.launch {
                withTimeout(1000) {
                    snackbarState.showSnackbar(text)
                }
            }
        }
        state.ObserveEffects(::showMessage)
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarState, Modifier.testTag("Snackbar")) },
        ) {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(40.dp),
                horizontalArrangement = Arrangement.spacedBy(spacing),
                verticalArrangement = Arrangement.spacedBy(spacing),
                contentPadding = PaddingValues(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                header(Res.string.graphic_icons_core)
                icons(coreIcons, event)
                header(
                    Res.string.graphic_icons_extended,
                    Modifier.padding(top = 16.dp)
                )
                when (extendedIcons) {
                    LazyData.Loading -> loading()
                    is LazyData.Data -> icons(extendedIcons.data, event)
                }
            }
        }
    }

    private fun LazyGridScope.loading() = item(span = { GridItemSpan(maxLineSpan) }) {
        LinearProgressIndicator(
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.tertiary,
        )
    }

    @Composable
    private fun State.ObserveEffects(showMessage: (String) -> Unit) {
        when (val effect = effect.value) {
            null -> nothing()
            is Effect.ShowMessage -> showMessage(effect.text)
        }
        effect.value = null
    }

    private fun LazyGridScope.icons(
        icons: PersistentList<IconData>,
        event: (Event) -> Unit,
    ) = items(icons) {
        Icon(it) { event(Event.OnIconClicked(it)) }
    }

    private fun LazyGridScope.header(
        titleRes: StringResource,
        modifier: Modifier = Modifier,
    ) = item(span = { GridItemSpan(maxLineSpan) }) {
        Header(stringResource(titleRes), modifier)
    }

    @Composable
    fun Icon(data: IconData, onClick: () -> Unit) = Icon(
        imageVector = data.icon,
        contentDescription = data.title,
        tint = MaterialTheme.colorScheme.onSecondaryContainer,
        modifier = Modifier
            .fillMaxSize()
            .aspectRatio(1f)
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable { onClick() }
            .padding(4.dp)
    )

    @Composable
    fun Code() = CodePanel(rememberCodeEditor {
        appendPreview()
        appendState()
        appendLazyData()
        appendIconData()
        appendLazyGridScope_header()
        appendLazyGridScope_icons()
        appendIcon()
        appendLoading()
        appendState_ObserveEffects()
    })
}
