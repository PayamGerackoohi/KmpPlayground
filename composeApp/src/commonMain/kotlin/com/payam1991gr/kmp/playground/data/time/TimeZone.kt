package com.payam1991gr.kmp.playground.data.time

import com.payam1991gr.kmp.playground.data.model.time.LabeledTimeZone
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.asTimeZone
import kotlinx.datetime.offsetAt

fun TimeZone.offsetStringAt(instant: Instant) = offsetAt(instant).string()

infix fun TimeZone.label(label: String) = LabeledTimeZone(this, label)

fun UtcOffset.string() = if (this == UtcOffset.ZERO) "+0 HRS" else toString()

fun UtcOffset.asLabeledTimeZone() = asTimeZone().run {
    LabeledTimeZone(
        this,
        when (this) {
            TimeZone.UTC -> "GMT"
            else -> "UTC$id"
        }
    )
}
