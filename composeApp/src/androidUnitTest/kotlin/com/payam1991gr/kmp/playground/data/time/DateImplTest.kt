package com.payam1991gr.kmp.playground.data.time

import com.google.common.truth.Truth.*
import org.junit.Test

class DateImplTest {
    @Test
    fun `no initial value - test`() {
        DateImpl().apply {
            assertThat(ms).isNull()
            assertThat(text).isNull()
            ms = 0
            assertThat(ms).isEqualTo(0)
            assertThat(text).isEqualTo("1970-01-01")
        }
    }

    @Test
    fun `with initial value - test`() {
        DateImpl(0).apply {
            assertThat(ms).isEqualTo(0)
            assertThat(text).isEqualTo("1970-01-01")
        }
    }

    @Test
    fun `toString - test`() {
        listOf(
            DateImpl() to "Date(ms=null, text=null)",
            DateImpl(0) to "Date(ms=0, text=1970-01-01)",
        ).forEach { (input, output) ->
            assertThat(input.toString()).isEqualTo(output)
        }
    }
}
