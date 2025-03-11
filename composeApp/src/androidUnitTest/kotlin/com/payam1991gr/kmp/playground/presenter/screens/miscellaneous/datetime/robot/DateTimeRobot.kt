package com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.datetime.robot

import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.datetime.DateTimePresenter
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.DateTimeScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

typealias Crt = CircuitReceiveTurbine<State>

interface DateTimeRobot {
    suspend fun onState(
        showCode: Boolean? = null,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

class DateTimeRobotImpl(private val crt: Crt) : DateTimeRobot {
    override suspend fun onState(
        showCode: Boolean?,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit
    ) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        showCode?.let { assertThat(this.showCode).isEqualTo(it) }
        if (toolbarActions.isNotEmpty())
            assertThat(this.toolbarActions).isEqualTo(toolbarActions.toPersistentList())
        block()
    }
}

suspend fun DateTimePresenter.robot(block: suspend DateTimeRobot.() -> Unit) = test {
    DateTimeRobotImpl(this).block()
//    cancelAndIgnoreRemainingEvents()
}
