package com.payam1991gr.kmp.playground.data

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
