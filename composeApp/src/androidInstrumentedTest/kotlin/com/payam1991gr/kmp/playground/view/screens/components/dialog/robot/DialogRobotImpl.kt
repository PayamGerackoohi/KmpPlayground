package com.payam1991gr.kmp.playground.view.screens.components.dialog.robot

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.components.dialog.robot.DialogRobot.BasicAlertDialogScope
import com.payam1991gr.kmp.playground.view.screens.components.dialog.robot.DialogRobot.DatePickerDialogSectionScope
import com.payam1991gr.kmp.playground.view.screens.components.dialog.robot.DialogRobot.TimePickerDialogSectionScope
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.robot.DatePickerRobot
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.robot.TimePickerRobot
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.robot.datePickerRobot
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.robot.timePickerRobot
import com.payam1991gr.kmp.playground.view.test.util.SniBlock
import com.payam1991gr.kmp.playground.view.test.util.uiDevice

class DialogRobotImpl(private val rule: ComposeContentTestRule) : DialogRobot {
    override fun PreviewScope.basicAlertDialogSection(block: BasicAlertDialogScope.() -> Unit) =
        BasicAlertDialogScopeImpl().block()

    override fun PreviewScope.datePickerDialogSection(block: DatePickerDialogSectionScope.() -> Unit) =
        DatePickerDialogSectionScopeImpl().block()

    override fun PreviewScope.timePickerDialogSection(block: TimePickerDialogSectionScope.() -> Unit) =
        TimePickerDialogSectionScopeImpl().block()

    override fun CodeScope.basicAlertDialogSampleCode() = rule.apply {
        onNodeWithText("@OptIn(ExperimentalMaterial3Api::class)", substring = true)
            .assertIsDisplayed()
        onNodeWithText("@Composable", substring = true)
            .assertIsDisplayed()
        onNodeWithText("fun BasicAlertDialogSample() = ContentList {", substring = true)
            .assertIsDisplayed()
    }

    override fun CodeScope.datePickerDialogSampleCode() = rule
        .onNodeWithText(
            "fun DatePickerDialogSample(state: DatePicker) = ContentList {",
            substring = true,
        )
        .assertIsDisplayed()

    override fun CodeScope.timePickerDialogSampleCode() = rule
        .onNodeWithText("fun TimePickerDialogSample() = ContentList {", substring = true)
        .assertIsDisplayed()

    inner class BasicAlertDialogScopeImpl : BasicAlertDialogScope {
        override fun showButton(block: SniBlock) = rule
            .onNodeWithTag("BasicAlertDialog.Show")
            .assertIsDisplayed()
            .block()

        override fun dialog(block: BasicAlertDialogScope.DialogScope.() -> Unit) = rule
            .onNodeWithTag("BasicAlertDialogSample")
            .assertIsDisplayed()
            .apply { DialogScopeImpl().block() }

        override fun assertThatDialogIsNotShown() = rule
            .onAllNodesWithTag("BasicAlertDialogSample")
            .assertCountEquals(0)

        override fun verifyDismissibleDialog() {
            assertThatDialogIsNotShown()
            showButton { performClick() }
            dialog {
                content()
                confirmButton { performClick() }
            }
            assertThatDialogIsNotShown()
            showButton { performClick() }
            dialog {
                content()
                clickOutside()
            }
            assertThatDialogIsNotShown()
        }

        override fun verifyMandatoryDialog() {
            assertThatDialogIsNotShown()
            showButton { performClick() }
            dialog {
                content()
                clickOutside()
                confirmButton { performClick() }
            }
            assertThatDialogIsNotShown()
        }

        inner class DialogScopeImpl : BasicAlertDialogScope.DialogScope {
            override fun content() = rule.onNodeWithText("This area typically", substring = true)
                .assertIsDisplayed()

            override fun confirmButton(block: SniBlock) = rule.onNodeWithText("Confirm")
                .assertIsDisplayed()
                .block()

            // t*do actually not clicking outside!
//            override fun clickOutside() = rule.onRoot().performTouchInput { click(Offset.Zero) } // not working
//            override fun clickOutside() = rule.onAllNodes(isRoot())[0].performTouchInput { click(Offset.Zero) } // not working
//            override fun clickOutside() = rule.onAllNodes(isRoot())[1].performTouchInput { click(Offset.Zero) } // not working
//            override fun clickOutside() = rule.onNodeWithTag("TestRoot").performTouchInput { click(Offset.Zero) } // not working
//            override fun clickOutside() = uiDevice.click(0, 0) // not working
//            override fun clickOutside() = rule.onAllNodes(isRoot())[0].performKeyPress(KeyEvent(NativeKeyEvent(NativeKeyEvent.ACTION_DOWN, NativeKeyEvent.KEYCODE_BACK))) // not working
//            override fun clickOutside() = rule.onAllNodes(isRoot())[1].performKeyPress(KeyEvent(NativeKeyEvent(NativeKeyEvent.ACTION_DOWN, NativeKeyEvent.KEYCODE_BACK))) // not working
            override fun clickOutside() = uiDevice.pressBack()
        }
    }

