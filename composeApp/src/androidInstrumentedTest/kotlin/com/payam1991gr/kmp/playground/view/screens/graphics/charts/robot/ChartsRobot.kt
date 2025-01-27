package com.payam1991gr.kmp.playground.view.screens.graphics.charts.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface ChartsRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class ChartsRobotImpl(private val rule: ComposeContentTestRule) : ChartsRobot {
    override fun previewContent() = rule
        .onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule
        .onNodeWithText("Code")
        .assertIsDisplayed()
}

fun chartsRobot(
    rule: ComposeContentTestRule,
    block: ChartsRobot.() -> Unit,
): ChartsRobot = ChartsRobotImpl(rule).apply(block)
