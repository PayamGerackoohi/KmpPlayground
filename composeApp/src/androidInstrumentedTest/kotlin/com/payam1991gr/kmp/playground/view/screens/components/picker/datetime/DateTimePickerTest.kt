package com.payam1991gr.kmp.playground.view.screens.components.picker.datetime

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import com.payam1991gr.kmp.playground.data.model.time.Time
import com.payam1991gr.kmp.playground.data.time.DateImpl
import com.payam1991gr.kmp.playground.preview.Fake
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen.State
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.robot.TimePickerRobot
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.robot.datePickerRobot
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen.State.TimeState.Event as TimeStateEvent
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.robot.dateTimePickerRobot
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.robot.timePickerRobot
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.state.DateRangePickerImpl
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test

class DateTimePickerTest : BaseTest() {
    @Test
    fun screenTest() {
        var showCode by mutableStateOf(false)
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            DateTimePicker().Content(
                State(
                    showCode = showCode,
                    toolbarActions = persistentListOf(
                        Action.Back,
                        if (showCode) Action.Preview else Action.Code,
                    ),
                    datePicker = Fake.DatePickerState(),
                    dateRangePicker = Fake.DateRangePickerState(),
                    timePicker = Fake.timeState(),
                    timeInput = Fake.timeState(),
                    event = event,
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            dateTimePickerRobot(rule) {
                toolbar {
                    title("Date-Time Picker")
                    on("Back") { performClick() }
                    verify { event(Event.OnToolbarAction(Action.Back)) }

                    assertThatCodePageIsNotDisplayed()
                    preview {
                        datePickerSection()
                        dateRangePickerSection()
                        timePickerSection()
                        timeInputSection()

                        on("Code") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Code)) }
                        showCode = true
                    }

                    assertThatPreviewPageIsNotDisplayed()
                    code {
                        resources()
                        datePickerSample()
                        dateRangePickerSample()
                        timePickerSample()
                        timeInputSample()
                        box()

                        on("Preview") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Preview)) }
                    }
                }
            }
        }
        confirmVerified()
    }

    @Test
    fun datePicker_NoInitDate_Test() {
        rule.setContent {
            DateTimePicker().Content(
                State(
                    showCode = false,
                    toolbarActions = persistentListOf(),
                    datePicker = Fake.DatePickerState(
                        initialDisplayDate = Fake.timestamp,
                        date = DateImpl(),
                    ),
                    dateRangePicker = Fake.DateRangePickerState(),
                    timePicker = Fake.timeState(),
                    timeInput = Fake.timeState(),
                    event = mockk(),
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            dateTimePickerRobot(rule) {
                preview {
                    datePickerSection {
                        header("Date Picker")
                        description("Date pickers let people select a date and preferably")

                        datePickerRobot(rule) {
                            verifySwitchModeButton()
                            settings { on("Mode Toggle") { performClick() } }
                            assertThatSwitchModeButtonIsNotShown()
                            settings { on("No Mode Toggle") }

                            timestamp()
                            date()
                            selectedDate()

                            on("Monday, October 14, 1991", true) { performClick() }
                            timestamp("687398400000", true)
                            date("1991-10-14")
                            selectedDate("Oct 14, 1991")
                        }
                    }
                }
            }
        }
        confirmVerified()
    }

    @Test
    fun datePicker_WithInitDate_Test() {
        rule.setContent {
            DateTimePicker().Content(
                State(
                    showCode = false,
                    toolbarActions = persistentListOf(),
                    datePicker = Fake.DatePickerState(
                        initialDisplayDate = Fake.timestamp,
                        date = DateImpl(initialTimestamp = Fake.timestamp),
                    ),
                    dateRangePicker = Fake.DateRangePickerState(),
                    timePicker = Fake.timeState(),
                    timeInput = Fake.timeState(),
                    event = mockk(),
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            dateTimePickerRobot(rule) {
                preview {
                    datePickerSection {
                        header("Date Picker")
                        description("Date pickers let people select a date and preferably")

                        datePickerRobot(rule) {
                            timestamp("687398400000")
                            date("1991-10-14")
                            selectedDate("Oct 14, 1991")

                            on("Thursday, October 31, 1991", true) { performClick() }
                            timestamp("688867200000", true)
                            date("1991-10-31")
                            selectedDate("Oct 31, 1991")
                        }
                    }
                }
            }
        }
        confirmVerified()
    }

    @Test
    fun dateRangePicker_NoInitRange_Test() {
        rule.setContent {
            DateTimePicker().Content(
                State(
                    showCode = false,
                    toolbarActions = persistentListOf(),
                    datePicker = Fake.DatePickerState(),
                    dateRangePicker = DateRangePickerImpl(
                        initialDisplayDate = Fake.timestamp,
                    ),
                    timePicker = Fake.timeState(),
                    timeInput = Fake.timeState(),
                    event = mockk(),
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            dateTimePickerRobot(rule) {
                preview {
                    dateRangePickerSection {
                        header("Date Range Picker")
                        description("Date range pickers let people select a range of dates and")

                        switchModeButton()
                        settings { on("Mode Toggle") { performClick() } }
                        assertThatSwitchModeButtonIsNotShown()
                        settings { on("No Mode Toggle") }

                        timestamp()
                        date()
                        selectedDate()

                        on("Monday, October 14, 1991", true) { performClick() }
                        timestamp("687398400000", scrollTo = true)
                        date("1991-10-14")
                        selectedDate("Monday, October 14, 1991")

                        on("Thursday, October 31, 1991", true) { performClick() }
                        timestamp("687398400000", "688867200000", true)
                        date("1991-10-14", "1991-10-31")
                        selectedDate("Monday, October 14, 1991", "Thursday, October 31, 1991")
                    }
                }
            }
        }
        confirmVerified()
    }

    @Test
    fun dateRangePicker_WithInitRange_Test() {
        rule.setContent {
            DateTimePicker().Content(
                State(
                    showCode = false,
                    toolbarActions = persistentListOf(),
                    datePicker = Fake.DatePickerState(),
                    dateRangePicker = DateRangePickerImpl(
                        initialDisplayDate = Fake.timestamp,
                        initialStartDate = Fake.timestamp,
                        initialEndDate = Fake.timestamp2,
                    ),
                    timePicker = Fake.timeState(),
                    timeInput = Fake.timeState(),
                    event = mockk(),
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            dateTimePickerRobot(rule) {
                preview {
                    dateRangePickerSection {
                        header("Date Range Picker")
                        description("Date range pickers let people select a range of dates and")
                        timestamp("687398400000", "688867200000", true)
                        date("1991-10-14", "1991-10-31")
                        selectedDate("Monday, October 14, 1991", "Thursday, October 31, 1991")
                    }
                }
            }
        }
        confirmVerified()
    }

    @Test
    fun timePicker_Test() {
        val event = mockk<(TimeStateEvent) -> Unit> { every { this@mockk(any()) } just runs }
        var state by mutableStateOf(State.TimeState(Time(), event))
        rule.setContent {
            DateTimePicker().Content(
                State(
                    showCode = false,
                    toolbarActions = persistentListOf(),
                    datePicker = Fake.DatePickerState(),
                    dateRangePicker = Fake.DateRangePickerState(),
                    timePicker = state,
                    timeInput = Fake.timeState(),
                    event = mockk(),
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            dateTimePickerRobot(rule) {
                preview {
                    timePickerSection {
                        header("Time Picker")
                        description("It shows a picker that allows the user to select time. ")
                        timePickerRobot(rule) {
                            verify24HourMode(true)
                            time("00:00", true)
                            verify { event(TimeStateEvent.OnHourChanged(0)) }
                            verify { event(TimeStateEvent.OnMinuteChanged(0)) }

                            select(TimePickerRobot.Mode._24Hours, hour = "14")
                            rule.waitForIdle()
                            verify { event(TimeStateEvent.OnHourChanged(14)) }
                            state = state.copy(time = state.time.copy(hour = 14))
                            time("14:00")

                            verifyMinutesMode()
                            select(TimePickerRobot.Mode._24Hours, minute = "50")
                            rule.waitForIdle()
                            verify { event(TimeStateEvent.OnMinuteChanged(50)) }
                            state = state.copy(time = state.time.copy(minute = 50))
                            time("14:50")

                            settings {
                                on("24 Hour") { performClick() }
                                on("12 Hour")
                            }

                            verify12HourMode()
                            time("14:50")
                            daySection { am() }
                        }
                        rule.waitForIdle()
                        verify { event(TimeStateEvent.OnHourChanged(2)) }
                        state = state.copy(time = state.time.copy(hour = 2))
                        time("02:50")
                    }
                }
            }
        }
        confirmVerified()
    }

    @Test
    fun timeInput_Test() {
        val event = mockk<(TimeStateEvent) -> Unit> { every { this@mockk(any()) } just runs }
        var state by mutableStateOf(State.TimeState(Time(), event))
        rule.setContent {
            DateTimePicker().Content(
                State(
                    showCode = false,
                    toolbarActions = persistentListOf(),
                    datePicker = Fake.DatePickerState(),
                    dateRangePicker = Fake.DateRangePickerState(),
                    timePicker = Fake.timeState(),
                    timeInput = state,
                    event = mockk(),
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            dateTimePickerRobot(rule) {
                preview {
                    timeInputSection {
                        header("Time Input")
                        description("It shows a time input that allows the user to enter the time ")

                        time("00:00", true)
                        verify { event(TimeStateEvent.OnHourChanged(0)) }
                        verify { event(TimeStateEvent.OnMinuteChanged(0)) }

                        input {
                            insert(hour = "14")
                            rule.waitForIdle()
                            verify { event(TimeStateEvent.OnHourChanged(14)) }
                            state = state.copy(time = state.time.copy(hour = 14))
                            time("14:00")

                            selectMinutes()
                            insert(minute = "50")
                            rule.onNodeWithContentDescription("for minutes")
                                .assertIsDisplayed()
                                .performTextReplacement("50")
                            rule.waitForIdle()
                            verify { event(TimeStateEvent.OnMinuteChanged(50)) }
                            state = state.copy(time = state.time.copy(minute = 50))
                            time("14:50")

                            settings {
                                on("24 Hour") { performClick() }
                                on("12 Hour")
                            }

                            time("14:50")
                            daySection { am() }
                        }
                        rule.waitForIdle()
                        verify { event(TimeStateEvent.OnHourChanged(2)) }
                        state = state.copy(time = state.time.copy(hour = 2))
                        time("02:50")
                    }
                }
            }
        }
        confirmVerified()
    }
}
