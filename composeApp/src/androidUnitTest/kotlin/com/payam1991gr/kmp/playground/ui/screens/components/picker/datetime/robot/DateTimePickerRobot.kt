package com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.DateTimePickerPresenter
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.DateTimePickerScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface DateTimePickerRobot {
    suspend fun onState(
        showCode: Boolean,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class DateTimePickerRobotImpl(private val crt: Crt) : DateTimePickerRobot {
    override suspend fun onState(
        showCode: Boolean,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit
    ) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        assertThat(this.showCode).isEqualTo(showCode)
        assertThat(this.toolbarActions).isEqualTo(toolbarActions.toPersistentList())
        block()
    }
}

suspend fun DateTimePickerPresenter.robot(block: suspend DateTimePickerRobot.() -> Unit) = test {
    DateTimePickerRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
