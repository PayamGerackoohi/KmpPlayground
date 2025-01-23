package com.payam1991gr.kmp.playground.ui.screens.components.dialog

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.preview.Fake
import com.payam1991gr.kmp.playground.ui.LocalTestMode
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogScreen.State
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogScreen.State.Event
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.robot.dialogRobot
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.robot.TimePickerRobot
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.state.rememberDatePickerState
import com.payam1991gr.kmp.playground.ui.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test

class DialogTest : BaseTest() {
    @Test
    fun screenTest() {
        var showCode by mutableStateOf(false)
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            CompositionLocalProvider(LocalTestMode provides true) {
                Dialog().Content(
                    State(
                        showCode = showCode,
                        toolbarActions = persistentListOf(
                            Action.Back,
                            if (showCode) Action.Preview else Action.Code,
                        ),
                        datePicker = rememberDatePickerState(),
                        event = event,
                    ),
                    Modifier,
                )
            }
        }
        samplePageRobot(rule) {
            dialogRobot(rule) {
                toolbar {
                    title("Dialog")
                    on("Back") { performClick() }
                    verify { event(Event.OnToolbarAction(Action.Back)) }

                    assertThatCodePageIsNotDisplayed()
                    preview {
                        basicAlertDialogSection { header("Basic Alert Dialog") }
                        datePickerDialogSection { header("Date Picker Dialog") }
                        timePickerDialogSection { header("Time Picker Dialog") }

                        on("Code") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Code)) }
                        showCode = true
                    }

                    assertThatPreviewPageIsNotDisplayed()
                    code {
                        basicAlertDialogSampleCode()
                        datePickerDialogSampleCode()
                        timePickerDialogSampleCode()

                        on("Preview") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Preview)) }
                    }
                }
            }
        }
        confirmVerified()
    }

    @Test
    fun basicAlertDialogTest() {
        rule.setContent {
            CompositionLocalProvider(LocalTestMode provides true) {
                Dialog().Content(
                    State(
                        showCode = false,
                        toolbarActions = persistentListOf(),
                        datePicker = rememberDatePickerState(),
                        event = mockk(),
                    ),
                    Modifier,
                )
            }
        }
        samplePageRobot(rule) {
            dialogRobot(rule) {
                preview {
                    basicAlertDialogSection {
                        header("Basic Alert Dialog")
                        description("Dialogs provide important prompts")
                        verifyDismissibleDialog()

                        settings {
                            on("Dismissible") { performClick() }
                            on("Mandatory")

                            on("Animate") { performClick() }
                            on("Instant")
                        }

                        verifyMandatoryDialog()
                    }
                }
            }
        }
        confirmVerified()
    }

    @Test
    fun datePickerDialogTest() {
        rule.setContent {
            CompositionLocalProvider(LocalTestMode provides true) {
                Dialog().Content(
                    State(
                        showCode = false,
                        toolbarActions = persistentListOf(),
                        datePicker = rememberDatePickerState(
                            initialDisplayDate = Fake.timestamp,
                        ),
                        event = mockk(),
                    ),
                    Modifier,
                )
            }
        }
        samplePageRobot(rule) {
            dialogRobot(rule) {
                preview {
                    datePickerDialogSection {
                        header("Date Picker Dialog")
                        description("A dialog for displaying a DatePicker. Date pickers let people")
                        timestamp()
                        date()
                        showButton { performClick() }
                        calendar {
                            selectedDate()
                            on("Monday, October 14, 1991") { performClick() }
                            selectedDate("Oct 14, 1991")
                            okButton { performClick() }
                        }
                        timestamp("687398400000")
                        date("1991-10-14")
                    }
                }
            }
        }
        confirmVerified()
    }

    @Test
    fun timePickerDialogTest() {
        rule.setContent {
            CompositionLocalProvider(LocalTestMode provides true) {
                Dialog().Content(
                    State(
                        showCode = false,
                        toolbarActions = persistentListOf(),
                        datePicker = rememberDatePickerState(),
                        event = mockk(),
                    ),
                    Modifier,
                )
            }
        }
        samplePageRobot(rule) {
            dialogRobot(rule) {
                preview {
                    timePickerDialogSection {
                        header("Time Picker Dialog")
                        description("Time pickers help users select and set a specific time. It")
                        time("00:00", true)
                        setTimeButton { performClick() }
                        clock {
                            verify12HourMode()
                            select(TimePickerRobot.Mode._12Hours, hour = "2")
                            okButton { performClick() }
                        }
                        time("02:00")

                        setTimeButton { performClick() }
                        clock {
                            switchToMinutesMode()
                            verifyMinutesMode()
                            select(TimePickerRobot.Mode._12Hours, minute = "20")
                            okButton { performClick() }
                        }
                        time("02:20")

                        setTimeButton { performClick() }
                        clock {
                            daySection { pm() }
                            okButton { performClick() }
                        }
                        time("14:20")
                    }
                }
            }
        }
        confirmVerified()
    }
}
