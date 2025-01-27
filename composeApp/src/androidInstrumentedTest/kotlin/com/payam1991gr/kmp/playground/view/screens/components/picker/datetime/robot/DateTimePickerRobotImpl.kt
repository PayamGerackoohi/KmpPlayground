package com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.robot

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.filter
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTextReplacement
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.robot.DateTimePickerRobot.DatePickerScope
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.robot.DateTimePickerRobot.DateRangePickerScope
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.robot.DateTimePickerRobot.TimeInputScope
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.robot.DateTimePickerRobot.TimePickerScope
import com.payam1991gr.kmp.playground.view.test.util.Sni
import com.payam1991gr.kmp.playground.view.test.util.SniBlock

class DateTimePickerRobotImpl(private val rule: ComposeContentTestRule) : DateTimePickerRobot {
    override fun PreviewScope.datePickerSection(block: DatePickerScope.() -> Unit) = sni
        .performScrollToNode(hasTextExactly("Date Picker"))
        .assertIsDisplayed()
        .apply { DatePickerScopeImpl().block() }

    override fun PreviewScope.dateRangePickerSection(block: DateRangePickerScope.() -> Unit) = sni
        .performScrollToNode(hasTextExactly("Date Range Picker"))
        .assertIsDisplayed()
        .apply { DateRangePickerScopeImpl().block() }

    override fun PreviewScope.timePickerSection(block: TimePickerScope.() -> Unit) = sni
        .performScrollToNode(hasTextExactly("Time Picker"))
        .assertIsDisplayed()
        .apply { TimePickerScopeImpl().block() }

    override fun PreviewScope.timeInputSection(block: TimeInputScope.() -> Unit) = sni
        .performScrollToNode(hasTextExactly("Time Input"))
        .assertIsDisplayed()
        .apply { TimeInputScopeImpl().block() }

    override fun CodeScope.resources() {
        rule.onNodeWithText("private val timestampStringRes = Res.string.", substring = true)
            .assertIsDisplayed()
        rule.onNodeWithText("private val dateStringRes = Res.string.", substring = true)
            .assertIsDisplayed()
    }

    override fun CodeScope.datePickerSample() = rule.onNodeWithText(
        "fun DatePickerSample(state: State.DatePicker) = ContentList {",
        substring = true,
    ).assertIsDisplayed()

    override fun CodeScope.dateRangePickerSample() = rule.onNodeWithText(
        "fun DateRangePickerSample(state: State.DateRangePicker) = ContentList {",
        substring = true,
    ).assertIsDisplayed()

    override fun CodeScope.timePickerSample() = rule.onNodeWithText(
        "fun TimePickerSample(state: State.TimeState) = \"TimePicker\".let { tag ->",
        substring = true,
    ).assertIsDisplayed()

    override fun CodeScope.timeInputSample() = rule.onNodeWithText(
        "fun TimeInputSample(state: State.TimeState) = \"TimeInput\".let { tag ->",
        substring = true,
    ).assertIsDisplayed()

    override fun CodeScope.box() = rule
        .onNodeWithText("fun Any?.box() = this?.toString() ?: \"☐\"", substring = true)
        .assertIsDisplayed()

    inner class DatePickerScopeImpl : DatePickerScope {
        override fun PreviewScope.timestamp(value: String, scrollTo: Boolean) {
            val text = "Timestamp: $value"
            if (scrollTo) sni.performScrollToNode(hasText(text))
            rule
                .onNodeWithText(text)
                .assertIsDisplayed()
        }

        override fun date(text: String) = rule
            .onNodeWithText("Date: $text")
            .assertIsDisplayed()
    }

