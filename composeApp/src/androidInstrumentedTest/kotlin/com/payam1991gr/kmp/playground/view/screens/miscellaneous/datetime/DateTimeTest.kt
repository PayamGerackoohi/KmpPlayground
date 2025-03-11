package com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.data.time.label
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.module.clock.robot.analogClockRobot
import com.payam1991gr.kmp.playground.view.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.DateTimeScreen.State
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.DateTimeScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.robot.dateTimeRobot
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import org.junit.Test

class DateTimeTest : BaseTest() {
    @Test
    fun test() {
        var showCode by mutableStateOf(false)
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        val timeZones = persistentListOf(
            TimeZone.UTC label "GMT",
            TimeZone.of("Asia/Tokyo") label "Tokyo",
        )
        var timestamp by mutableLongStateOf(0)
        rule.setContent {
            val instant = remember(timestamp) { Instant.fromEpochSeconds(timestamp) }
            DateTime().Content(
                State(
                    showCode = showCode,
                    toolbarActions = persistentListOf(
                        Action.Back,
                        if (showCode) Action.Preview else Action.Code,
                    ),
                    now = instant,
                    timeZones = timeZones,
                    event = event,
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            dateTimeRobot(rule) {
                toolbar {
                    title("Date/Time")

                    on("Back") { performClick() }
                    verify { event(Event.OnToolbarAction(Action.Back)) }

                    assertThatCodePageIsNotDisplayed()
                    preview {
                        analogClockRobot(rule) {
                            card("GMT") {
                                clock("GMT") { state("00:00:00") }
                                title()
                                date("1970-01-01")
                                time("00:00:00")
                                utc("+0 HRS")
                            }
                            card("Tokyo") {
                                clock("Tokyo") { state("09:00:00") }
                                title()
                                date("1970-01-01")
                                time("09:00:00")
                                utc("+09:00")
                            }
                            timestamp = 1_000_000
                            card("GMT") {
                                clock("GMT") { state("13:46:40") }
                                title()
                                date("1970-01-12")
                                time("13:46:40")
                                utc("+0 HRS")
                            }
                            card("Tokyo") {
                                clock("Tokyo") { state("22:46:40") }
                                title()
                                date("1970-01-12")
                                time("22:46:40")
                                utc("+09:00")
                            }
                        }
                        on("Code") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Code)) }
                        showCode = true
                    }

                    assertThatPreviewPageIsNotDisplayed()
                    code {
                        analogClockCard()
                        analogClock()
                        timeZoneData()
                        on("Preview") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Preview)) }
                    }
                }
            }
        }
        confirmVerified()
    }
}
