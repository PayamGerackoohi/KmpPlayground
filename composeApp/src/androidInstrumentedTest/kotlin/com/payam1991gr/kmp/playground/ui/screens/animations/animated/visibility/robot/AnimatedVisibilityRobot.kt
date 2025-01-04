package com.payam1991gr.kmp.playground.ui.screens.animations.animated.visibility.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface AnimatedVisibilityRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class AnimatedVisibilityRobotImpl(
    private val rule: ComposeContentTestRule,
) : AnimatedVisibilityRobot {
    override fun previewContent() = rule
        .onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule
        .onNodeWithText("Code")
        .assertIsDisplayed()
}

fun animatedVisibilityRobot(
    rule: ComposeContentTestRule,
    block: AnimatedVisibilityRobot.() -> Unit,
): AnimatedVisibilityRobot = AnimatedVisibilityRobotImpl(rule).apply(block)
