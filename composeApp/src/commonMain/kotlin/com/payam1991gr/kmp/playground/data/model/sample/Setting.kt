package com.payam1991gr.kmp.playground.data.model.sample

import androidx.compose.runtime.Stable
import org.jetbrains.compose.resources.StringResource

@Stable
interface Setting {
    var value: Boolean
    val labelRes: StringResource
    fun toggle()
}
