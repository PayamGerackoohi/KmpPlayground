package com.payam1991gr.kmp.playground.presenter.screens.home.robot

import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.presenter.screens.home.HomePresenter
import com.payam1991gr.kmp.playground.view.screens.home.HomeScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test

interface HomeRobot {
    suspend fun onState(block: suspend State.() -> Unit): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class HomeRobotImpl(private val crt: Crt) : HomeRobot {
    override suspend fun onState(block: suspend State.() -> Unit) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        block()
    }
}

suspend fun HomePresenter.robot(block: suspend HomeRobot.() -> Unit) = test {
    HomeRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
