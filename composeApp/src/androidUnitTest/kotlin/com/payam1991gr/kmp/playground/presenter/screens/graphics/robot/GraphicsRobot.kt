package com.payam1991gr.kmp.playground.presenter.screens.graphics.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.graphics.GraphicsPresenter
import com.slack.circuit.test.CircuitReceiveTurbine
import com.payam1991gr.kmp.playground.view.screens.graphics.GraphicsScreen.State
import com.slack.circuit.test.test

interface GraphicsRobot {
    suspend fun onState(block: suspend State.() -> Unit): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class GraphicsRobotImpl(private val crt: Crt) : GraphicsRobot {
    override suspend fun onState(block: suspend State.() -> Unit) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        block()
    }
}

suspend fun GraphicsPresenter.robot(block: suspend GraphicsRobot.() -> Unit) = test {
    GraphicsRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