    inner class DateRangePickerScopeImpl : DateRangePickerScope {
        override fun PreviewScope.timestamp(from: String, to: String, scrollTo: Boolean) {
            val text = "Timestamp: $from - $to"
            if (scrollTo) sni.performScrollToNode(hasTextExactly(text))
            sni.onChildren()
                .filterToOne(hasTextExactly(text))
                .assertIsDisplayed()
        }

        override fun date(from: String, to: String) = rule
            .onNodeWithText("Date: $from - $to")
            .assertIsDisplayed()

        override fun PreviewScope.on(date: String, scrollTo: Boolean, block: SniBlock) {
            if (scrollTo) sni.performScrollToNode(hasText(date))
            rule.onNodeWithText(date)
                .assertIsDisplayed()
                .block()
        }

        override fun selectedDate(start: String, end: String) = rule
            .onNodeWithContentDescription("Start date: $start, End date: $end")
            .assertIsDisplayed()

        private fun onDateRangePicker() = rule.onNodeWithTag("DateRangePicker")

        override fun switchModeButton() = onDateRangePicker()
            .onChildren()
            .filterToOne(hasContentDescription("Switch to text input mode"))
            .assertIsDisplayed()

        override fun assertThatSwitchModeButtonIsNotShown() = onDateRangePicker()
            .onChildren()
            .filter(hasContentDescription("Switch to text input mode"))
            .assertCountEquals(0)

        override fun settings(block: DateRangePickerScope.SettingsScope.() -> Unit) = rule
            .onNodeWithTag("DateRangePicker.Settings")
            .assertIsDisplayed()
            .apply { SettingsScopeImpl(this).block() }

        inner class SettingsScopeImpl(private val sni: Sni) : DateRangePickerScope.SettingsScope {
            override fun on(label: String, block: SniBlock) = sni
                .onChildren()
                .filterToOne(hasTextExactly(label))
                .block()
        }
    }

    inner class TimePickerScopeImpl : TimePickerScope {
        private val tag = "TimePicker"

        override fun settings(block: TimePickerScope.SettingsScope.() -> Unit) = rule
            .onNodeWithTag("$tag.Settings")
            .assertIsDisplayed()
            .apply { SettingsScopeImpl(this).block() }

        override fun PreviewScope.time(value: String, scrollTo: Boolean) {
            val text = "$tag.Text"
            if (scrollTo) sni.performScrollToNode(hasTestTag(text))
            rule
                .onNodeWithTag(text)
                .assertTextEquals("Time: $value")
                .assertIsDisplayed()
        }

        inner class SettingsScopeImpl(private val sni: Sni) : TimePickerScope.SettingsScope {
            override fun on(label: String, block: SniBlock) = sni
                .onChildren()
                .filterToOne(hasTextExactly(label))
                .assertIsDisplayed()
                .block()
        }
    }

    inner class TimeInputScopeImpl : TimeInputScope {
        override fun PreviewScope.time(text: String, scrollTo: Boolean) {
            val tag = "TimeInput.Text"
            if (scrollTo) sni.performScrollToNode(hasTestTag(tag))
            rule.onNodeWithTag(tag)
                .assertTextEquals("Time: $text")
                .assertIsDisplayed()
        }

        override fun settings(block: TimeInputScope.SettingsScope.() -> Unit) = rule
            .onNodeWithTag("TimeInput.Settings")
            .assertIsDisplayed()
            .apply { SettingsScopeImpl(this).block() }

        override fun input(block: TimeInputScope.InputScope.() -> Unit) = rule
            .onNodeWithTag("TimeInput")
            .assertIsDisplayed()
            .apply { InputScopeImpl(this).block() }

        inner class SettingsScopeImpl(private val sni: Sni) : TimeInputScope.SettingsScope {
            override fun on(label: String, block: SniBlock) = sni
                .onChildren()
                .filterToOne(hasTextExactly(label))
                .assertIsDisplayed()
                .block()
        }

        inner class InputScopeImpl(private val sni: Sni) : TimeInputScope.InputScope {
            override fun selectMinutes() = sni
                .onChildren()
                .filterToOne(hasContentDescription("Select minutes"))
                .assertIsDisplayed()
                .performClick()

            override fun insert(hour: String?, minute: String?) {
                insert(hour, "for hour")
                insert(minute, "for minutes")
            }

            private fun insert(time: String?, label: String) = time?.let {
                sni.onChildren()
                    .filterToOne(hasContentDescription(label))
                    .assertIsDisplayed()
                    .performTextReplacement(it)
            }

            override fun daySection(block: TimeInputScope.InputScope.DaySectionScope.() -> Unit) =
                rule
                    .onNodeWithContentDescription("Select AM or PM")
                    .assertIsDisplayed()
                    .apply { DaySectionScopeImpl(this).block() }

            inner class DaySectionScopeImpl(private val sni: Sni) :
                TimeInputScope.InputScope.DaySectionScope {
                override fun am() = sni
                    .onChildren()
                    .filterToOne(hasTextExactly("AM"))
                    .performClick()

                override fun pm() = sni
                    .onChildren()
                    .filterToOne(hasTextExactly("PM"))
                    .performClick()
            }
        }
    }
}
