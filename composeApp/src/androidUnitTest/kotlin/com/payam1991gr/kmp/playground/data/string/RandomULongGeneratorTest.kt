package com.payam1991gr.kmp.playground.data.string

import com.google.common.truth.Truth.*
import org.junit.Test

class RandomULongGeneratorTest {
    @Test
    fun `regex - test`() {
        RandomULongGeneratorImpl().apply {
            repeat(10) {
                randomNumber().apply {
                    Regex("\\d{0,20}").matches(this)
                    assertThat(toULongOrNull()).isNotNull()
                }
            }
        }
    }
}
