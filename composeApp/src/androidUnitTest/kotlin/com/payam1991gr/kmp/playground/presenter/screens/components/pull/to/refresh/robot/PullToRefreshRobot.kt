package com.payam1991gr.kmp.playground.presenter.screens.components.pull.to.refresh.robot

import com.payam1991gr.kmp.playground.presenter.screens.components.pull.to.refresh.PullToRefreshPresenter
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefreshScreen.State
import com.slack.circuit.test.test

interface PullToRefreshRobot {
    suspend fun onState(
        showCode: Boolean,
        vararg toolbarActions: Action,
        block: suspend State.() -> Unit = {},
    ): Any
}

suspend fun PullToRefreshPresenter.robot(
    block: suspend PullToRefreshRobot.() -> Unit,
) = test {
    PullToRefreshRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
