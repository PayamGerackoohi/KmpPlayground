package com.payam1991gr.kmp.playground.ui.screens.graphics

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.ui.module.SampleList
import com.payam1991gr.kmp.playground.ui.module.SamplePage
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.graphics.GraphicsScreen.State
import com.payam1991gr.kmp.playground.ui.screens.graphics.GraphicsScreen.State.Event
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.stringResource

class Graphics : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) {
        SamplePage(
            showCode = false,
            titleRes = Res.string.home_graphics,
            actions = persistentListOf(Action.Back),
            onClick = { state.event(Event.OnBackPressed) },
            preview = {
                SampleList(
                    samples = state.items,
                    nameOf = { stringResource(it.labelRes) },
                    onClick = { state.event(Event.OnClick(it)) },
                    modifier = Modifier.fillMaxSize(),
                )
            },
            code = {},
        )
    }
}
