package com.payam1991gr.kmp.playground.ui.screens.components.dialog.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.ui.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.ui.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.robot.TimePickerRobot
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.robot.TimePickerRobot.DaySectionScope
import com.payam1991gr.kmp.playground.ui.test.util.BOX
import com.payam1991gr.kmp.playground.ui.test.util.SniBlock

interface DialogRobot {
    fun PreviewScope.basicAlertDialogSection(block: BasicAlertDialogScope.() -> Unit): Any
    fun PreviewScope.datePickerDialogSection(block: DatePickerDialogSectionScope.() -> Unit): Any
    fun PreviewScope.timePickerDialogSection(block: TimePickerDialogSectionScope.() -> Unit): Any
    fun CodeScope.basicAlertDialogSampleCode(): Any
    fun CodeScope.datePickerDialogSampleCode(): Any
    fun CodeScope.timePickerDialogSampleCode(): Any

    interface BasicAlertDialogScope {
        fun showButton(block: SniBlock = {}): Any
        fun dialog(block: DialogScope.() -> Unit): Any
        fun assertThatDialogIsNotShown(): Any
        fun verifyDismissibleDialog()
        fun verifyMandatoryDialog()

        interface DialogScope {
            fun content(): Any
            fun confirmButton(block: SniBlock = {}): Any
            fun clickOutside(): Any
        }
    }

    interface DatePickerDialogSectionScope {
        fun timestamp(text: String = BOX): Any
        fun date(text: String = BOX): Any
        fun showButton(block: SniBlock = {}): Any
        fun calendar(block: CalendarScope.() -> Unit = {}): Any

        interface CalendarScope {
            fun selectedDate(text: String = "Selected date"): Any
            fun PreviewScope.on(date: String, scrollTo: Boolean = false, block: SniBlock = {}): Any
            fun okButton(block: SniBlock = {}): Any
        }
    }

    interface TimePickerDialogSectionScope {
        fun PreviewScope.time(text: String, scrollTo: Boolean = false): Any
        fun PreviewScope.setTimeButton(block: SniBlock = {}): Any
        fun clock(block: ClockScope.() -> Unit): Any

        interface ClockScope {
            fun verify12HourMode(): Any
            fun PreviewScope.verify24HourMode(scrollTo: Boolean = false): Any
            fun daySection(block: DaySectionScope.() -> Unit = {}): Any
            fun switchToMinutesMode(): Any
            fun verifyMinutesMode(): Any
            fun select(
                mode: TimePickerRobot.Mode,
                hour: String? = null,
                minute: String? = null,
            ): Any

            fun okButton(block: SniBlock = {}): Any
        }
    }
}

fun dialogRobot(
    rule: ComposeContentTestRule,
    block: DialogRobot.() -> Unit,
): DialogRobot = DialogRobotImpl(rule).apply(block)
