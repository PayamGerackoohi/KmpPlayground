package com.payam1991gr.kmp.playground.view.screens.io.file

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.screens.io.file.FileScreen.State
import com.payam1991gr.kmp.playground.view.screens.io.file.FileScreen.State.Event
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*

class File : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) {
        SamplePage(
            showCode = state.showCode,
            titleRes = Res.string.io_file,
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
