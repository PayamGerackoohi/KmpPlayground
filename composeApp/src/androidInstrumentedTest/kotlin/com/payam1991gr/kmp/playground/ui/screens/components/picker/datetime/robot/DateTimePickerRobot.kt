package com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface DateTimePickerRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class DateTimePickerRobotImpl(private val rule: ComposeContentTestRule) : DateTimePickerRobot {
    override fun previewContent() = rule
        .onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule
        .onNodeWithText("Code")
        .assertIsDisplayed()
}

fun dateTimePickerRobot(
    rule: ComposeContentTestRule,
    block: DateTimePickerRobot.() -> Unit,
): DateTimePickerRobot = DateTimePickerRobotImpl(rule).apply(block)
