package com.payam1991gr.kmp.playground.ui.screens.components.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.ui.screens.components.ComponentsPresenter
import com.payam1991gr.kmp.playground.ui.screens.components.ComponentsScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test

interface ComponentsRobot {
    suspend fun onState(block: suspend State.() -> Unit): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class ComponentsRobotImpl(private val crt: Crt) : ComponentsRobot {
    override suspend fun onState(block: suspend State.() -> Unit) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        block()
    }
}

suspend fun ComponentsPresenter.robot(block: suspend ComponentsRobot.() -> Unit) = test {
    ComponentsRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
