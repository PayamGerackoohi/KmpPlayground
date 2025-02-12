package com.payam1991gr.kmp.playground.presenter.screens.components.pull.to.refresh.robot

import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefreshScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import kotlinx.collections.immutable.toPersistentList

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
