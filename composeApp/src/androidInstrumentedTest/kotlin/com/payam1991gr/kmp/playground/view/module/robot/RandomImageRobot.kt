package com.payam1991gr.kmp.playground.view.module.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithContentDescription

interface RandomImageRobot {
    fun image(contentDescription: String): Any
}

class RandomImageRobotImpl(private val rule: ComposeContentTestRule) : RandomImageRobot {
    override fun image(contentDescription: String) = rule
        .onNodeWithContentDescription(contentDescription)
        .assertIsDisplayed()
}

fun randomImageRobot(
    rule: ComposeContentTestRule,
    block: RandomImageRobot.() -> Unit,
): RandomImageRobot = RandomImageRobotImpl(rule).apply(block)
