package com.payam1991gr.kmp.playground.data.time

import com.google.common.truth.Truth.*
import org.junit.Test

class DateImplTest {
    @Test
    fun `no initial value - test`() {
        DateImpl().apply {
            assertThat(text).isNull()
            ms = 0
            assertThat(text).isEqualTo("1970-01-01")
        }
    }

    @Test
    fun `with initial value - test`() {
        DateImpl(0).apply {
            assertThat(text).isEqualTo("1970-01-01")
        }
    }
}
