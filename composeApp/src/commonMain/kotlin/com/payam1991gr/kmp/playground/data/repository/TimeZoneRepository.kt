package com.payam1991gr.kmp.playground.data.repository

import com.payam1991gr.kmp.playground.data.model.time.LabeledTimeZone
import kotlinx.collections.immutable.PersistentList

interface TimeZoneRepository {
    val timeZones: PersistentList<LabeledTimeZone>
}
