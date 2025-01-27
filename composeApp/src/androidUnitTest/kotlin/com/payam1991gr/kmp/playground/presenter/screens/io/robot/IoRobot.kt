package com.payam1991gr.kmp.playground.presenter.screens.io.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.io.IoPresenter
import com.payam1991gr.kmp.playground.view.screens.io.IoScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test

interface IoRobot {
    suspend fun onState(block: suspend State.() -> Unit): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class IoRobotImpl(private val crt: Crt) : IoRobot {
    override suspend fun onState(block: suspend State.() -> Unit) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        block()
    }
}

suspend fun IoPresenter.robot(block: suspend IoRobot.() -> Unit) = test {
    IoRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
