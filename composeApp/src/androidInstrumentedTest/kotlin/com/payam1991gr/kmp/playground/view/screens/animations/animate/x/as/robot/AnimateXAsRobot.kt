package com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface AnimateXAsRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class AnimateXAsRobotImpl(private val rule: ComposeContentTestRule) : AnimateXAsRobot {
    override fun previewContent() = rule
        .onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule
        .onNodeWithText("Code")
        .assertIsDisplayed()
}

fun animateXAsRobot(
    rule: ComposeContentTestRule,
    block: AnimateXAsRobot.() -> Unit,
): AnimateXAsRobot = AnimateXAsRobotImpl(rule).apply(block)
