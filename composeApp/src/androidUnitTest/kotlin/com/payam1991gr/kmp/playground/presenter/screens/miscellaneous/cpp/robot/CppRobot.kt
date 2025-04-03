package com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.cpp.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.cpp.CppPresenter
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface CppRobot {
    suspend fun onState(
        showCode: Boolean? = null,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class CppRobotImpl(private val crt: Crt) : CppRobot {
    override suspend fun onState(
        showCode: Boolean?,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit
    ) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        showCode?.let { assertThat(this.showCode).isEqualTo(it) }
        if (toolbarActions.isNotEmpty())
            assertThat(this.toolbarActions).isEqualTo(toolbarActions.toPersistentList())
        block()
    }
}

suspend fun CppPresenter.robot(block: suspend CppRobot.() -> Unit) = test {
    CppRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
