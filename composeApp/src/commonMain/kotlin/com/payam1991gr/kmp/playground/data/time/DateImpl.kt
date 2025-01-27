package com.payam1991gr.kmp.playground.data.time

import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf

@Stable
class DateImpl(initialTimestamp: Long? = null) : Date {
    // override var ms by mutableStateOf(initialTimestamp) // to make kover happy
    private var _ms = mutableStateOf(initialTimestamp)
    override var ms
        get() = _ms.value
        set(value) {
            _ms.value = value
        }

    // override val text by derivedStateOf { ms.toString() } // to make kover happy
    private val _text = derivedStateOf { ms.toDate() }
    override val text get() = _text.value

    override fun toString(): String = listOf(
        "ms=$ms",
        "text=$text",
    ).joinToString(", ", "Date(", ")")
}
