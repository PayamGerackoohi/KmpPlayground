package com.payam1991gr.kmp.playground.presenter.screens.io.file.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.io.file.FilePresenter
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.io.file.FileScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface FileRobot {
    suspend fun onState(
        showCode: Boolean,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class FileRobotImpl(private val crt: Crt) : FileRobot {
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

suspend fun FilePresenter.robot(block: suspend FileRobot.() -> Unit) = test {
    FileRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
