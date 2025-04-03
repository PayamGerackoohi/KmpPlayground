package com.payam1991gr.kmp.playground.data

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.string.box
import com.payam1991gr.kmp.playground.data.string.decimalFormat
import com.payam1991gr.kmp.playground.data.string.isDecimal
import com.payam1991gr.kmp.playground.data.string.isDecimalArray
import com.payam1991gr.kmp.playground.data.string.isFloat
import com.payam1991gr.kmp.playground.data.string.merge
import com.payam1991gr.kmp.playground.data.string.toHeader
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

    @Test
    fun `decimalFormat - test`() {
        listOf(
            "" to "",
            "1" to "1",
            "12" to "12",
            "123" to "123",
            "1234" to "1,234",
            "12345" to "12,345",
            "123456" to "123,456",
            "1234567" to "1,234,567",
            "12345678" to "12,345,678",
            "123456789" to "123,456,789",
            "1234567890" to "1,234,567,890",
            "12345678901" to "12,345,678,901",
            "123456789012" to "123,456,789,012",
            "1234567890123" to "1,234,567,890,123",
            "12345678901234" to "12,345,678,901,234",
        ).forEach { (input, output) ->
            assertWithMessage("${input}.decimalFormat() = $output")
                .that(input.decimalFormat())
                .isEqualTo(output)
        }
    }
}
