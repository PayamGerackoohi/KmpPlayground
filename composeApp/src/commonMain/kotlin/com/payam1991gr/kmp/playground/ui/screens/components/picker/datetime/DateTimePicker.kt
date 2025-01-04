package com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.ui.module.SamplePage
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.DateTimePickerScreen.State
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.DateTimePickerScreen.State.Event
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*

class DateTimePicker : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) {
        SamplePage(
            showCode = state.showCode,
            titleRes = Res.string.components_date_time_picker,
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
