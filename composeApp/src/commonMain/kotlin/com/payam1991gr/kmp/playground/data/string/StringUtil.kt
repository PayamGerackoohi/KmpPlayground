package com.payam1991gr.kmp.playground.data.string

import com.payam1991gr.kmp.playground.view.isGerman

fun String.toHeader(): String = mapIndexed { index, it ->
    if (index == 0) if (it.isLowerCase()) it.uppercase() else it
    else if (it.isLowerCase() || it == ' ') it
    else " $it"
}.joinToString("")

@Suppress("IfThenToElvis") // to make kover happy
fun Any?.box() = if (this == null) "☐" else toString()

fun Char.isDecimal() = isDigit() || this == '-'
fun Char.isFloat() = isDecimal() || this == '.'
fun Char.isDecimalArray() = isDecimal() || this == ','

infix fun String.merge(other: String) = if (other.isBlank()) this else "$this.${other.trim()}"

fun String.decimalFormat(
    delimiter: String = if (isGerman) "." else ",",
): String = (length % 3).let { offset ->
    if (offset == 0) chunked(3)
    else listOf(substring(0..<offset)) + substring(startIndex = offset).chunked(3)
}.joinToString(separator = delimiter)
