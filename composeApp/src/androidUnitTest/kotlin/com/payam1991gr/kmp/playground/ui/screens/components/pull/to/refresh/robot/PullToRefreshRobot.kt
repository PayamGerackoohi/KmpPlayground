package com.payam1991gr.kmp.playground.ui.screens.components.pull.to.refresh.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.components.pull.to.refresh.PullToRefreshPresenter
import com.payam1991gr.kmp.playground.ui.screens.components.pull.to.refresh.PullToRefreshScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface PullToRefreshRobot {
    suspend fun onState(
        showCode: Boolean,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class PullToRefreshRobotImpl(private val crt: Crt) : PullToRefreshRobot {
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

suspend fun PullToRefreshPresenter.robot(block: suspend PullToRefreshRobot.() -> Unit) = test {
    PullToRefreshRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
