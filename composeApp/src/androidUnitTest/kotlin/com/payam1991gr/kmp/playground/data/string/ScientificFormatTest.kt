package com.payam1991gr.kmp.playground.data.string

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.string.ScientificFormat.toScientific
import org.junit.Test

class ScientificFormatTest {
    @Test
    fun `toScientific - test`() {
        listOf(
            "" to "",
            "asdf" to "",
            "-1" to "",
            "-1000" to "",
            "0" to "=0.0e0",
            "1" to "=1.0e0",
            "10" to "=1.0e1",
            "100" to "=1.0e2",
            "1000" to "=1.0e3",
            "9" to "=9.0e0",
            "99" to "=9.9e1",
            "999" to "≈1.0e3",
            "9999" to "≈1.0e4",
            "9,999" to "≈1.0e4",
            "123" to "≈1.2e2",
            "123,456" to "≈1.2e5",
            "234,567" to "≈2.3e5",
            "345,678" to "≈3.5e5",
            "456,789" to "≈4.6e5",
            "567,890" to "≈5.7e5",
            "678,901" to "≈6.8e5",
            "789,012" to "≈7.9e5",
            "890,123" to "≈8.9e5",
            " 1,000  " to "=1.0e3",
            "1,111,111,111,111,111,111,111,111,111,111,111,111,111,111,111,111,111,111,111,111" to "≈1.1e60",
            "1,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000,000" to "=1.0e60",
            "9,999,999,999,999,999,999,999,999,999,999,999,999,999,999,999,999,999,999,999,999" to "≈1.0e61",
        ).forEach { (input, output) ->
//            assertWithMessage("$input.toScientific()")
            assertThat(input.toScientific()).isEqualTo(output)
        }
    }
}
