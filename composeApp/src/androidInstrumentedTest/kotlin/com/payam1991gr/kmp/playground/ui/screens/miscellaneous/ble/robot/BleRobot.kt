package com.payam1991gr.kmp.playground.ui.screens.miscellaneous.ble.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface BleRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class BleRobotImpl(private val rule: ComposeContentTestRule) : BleRobot {
    override fun previewContent() = rule
        .onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule
        .onNodeWithText("Code")
        .assertIsDisplayed()
}

fun bleRobot(
    rule: ComposeContentTestRule,
    block: BleRobot.() -> Unit,
): BleRobot = BleRobotImpl(rule).apply(block)
