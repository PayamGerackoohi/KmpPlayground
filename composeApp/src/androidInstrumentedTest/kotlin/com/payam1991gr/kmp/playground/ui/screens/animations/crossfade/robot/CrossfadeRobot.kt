package com.payam1991gr.kmp.playground.ui.screens.animations.crossfade.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface CrossfadeRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class CrossfadeRobotImpl(private val rule: ComposeContentTestRule) : CrossfadeRobot {
    override fun previewContent() = rule
        .onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule
        .onNodeWithText("Code")
        .assertIsDisplayed()
}

fun crossfadeRobot(
    rule: ComposeContentTestRule,
    block: CrossfadeRobot.() -> Unit,
): CrossfadeRobot = CrossfadeRobotImpl(rule).apply(block)
