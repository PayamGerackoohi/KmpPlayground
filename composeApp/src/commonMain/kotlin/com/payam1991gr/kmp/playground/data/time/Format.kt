package com.payam1991gr.kmp.playground.data.time

import kotlinx.datetime.LocalTime
import kotlinx.datetime.format.char

val timeFormat = LocalTime.Format {
    hour(); char(':'); minute(); char(':'); second()
}
