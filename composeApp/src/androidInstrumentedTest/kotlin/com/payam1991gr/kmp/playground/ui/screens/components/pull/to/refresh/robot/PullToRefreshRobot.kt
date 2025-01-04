package com.payam1991gr.kmp.playground.ui.screens.components.pull.to.refresh.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface PullToRefreshRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class PullToRefreshRobotImpl(private val rule: ComposeContentTestRule) : PullToRefreshRobot {
    override fun previewContent() = rule
        .onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule
        .onNodeWithText("Code")
        .assertIsDisplayed()
}

fun pullToRefreshRobot(
    rule: ComposeContentTestRule,
    block: PullToRefreshRobot.() -> Unit,
): PullToRefreshRobot = PullToRefreshRobotImpl(rule).apply(block)
