package com.payam1991gr.kmp.playground.ui.screens.miscellaneous.pdf.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.pdf.PdfPresenter
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.pdf.PdfScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface PdfRobot {
    suspend fun onState(
        showCode: Boolean,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class PdfRobotImpl(private val crt: Crt) : PdfRobot {
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

suspend fun PdfPresenter.robot(block: suspend PdfRobot.() -> Unit) = test {
    PdfRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}