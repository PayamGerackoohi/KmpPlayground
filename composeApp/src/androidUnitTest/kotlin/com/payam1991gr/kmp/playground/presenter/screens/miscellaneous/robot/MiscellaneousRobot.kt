package com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.MiscellaneousPresenter
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.MiscellaneousScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test

interface MiscellaneousRobot {
    suspend fun onState(block: suspend State.() -> Unit): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class MiscellaneousRobotImpl(private val crt: Crt) : MiscellaneousRobot {
    override suspend fun onState(block: suspend State.() -> Unit) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        block()
    }
}

suspend fun MiscellaneousPresenter.robot(block: suspend MiscellaneousRobot.() -> Unit) = test {
    MiscellaneousRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
