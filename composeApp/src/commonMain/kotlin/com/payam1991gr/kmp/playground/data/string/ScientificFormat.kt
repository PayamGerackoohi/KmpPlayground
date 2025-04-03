package com.payam1991gr.kmp.playground.data.string

import com.payam1991gr.kmp.playground.view.isGerman
import kotlin.math.roundToInt

object ScientificFormat {
    class DataUtil(n: Int?, var power: Int, val isTruncatedPartFullyZero: Boolean) {
        val hasError = n == null
        var number = n ?: 0
    }

    /**
     * Turns positive integers into scientific form
     */
    fun String.toScientific(): String = (if (isGerman) "." else ",").let { delimiter ->
        replace(delimiter, "").trim()
    }.run result@{
        return if (all { it.isDigit() }) extractBase().run {
            if (hasError) return@result ""
            var precisionSign = "="
            if (number >= 100) {
                number = (number / 10f).roundToInt().also {
                    if (!isTruncatedPartFullyZero || it * 10 != number) precisionSign = "≈"
                }
                power++
            }
            var base = number
            var c = 1
            while (base >= 10) {
                base /= 10
                c *= 10
                power++
            }
            val b = number.toFloat() / c
            b.toString()
                .let { if (isGerman) it.replace('.', ',') else it }
                .let { "$precisionSign${it}e$power" }
        } else ""
    }

    private fun String.extractBase() = if (length < 3)
        DataUtil(toIntOrNull(), 0, true)
    else subSequence(3, length).let { rest ->
        DataUtil(substring(0, 3).toIntOrNull(), rest.length, rest.all { it == '0' })
    }
}
