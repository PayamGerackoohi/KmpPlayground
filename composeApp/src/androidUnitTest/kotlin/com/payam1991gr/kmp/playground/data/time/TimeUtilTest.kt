package com.payam1991gr.kmp.playground.data.time

import com.google.common.truth.Truth.*
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
}
