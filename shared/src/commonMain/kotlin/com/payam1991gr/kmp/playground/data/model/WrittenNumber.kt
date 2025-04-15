package com.payam1991gr.kmp.playground.data.model

import kotlinx.serialization.Serializable

@Serializable
data class WrittenNumber(val value: Int = 0, val string: String = "") {
    companion object {
        private val digits =
            arrayOf("Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine")
        private val tens =
            arrayOf("Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety")

        fun of(value: Int) = WrittenNumber(
            value, if (value !in 0..100) "Unsupported"
            else when (value) {
                in 0..<10 -> digits[value]
                10 -> "Ten"
                11 -> "Eleven"
                12 -> "Twelve"
                13 -> "Thirteen"
                15 -> "Fifteen"
                18 -> "Eighteen"
                in 10..<20 -> "${digits[value - 10]}teen"
                100 -> "One Hundred"
                else -> (value % 10).let { r ->
                    "${tens[value / 10 - 2]}${if (r == 0) "" else " ${digits[r]}"}"
                }
            }
        )
    }
}
