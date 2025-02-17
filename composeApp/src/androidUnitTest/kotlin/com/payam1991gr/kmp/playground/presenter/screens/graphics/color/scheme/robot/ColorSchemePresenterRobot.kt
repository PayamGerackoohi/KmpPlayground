package com.payam1991gr.kmp.playground.presenter.screens.graphics.color.scheme.robot

import com.payam1991gr.kmp.playground.presenter.screens.graphics.color.scheme.ColorSchemePresenter
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.ColorSchemeScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test

interface ColorSchemePresenterRobot{
    suspend fun onState(
        showCode: Boolean,
        vararg actions: Action,
        block: suspend State.() -> Unit = {},
    ): State
}

typealias Crt = CircuitReceiveTurbine<State>

suspend fun ColorSchemePresenter.robot(block: suspend ColorSchemePresenterRobot.() -> Unit) = test {
    ColorSchemePresenterRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
