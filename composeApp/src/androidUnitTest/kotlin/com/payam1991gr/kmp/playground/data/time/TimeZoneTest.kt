package com.payam1991gr.kmp.playground.data.time

import com.google.common.truth.Truth.*
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import org.junit.Test

class TimeZoneTest {
    @Test
    fun `offsetStringAt - test`() {
        val instant = Instant.fromEpochSeconds(0)
        listOf(
            TimeZone.of("America/New_York") to "-05:00",
            TimeZone.UTC to "+0 HRS",
            TimeZone.of("Europe/Berlin") to "+01:00",
        ).forEach { (input, output) ->
            assertThat(input.offsetStringAt(instant)).isEqualTo(output)
        }
    }

    @Test
    fun `UtcOffset_string - test`() = listOf(
        UtcOffset(-5) to "-05:00",
        UtcOffset(0) to "+0 HRS",
        UtcOffset(1) to "+01:00",
    ).forEach { (input, output) ->
        assertThat(input.string()).isEqualTo(output)
    }
}
