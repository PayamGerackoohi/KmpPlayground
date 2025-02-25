package com.payam1991gr.kmp.playground.presenter.screens.graphics.icons.robot

import com.payam1991gr.kmp.playground.data.model.IconData
import com.payam1991gr.kmp.playground.presenter.screens.graphics.icons.IconsPresenter
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State
import com.slack.circuit.test.test

interface IconsRobot {
    suspend fun onState(
        showCode: Boolean,
        vararg actions: Action,
        block: suspend State.() -> Unit = {},
    ): State

    fun State.verifyBehavior(icon: IconData)
}

suspend fun IconsPresenter.robot(block: suspend IconsRobot.() -> Unit) = test {
    IconsRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
