package com.payam1991gr.kmp.playground.ui.screens.components.carousel.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.components.carousel.CarouselPresenter
import com.payam1991gr.kmp.playground.ui.screens.components.carousel.CarouselScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface CarouselRobot {
    suspend fun onState(
        showCode: Boolean,
        vararg actions: Action,
        block: suspend State.() -> Unit = {},
    ): State
}

typealias Crt = CircuitReceiveTurbine<State>

class CarouselRobotImpl(private val crt: Crt) : CarouselRobot {
    override suspend fun onState(
        showCode: Boolean,
        vararg actions: Action,
        block: suspend State.() -> Unit,
    ) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        assertThat(showCode).isEqualTo(this.showCode)
        assertThat(actions.toPersistentList()).isEqualTo(toolbarActions)
        block()
    }
}

suspend fun CarouselPresenter.robot(block: suspend CarouselRobot.() -> Unit) = test {
    CarouselRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
