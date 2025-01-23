package com.payam1991gr.kmp.playground.data.time

import com.google.common.truth.Truth.*
import org.junit.Test

class DateUtilTest {
    @Test
    fun `toDate - test`() {
        listOf(
            null to null,
            0L to "1970-01-01",
            500000000000 to "1985-11-05",
            600000000000 to "1989-01-05",
            687398400000 to "1991-10-14",
            688867200000 to "1991-10-31",
            700000000000 to "1992-03-07",
            800000000000 to "1995-05-09",
            900000000000 to "1998-07-09",
            1000000000000 to "2001-09-09",
            1500000000000 to "2017-07-14",
            2000000000000 to "2033-05-18",
        ).forEach { (input, output) ->
            assertThat(input.toDate()).isEqualTo(output)
        }
    }
}
