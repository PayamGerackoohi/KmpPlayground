package com.payam1991gr.kmp.playground.data.repository

import com.google.common.truth.Truth.*
import org.junit.Test

class TimeZoneRepositoryTest {
    private val repository = TimeZoneRepositoryImpl()

    @Test
    fun test() {
        repository.timeZones.apply {
            assertThat(size).isEqualTo(30)
            assertThat(this[0].value.id).isEqualTo("America/New_York")
            assertThat(this[0].label).isEqualTo("New York")
        }
    }
}
