package com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.ui.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.ui.test.util.SniBlock

interface DatePickerRobot {
    fun verifySwitchModeButton(): Any
    fun assertThatSwitchModeButtonIsNotShown(): Any
    fun selectedDate(text: String = "Selected date"): Any
    fun PreviewScope.on(date: String, scrollTo: Boolean = false, block: SniBlock = {}): Any
}

fun datePickerRobot(
    rule: ComposeContentTestRule,
    block: DatePickerRobot.() -> Unit,
): DatePickerRobot = DatePickerRobotImpl(rule).apply(block)
