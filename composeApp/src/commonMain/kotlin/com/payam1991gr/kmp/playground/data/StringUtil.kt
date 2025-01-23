package com.payam1991gr.kmp.playground.data

fun String.toHeader(): String = mapIndexed { index, it ->
    if (index == 0) if (it.isLowerCase()) it.uppercase() else it
    else if (it.isLowerCase() || it == ' ') it
    else " $it"
}.joinToString("")

fun Any?.box() = this?.toString() ?: "☐"
