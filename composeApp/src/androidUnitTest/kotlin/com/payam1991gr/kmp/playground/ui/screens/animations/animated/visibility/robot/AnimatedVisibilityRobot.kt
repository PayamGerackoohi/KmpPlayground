package com.payam1991gr.kmp.playground.ui.screens.animations.animated.visibility.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.visibility.AnimatedVisibilityPresenter
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.visibility.AnimatedVisibilityScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface AnimatedVisibilityRobot {
    suspend fun onState(
        showCode: Boolean,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class AnimatedVisibilityRobotImpl(private val crt: Crt) : AnimatedVisibilityRobot {
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

suspend fun AnimatedVisibilityPresenter.robot(
    block: suspend AnimatedVisibilityRobot.() -> Unit,
) = test {
    AnimatedVisibilityRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
