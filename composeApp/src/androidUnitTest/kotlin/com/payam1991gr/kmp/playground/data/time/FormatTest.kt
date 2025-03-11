package com.payam1991gr.kmp.playground.data.time

import com.google.common.truth.Truth.*
import kotlinx.datetime.LocalTime
import org.junit.Test

class FormatTest {
    @Test
    fun `timeFormat - test`() = listOf(
        LocalTime(0,0,0) to "00:00:00",
        LocalTime(1, 2, 3) to "01:02:03",
        LocalTime(23,59,59) to "23:59:59",
    ).forEach { (input, output) ->
        assertThat(timeFormat.format(input)).isEqualTo(output)
    }
}
