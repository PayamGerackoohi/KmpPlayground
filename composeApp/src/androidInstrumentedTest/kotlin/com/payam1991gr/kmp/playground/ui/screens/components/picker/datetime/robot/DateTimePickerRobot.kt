package com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.ui.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.ui.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.ui.test.util.BOX
import com.payam1991gr.kmp.playground.ui.test.util.SniBlock

interface DateTimePickerRobot {
    fun PreviewScope.datePickerSection(block: DatePickerScope.() -> Unit = {}): Any
    fun PreviewScope.dateRangePickerSection(block: DateRangePickerScope.() -> Unit = {}): Any
    fun PreviewScope.timePickerSection(block: TimePickerScope.() -> Unit = {}): Any
    fun PreviewScope.timeInputSection(block: TimeInputScope.() -> Unit = {}): Any
    fun CodeScope.resources(): Any
    fun CodeScope.datePickerSample(): Any
    fun CodeScope.dateRangePickerSample(): Any
    fun CodeScope.timePickerSample(): Any
    fun CodeScope.timeInputSample(): Any
    fun CodeScope.box(): Any

    interface DatePickerScope {
        fun PreviewScope.timestamp(value: String = BOX, scrollTo: Boolean = false): Any
        fun date(text: String = BOX): Any
    }

    interface DateRangePickerScope {
        fun PreviewScope.timestamp(
            from: String = BOX,
            to: String = BOX,
            scrollTo: Boolean = false,
        ): Any

        fun date(from: String = BOX, to: String = BOX): Any
        fun PreviewScope.on(date: String, scrollTo: Boolean = false, block: SniBlock = {}): Any
        fun selectedDate(start: String = "None", end: String = "None"): Any
        fun switchModeButton(): Any
        fun assertThatSwitchModeButtonIsNotShown(): Any
        fun settings(block: SettingsScope.() -> Unit): Any

        interface SettingsScope {
            fun on(label: String, block: SniBlock = {}): Any
        }
    }

    interface TimePickerScope {
        fun PreviewScope.time(value: String, scrollTo: Boolean = false): Any
        fun settings(block: SettingsScope.() -> Unit): Any

        interface SettingsScope {
            fun on(label: String, block: SniBlock = {}): Any
        }
    }

    interface TimeInputScope {
        fun PreviewScope.time(text: String, scrollTo: Boolean = false): Any
        fun settings(block: SettingsScope.() -> Unit): Any
        fun input(block: InputScope.() -> Unit = {}): Any

        interface SettingsScope {
            fun on(label: String, block: SniBlock = {}): Any
        }

        interface InputScope {
            fun selectMinutes(): Any
            fun insert(hour: String? = null, minute: String? = null): Any
            fun daySection(block: DaySectionScope.() -> Unit = {}): Any
            interface DaySectionScope {
                fun am(): Any
                fun pm(): Any
            }
        }
    }
}

fun dateTimePickerRobot(
    rule: ComposeContentTestRule,
    block: DateTimePickerRobot.() -> Unit,
): DateTimePickerRobot = DateTimePickerRobotImpl(rule).apply(block)
