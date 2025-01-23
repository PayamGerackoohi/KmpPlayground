package com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.ui.module.robot.SamplePageRobot.PreviewScope

interface TimePickerRobot {
    fun verify12HourMode(): Any
    fun PreviewScope.verify24HourMode(scrollTo: Boolean = false): Any
    fun daySection(block: DaySectionScope.() -> Unit = {}): Any
    fun switchToMinutesMode(): Any
    fun verifyMinutesMode(): Any
    fun select(mode: Mode, hour: String? = null, minute: String? = null): Any

    @Suppress("EnumEntryName")
    enum class Mode { _12Hours, _24Hours }

    interface DaySectionScope {
        fun am(): Any
        fun pm(): Any
    }
}

fun timePickerRobot(
    rule: ComposeContentTestRule,
    block: TimePickerRobot.() -> Unit,
): TimePickerRobot = TimePickerRobotImpl(rule).apply(block)
