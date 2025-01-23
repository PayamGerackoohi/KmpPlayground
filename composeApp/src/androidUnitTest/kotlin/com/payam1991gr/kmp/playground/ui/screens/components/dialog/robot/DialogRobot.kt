package com.payam1991gr.kmp.playground.ui.screens.components.dialog.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogPresenter
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface DialogRobot {
    suspend fun onState(
        vararg toolbarActions: Action = arrayOf(),
        showCode: Boolean? = null,
        datePicker: String? = null,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class DialogRobotImpl(private val crt: Crt) : DialogRobot {
    override suspend fun onState(
        vararg toolbarActions: Action,
        showCode: Boolean?,
        datePicker: String?,
        block: suspend State.() -> Unit
    ) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        showCode?.let { assertThat(this.showCode).isEqualTo(it) }
        if (toolbarActions.isNotEmpty())
            assertThat(this.toolbarActions).isEqualTo(toolbarActions.toPersistentList())
        datePicker?.let { assertThat(this.datePicker.toString()).isEqualTo(it) }
        block()
    }
}

suspend fun DialogPresenter.robot(block: suspend DialogRobot.() -> Unit) = test {
    DialogRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
