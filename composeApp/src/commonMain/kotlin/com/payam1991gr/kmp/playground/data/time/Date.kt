package com.payam1991gr.kmp.playground.data.time

import androidx.compose.runtime.Stable

@Stable
interface Date {
    var ms: Long?
    val text: String?
}
