package com.payam1991gr.kmp.playground.presenter.screens.io.datastore.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.io.datastore.DatastorePresenter
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.io.datastore.DatastoreScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface DatastoreRobot {
    suspend fun onState(
        showCode: Boolean? = null,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class DatastoreRobotImpl(private val crt: Crt) : DatastoreRobot {
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

suspend fun DatastorePresenter.robot(block: suspend DatastoreRobot.() -> Unit) = test {
    DatastoreRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
