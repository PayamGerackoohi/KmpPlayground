package com.payam1991gr.kmp.playground.data.model

import com.google.common.truth.Truth.*
import org.junit.Test

class WrittenNumberTest {
    @Test
    fun `of - test`() {
        listOf(
            WrittenNumber(-1, "Unsupported"),
            WrittenNumber(101, "Unsupported"),
            WrittenNumber(0, "Zero"),
            WrittenNumber(1, "One"),
            WrittenNumber(2, "Two"),
            WrittenNumber(3, "Three"),
            WrittenNumber(4, "Four"),
            WrittenNumber(5, "Five"),
            WrittenNumber(6, "Six"),
            WrittenNumber(7, "Seven"),
            WrittenNumber(8, "Eight"),
            WrittenNumber(9, "Nine"),
            WrittenNumber(10, "Ten"),
            WrittenNumber(11, "Eleven"),
            WrittenNumber(12, "Twelve"),
            WrittenNumber(13, "Thirteen"),
            WrittenNumber(14, "Fourteen"),
            WrittenNumber(15, "Fifteen"),
            WrittenNumber(16, "Sixteen"),
            WrittenNumber(17, "Seventeen"),
            WrittenNumber(18, "Eighteen"),
            WrittenNumber(19, "Nineteen"),
            WrittenNumber(20, "Twenty"),
            WrittenNumber(21, "Twenty One"),
            WrittenNumber(22, "Twenty Two"),
            WrittenNumber(23, "Twenty Three"),
            WrittenNumber(24, "Twenty Four"),
            WrittenNumber(25, "Twenty Five"),
            WrittenNumber(26, "Twenty Six"),
            WrittenNumber(27, "Twenty Seven"),
            WrittenNumber(28, "Twenty Eight"),
            WrittenNumber(29, "Twenty Nine"),
            WrittenNumber(30, "Thirty"),
            WrittenNumber(31, "Thirty One"),
            WrittenNumber(40, "Forty"),
            WrittenNumber(41, "Forty One"),
            WrittenNumber(50, "Fifty"),
            WrittenNumber(51, "Fifty One"),
            WrittenNumber(60, "Sixty"),
            WrittenNumber(61, "Sixty One"),
            WrittenNumber(70, "Seventy"),
            WrittenNumber(71, "Seventy One"),
            WrittenNumber(80, "Eighty"),
            WrittenNumber(81, "Eighty One"),
            WrittenNumber(90, "Ninety"),
            WrittenNumber(91, "Ninety One"),
            WrittenNumber(100, "One Hundred"),
        ).forEach {
            assertThat(WrittenNumber.of(it.value)).isEqualTo(it)
        }
    }
}
