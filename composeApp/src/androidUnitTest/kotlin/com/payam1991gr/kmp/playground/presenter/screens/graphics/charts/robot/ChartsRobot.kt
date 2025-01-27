package com.payam1991gr.kmp.playground.presenter.screens.graphics.charts.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.graphics.charts.ChartsPresenter
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.graphics.charts.ChartsScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface ChartsRobot {
    suspend fun onState(
        showCode: Boolean,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class ChartsRobotImpl(private val crt: Crt) : ChartsRobot {
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

suspend fun ChartsPresenter.robot(block: suspend ChartsRobot.() -> Unit) = test {
    ChartsRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
