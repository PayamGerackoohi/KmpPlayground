package com.payam1991gr.kmp.playground.data.string

import com.google.common.truth.Truth.*
import org.junit.Test

class DecimalFormatTest {
    @Test
    fun `two way transformation - test`() {
        listOf(
            DecimalFormatImpl("") to listOf(
                0 to 0,
            ),
            DecimalFormatImpl("1") to listOf(
                0 to 0,
                1 to 1,
            ),
            DecimalFormatImpl("12") to listOf(
                0 to 0,
                1 to 1,
                2 to 2,
            ),
            DecimalFormatImpl("123") to listOf(
                0 to 0,
                1 to 1,
                2 to 2,
                3 to 3,
            ),
            DecimalFormatImpl("1234" /* 1,234 */) to listOf(
                0 to 0,
                1 to 2,
                2 to 3,
                3 to 4,
                4 to 5,
            ),
            DecimalFormatImpl("12345" /* 12,345 */) to listOf(
                0 to 0,
                1 to 1,
                2 to 3,
                3 to 4,
                4 to 5,
                5 to 6,
            ),
            DecimalFormatImpl("123456" /* 123,456 */) to listOf(
                0 to 0,
                1 to 1,
                2 to 2,
                3 to 4,
                4 to 5,
                5 to 6,
                6 to 7,
            ),
            DecimalFormatImpl("1234567" /* 1,234,567 */) to listOf(
                0 to 0,
                1 to 2,
                2 to 3,
                3 to 4,
                4 to 6,
                5 to 7,
                6 to 8,
                7 to 9,
            ),
            DecimalFormatImpl("12345678" /* 12,345,678 */) to listOf(
                0 to 0,
                1 to 1,
                2 to 3,
                3 to 4,
                4 to 5,
                5 to 7,
                6 to 8,
                7 to 9,
                8 to 10,
            ),
            DecimalFormatImpl("123456789" /* 123,456,789 */) to listOf(
                0 to 0,
                1 to 1,
                2 to 2,
                3 to 4,
                4 to 5,
                5 to 6,
                6 to 8,
                7 to 9,
                8 to 10,
                9 to 11,
            ),
            DecimalFormatImpl("1234567890" /* 1,234,567,890 */) to listOf(
                0 to 0,
                1 to 2,
                2 to 3,
                3 to 4,
                4 to 6,
                5 to 7,
                6 to 8,
                7 to 10,
                8 to 11,
                9 to 12,
                10 to 13,
            ),
            DecimalFormatImpl("12345678901" /* 12,345,678,901 */) to listOf(
                0 to 0,
                1 to 1,
                2 to 3,
                3 to 4,
                4 to 5,
                5 to 7,
                6 to 8,
                7 to 9,
                8 to 11,
                9 to 12,
                10 to 13,
                11 to 14,
            ),
        ).forEach { (format, cases) ->
            cases.forEach { (input, output) ->
//                assertWithMessage("${format.annotatedString}, $input -> $output")
                assertThat(format.originalToTransformed(input)).isEqualTo(output)
                assertThat(format.transformedToOriginal(output)).isEqualTo(input)
            }
        }
    }
}
