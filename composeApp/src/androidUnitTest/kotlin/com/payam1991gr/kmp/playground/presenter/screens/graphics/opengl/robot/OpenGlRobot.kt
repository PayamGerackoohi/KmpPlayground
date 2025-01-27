package com.payam1991gr.kmp.playground.presenter.screens.graphics.opengl.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.graphics.opengl.OpenGlPresenter
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.graphics.opengl.OpenGlScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface OpenGlRobot {
    suspend fun onState(
        showCode: Boolean,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class OpenGlRobotImpl(private val crt: Crt) : OpenGlRobot {
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

suspend fun OpenGlPresenter.robot(block: suspend OpenGlRobot.() -> Unit) = test {
    OpenGlRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
