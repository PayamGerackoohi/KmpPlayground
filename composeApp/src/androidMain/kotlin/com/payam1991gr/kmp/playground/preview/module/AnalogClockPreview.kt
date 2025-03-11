package com.payam1991gr.kmp.playground.preview.module

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.payam1991gr.kmp.playground.data.time.offsetStringAt
import com.payam1991gr.kmp.playground.preview.SinglePreview
import com.payam1991gr.kmp.playground.view.module.clock.AnalogClock
import com.payam1991gr.kmp.playground.view.module.clock.TimeZoneData
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@SinglePreview
@Composable
fun AnalogClock_Preview() {
    val timeZone = remember { TimeZone.currentSystemDefault() }
    val now = remember { Clock.System.now() }
    val dateTime = remember { now.toLocalDateTime(timeZone) }
    val zone = remember { timeZone.run { TimeZoneData(id, offsetStringAt(now)) } }
    AnalogClock(date = dateTime.date, time = dateTime.time, zone = zone)
}
