package com.payam1991gr.kmp.playground.presenter.screens.io.api.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.io.api.ApiPresenter
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.io.api.ApiScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface ApiRobot {
    suspend fun onState(
        showCode: Boolean? = null,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class ApiRobotImpl(private val crt: Crt) : ApiRobot {
    override suspend fun onState(
        showCode: Boolean?,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit
    ) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        showCode?.let { assertThat(this.showCode).isEqualTo(it) }
        toolbarActions.let {
            if (it.isNotEmpty()) assertThat(this.toolbarActions).isEqualTo(it.toPersistentList())
        }
        block()
    }
}

suspend fun ApiPresenter.robot(block: suspend ApiRobot.() -> Unit) = test {
    ApiRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
