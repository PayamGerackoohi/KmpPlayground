package com.payam1991gr.kmp.playground.view.screens.miscellaneous.ble

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.ble.BleScreen.State
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.ble.BleScreen.State.Event
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*

class Ble : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) {
        SamplePage(
            showCode = state.showCode,
            titleRes = Res.string.miscellaneous_ble,
            actions = state.toolbarActions,
            onClick = { state.event(Event.OnToolbarAction(it)) },
            preview = { Preview() },
            code = { Code() },
        )
    }

    @Composable
    fun Preview() {
        Text("Preview")
    }

    @Composable
    fun Code() {
        Text("Code")
    }
}
