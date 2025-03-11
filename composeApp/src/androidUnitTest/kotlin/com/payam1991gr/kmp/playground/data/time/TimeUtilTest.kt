package com.payam1991gr.kmp.playground.data.time

import com.google.common.truth.Truth.*
import kotlinx.datetime.LocalTime
import org.junit.Test

class TimeUtilTest {
    @Test
    fun `toPaddedString - test`() {
        listOf(
            -100 to "-100",
            -10 to "-10",
            -1 to "-1",
            0 to "00",
            1 to "01",
            10 to "10",
            100 to "100",
        ).forEach { (input, output) ->
            assertThat(input.toPaddedString()).isEqualTo(output)
        }
    }

    @Test
    fun `data-night - test`() = listOf(
        LocalTime(0, 0) to false,
        LocalTime(1, 0) to false,
        LocalTime(2, 0) to false,
        LocalTime(3, 0) to false,
        LocalTime(4, 0) to false,
        LocalTime(5, 0) to false,
        LocalTime(6, 0) to true,
        LocalTime(7, 0) to true,
        LocalTime(8, 0) to true,
        LocalTime(9, 0) to true,
        LocalTime(10, 0) to true,
        LocalTime(11, 0) to true,
        LocalTime(12, 0) to true,
        LocalTime(13, 0) to true,
        LocalTime(14, 0) to true,
        LocalTime(15, 0) to true,
        LocalTime(16, 0) to true,
        LocalTime(17, 0) to true,
        LocalTime(18, 0) to false,
        LocalTime(19, 0) to false,
        LocalTime(20, 0) to false,
        LocalTime(21, 0) to false,
        LocalTime(22, 0) to false,
        LocalTime(23, 0) to false,
    ).forEach { (input, isDay) ->
        assertThat(input.isDay()).isEqualTo(isDay)
        assertThat(input.isNight()).isNotEqualTo(isDay)
    }
}
