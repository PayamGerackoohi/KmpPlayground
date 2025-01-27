package com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.ble.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.ble.BlePresenter
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.ble.BleScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface BleRobot {
    suspend fun onState(
        showCode: Boolean,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class BleRobotImpl(private val crt: Crt) : BleRobot {
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

suspend fun BlePresenter.robot(block: suspend BleRobot.() -> Unit) = test {
    BleRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}