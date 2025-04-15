package com.payam1991gr.kmp.playground.view.screens.miscellaneous

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.view.module.SampleList
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.MiscellaneousScreen.State
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.MiscellaneousScreen.State.Event
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.stringResource

class Miscellaneous : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) = SamplePage(
        showCode = false,
        titleRes = Res.string.home_miscellaneous,
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