    inner class DatePickerDialogSectionScopeImpl : DatePickerDialogSectionScope {
        override fun timestamp(text: String) = rule
            .onNodeWithText("Timestamp: $text")
            .assertIsDisplayed()

        override fun date(text: String) = rule
            .onNodeWithText("Date: $text")
            .assertIsDisplayed()

        override fun showButton(block: SniBlock) = rule
            .onNodeWithTag("DatePickerDialog.Show")
            .assertIsDisplayed()
            .block()

        override fun calendar(
            block: DatePickerDialogSectionScope.CalendarScope.() -> Unit,
        ) = datePickerRobot(rule) {
            CalendarScopeImpl(this).block()
        }

        inner class CalendarScopeImpl(private val parent: DatePickerRobot) :
            DatePickerDialogSectionScope.CalendarScope {
            override fun selectedDate(text: String) = parent.selectedDate(text)

            override fun PreviewScope.on(date: String, scrollTo: Boolean, block: SniBlock) =
                parent.apply { on(date, scrollTo, block) }

            override fun okButton(block: SniBlock) = rule
                .onNodeWithText("OK")
                .block()
        }
    }

    inner class TimePickerDialogSectionScopeImpl : TimePickerDialogSectionScope {
        override fun PreviewScope.time(text: String, scrollTo: Boolean) {
            val tag = "TimePicker.Text"
            if (scrollTo) sni.performScrollToNode(hasTestTag(tag))
            rule.onNodeWithTag(tag)
                .assertIsDisplayed()
                .assertTextEquals("Time: $text")
        }

        override fun PreviewScope.setTimeButton(block: SniBlock) = rule
            .onNodeWithTag("TimePicker.SetTime")
            .assertIsDisplayed()
            .block()

        override fun clock(block: TimePickerDialogSectionScope.ClockScope.() -> Unit) =
            timePickerRobot(rule) {
                ClockScopeImpl(this).block()
            }

        inner class ClockScopeImpl(
            private val parent: TimePickerRobot,
        ) : TimePickerDialogSectionScope.ClockScope {
            override fun verify12HourMode() = parent.verify12HourMode()

            override fun PreviewScope.verify24HourMode(scrollTo: Boolean) = parent.apply {
                verify24HourMode(scrollTo)
            }

            override fun daySection(
                block: TimePickerRobot.DaySectionScope.() -> Unit,
            ) = parent.daySection(block)

            override fun switchToMinutesMode() = parent.switchToMinutesMode()

            override fun verifyMinutesMode() = parent.verifyMinutesMode()

            override fun select(
                mode: TimePickerRobot.Mode,
                hour: String?,
                minute: String?,
            ) = parent.select(mode, hour, minute)

            override fun okButton(block: SniBlock) = rule
                .onNodeWithText("OK")
                .assertIsDisplayed()
                .block()
        }
    }
}
