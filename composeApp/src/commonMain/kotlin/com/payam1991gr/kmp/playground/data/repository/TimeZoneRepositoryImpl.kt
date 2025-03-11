package com.payam1991gr.kmp.playground.data.repository

import com.payam1991gr.kmp.playground.data.model.time.LabeledTimeZone
import com.payam1991gr.kmp.playground.data.time.asLabeledTimeZone
import com.payam1991gr.kmp.playground.data.time.label
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset

class TimeZoneRepositoryImpl : TimeZoneRepository {
    override val timeZones: PersistentList<LabeledTimeZone> by lazy {
        (listOf(
            TimeZone.of("America/New_York") label "New York",
            TimeZone.UTC label "GMT",
            TimeZone.of("Europe/Berlin") label "Berlin",
            TimeZone.currentSystemDefault() label "System Default",
            TimeZone.of("Asia/Tokyo") label "Tokyo",
        ) + (-12..12).map { UtcOffset(it).asLabeledTimeZone() })
            .toPersistentList()
    }
}
