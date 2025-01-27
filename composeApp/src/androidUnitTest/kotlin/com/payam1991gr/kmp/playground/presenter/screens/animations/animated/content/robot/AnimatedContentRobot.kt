package com.payam1991gr.kmp.playground.presenter.screens.animations.animated.content.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.animations.animated.content.AnimatedContentPresenter
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContentScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface AnimatedContentRobot {
    suspend fun onState(
        showCode: Boolean,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class AnimatedContentRobotImpl(private val crt: Crt) : AnimatedContentRobot {
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

suspend fun AnimatedContentPresenter.robot(block: suspend AnimatedContentRobot.() -> Unit) = test {
    AnimatedContentRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
