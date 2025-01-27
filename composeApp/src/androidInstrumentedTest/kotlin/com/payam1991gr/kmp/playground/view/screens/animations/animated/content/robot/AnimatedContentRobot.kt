package com.payam1991gr.kmp.playground.view.screens.animations.animated.content.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface AnimatedContentRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class AnimatedContentRobotImpl(private val rule: ComposeContentTestRule) : AnimatedContentRobot {
    override fun previewContent() = rule.onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule.onNodeWithText("Code")
        .assertIsDisplayed()
}

fun animatedContentRobot(
    rule: ComposeContentTestRule,
    block: AnimatedContentRobot.() -> Unit,
): AnimatedContentRobot = AnimatedContentRobotImpl(rule).apply(block)
