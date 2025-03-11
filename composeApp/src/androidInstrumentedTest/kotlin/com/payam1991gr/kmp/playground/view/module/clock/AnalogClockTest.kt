package com.payam1991gr.kmp.playground.view.module.clock

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.payam1991gr.kmp.playground.data.time.offsetStringAt
import com.payam1991gr.kmp.playground.view.module.clock.robot.analogClockRobot
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.junit.Test

class AnalogClockTest : BaseTest() {
    @Test
    fun test() {
        val timeZone = TimeZone.UTC
        var timestamp by mutableLongStateOf(0)
        rule.setContent {
            val (dateTime, zone) = remember(timestamp) {
                Instant.fromEpochSeconds(timestamp).let { instant ->
                    instant.toLocalDateTime(timeZone) to
                            timeZone.run { TimeZoneData(id, offsetStringAt(instant)) }
                }
            }
            AnalogClock(
                date = dateTime.date,
                time = dateTime.time,
                zone = zone,
                tag = "GMT",
            )
        }
        analogClockRobot(rule) {
            clock("GMT") {
                state("00:00:00")
                timestamp = 1_000_000_000
                state("01:46:40")
            }
        }
    }
}
