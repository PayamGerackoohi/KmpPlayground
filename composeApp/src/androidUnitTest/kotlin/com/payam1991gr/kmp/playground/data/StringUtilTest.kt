package com.payam1991gr.kmp.playground.data

import com.google.common.truth.Truth.*
import org.junit.Test

class StringUtilTest {
    @Test
    fun `toHeader - test`() {
        @Suppress("SpellCheckingInspection")
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

    @Test
    fun `is decimal - test`() {
        listOf(
            '0' to true,
            '1' to true,
            '2' to true,
            '3' to true,
            '4' to true,
            '5' to true,
            '6' to true,
            '7' to true,
            '8' to true,
            '9' to true,
            '-' to true,
            '+' to false,
            '.' to false,
            ',' to false,
            ' ' to false,
            'A' to false,
            'a' to false,
            '[' to false,
            ']' to false,
            '/' to false,
            '\\' to false,
        ).forEach { (input, output) ->
            assertThat(input.isDecimal()).isEqualTo(output)
        }
    }

    @Test
    fun `is float - test`() {
        listOf(
            '0' to true,
            '1' to true,
            '2' to true,
            '3' to true,
            '4' to true,
            '5' to true,
            '6' to true,
            '7' to true,
            '8' to true,
            '9' to true,
            '.' to true,
            '-' to true,
            '+' to false,
            ',' to false,
            ' ' to false,
            'A' to false,
            'a' to false,
            '[' to false,
            ']' to false,
            '/' to false,
            '\\' to false,
        ).forEach { (input, output) ->
            assertThat(input.isFloat()).isEqualTo(output)
        }
    }

    @Test
    fun `is decimal array - test`() {
        listOf(
            '0' to true,
            '1' to true,
            '2' to true,
            '3' to true,
            '4' to true,
            '5' to true,
            '6' to true,
            '7' to true,
            '8' to true,
            '9' to true,
            ',' to true,
            '-' to true,
            '+' to false,
            '.' to false,
            ' ' to false,
            'A' to false,
            'a' to false,
            '[' to false,
            ']' to false,
            '/' to false,
            '\\' to false,
        ).forEach { (input, output) ->
            assertWithMessage("'$input'.isDecimalArray() = $output")
                .that(input.isDecimalArray())
                .isEqualTo(output)
        }
    }

    @Test
    fun `merge - test`() {
        val base = "Base"
        @Suppress("SpellCheckingInspection")
        listOf(
            "" to "Base",
            "a" to "Base.a",
            " a " to "Base.a",
            "ment" to "Base.ment",
        ).forEach { (input, output) ->
            assertThat(base merge input).isEqualTo(output)
        }
    }
}
