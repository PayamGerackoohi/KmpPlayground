package com.payam1991gr.kmp.playground.data.time

import kotlinx.datetime.LocalTime

fun Int.toPaddedString() = toString().padStart(2, '0')

fun LocalTime.isDay(): Boolean = hour in 6..<18

fun LocalTime.isNight(): Boolean = !isDay()
