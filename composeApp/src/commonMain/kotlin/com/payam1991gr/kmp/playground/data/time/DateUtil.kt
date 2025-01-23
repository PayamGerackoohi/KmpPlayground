package com.payam1991gr.kmp.playground.data.time

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Long?.toDate(): String? = this?.let {
    Instant.fromEpochMilliseconds(it)
        .toLocalDateTime(TimeZone.UTC)
        .date.toString()
}
