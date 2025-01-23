package com.payam1991gr.kmp.playground.data.time

import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

@Stable
class DateImpl(initialTimestamp: Long? = null) : Date {
    override var ms by mutableStateOf(initialTimestamp)
    override val text by derivedStateOf { ms.toDate() }

    override fun toString(): String = listOf(
        "ms=$ms",
        "text=$text",
    ).joinToString(", ", "Date(", ")")
}
