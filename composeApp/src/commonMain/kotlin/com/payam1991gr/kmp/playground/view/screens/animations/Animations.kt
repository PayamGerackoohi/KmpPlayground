package com.payam1991gr.kmp.playground.view.screens.animations

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.view.module.SampleList
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.screens.animations.AnimationsScreen.State
import com.payam1991gr.kmp.playground.view.screens.animations.AnimationsScreen.State.Event
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.stringResource

class Animations : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) {
        SamplePage(
            showCode = false,
            titleRes = Res.string.home_animations,
            actions = persistentListOf(SamplePage.Action.Back),
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
