package com.payam1991gr.kmp.playground.presenter.screens.animations.animate.x.`as`.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.animations.animate.x.`as`.AnimateXAsPresenter
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.AnimateXAsScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface AnimateXAsRobot {
    suspend fun onState(
        showCode: Boolean,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class AnimateXAsRobotImpl(private val crt: Crt) : AnimateXAsRobot {
    override suspend fun onState(
        showCode: Boolean,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit,
    ) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        assertThat(this.showCode).isEqualTo(showCode)
        assertThat(this.toolbarActions).isEqualTo(toolbarActions.toPersistentList())
        block()
    }
}

suspend fun AnimateXAsPresenter.robot(block: suspend AnimateXAsRobot.() -> Unit) = test {
    AnimateXAsRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
