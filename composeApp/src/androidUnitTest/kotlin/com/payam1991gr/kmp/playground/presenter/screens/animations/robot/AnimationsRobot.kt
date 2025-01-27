package com.payam1991gr.kmp.playground.presenter.screens.animations.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.animations.AnimationsPresenter
import com.payam1991gr.kmp.playground.view.screens.animations.AnimationsScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test

interface AnimationsRobot {
    suspend fun onState(block: suspend State.() -> Unit): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class AnimationsRobotImpl(private val crt: Crt) : AnimationsRobot {
    override suspend fun onState(block: suspend State.() -> Unit) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        block()
    }
}

suspend fun AnimationsPresenter.robot(block: suspend AnimationsRobot.() -> Unit) = test {
    AnimationsRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}