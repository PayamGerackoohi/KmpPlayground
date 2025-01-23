package com.payam1991gr.kmp.playground.data

import com.google.common.truth.Truth.*
import org.junit.Test

class StringUtilTest {
    @Test
    fun `toHeader - test`() {
        listOf(
            "" to "",
            "abcd" to "Abcd",
            "aQuick" to "A Quick",
            "QuickFox" to "Quick Fox",
            "aQuickBrownFox" to "A Quick Brown Fox",
            "AQuickBrownFox" to "A Quick Brown Fox",
            " AQuickBrown Fox " to "  A Quick Brown  Fox ",
        ).forEach { (input, output) ->
            assertThat(input.toHeader()).isEqualTo(output)
        }
    }

    @Test
    fun `box - test`() {
        listOf(
            null to "☐",
            "" to "",
            "asdf" to "asdf",
        ).forEach { (input, output) ->
            assertThat(input.box()).isEqualTo(output)
        }
    }
}
