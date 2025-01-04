package com.payam1991gr.kmp.playground.ui.screens.graphics.opengl.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface OpenGlRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class OpenGlRobotImpl(private val rule: ComposeContentTestRule) : OpenGlRobot {
    override fun previewContent() = rule
        .onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule
        .onNodeWithText("Code")
        .assertIsDisplayed()
}

fun openGlRobot(
    rule: ComposeContentTestRule,
    block: OpenGlRobot.() -> Unit,
): OpenGlRobot = OpenGlRobotImpl(rule).apply(block)
