package com.payam1991gr.kmp.playground.ui.screens.animations.crossfade.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.animations.crossfade.CrossfadePresenter
import com.payam1991gr.kmp.playground.ui.screens.animations.crossfade.CrossfadeScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface CrossfadeRobot {
    suspend fun onState(
        showCode: Boolean,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class CrossfadeRobotImpl(private val crt: Crt) : CrossfadeRobot {
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

suspend fun CrossfadePresenter.robot(block: suspend CrossfadeRobot.() -> Unit) = test {
    CrossfadeRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
