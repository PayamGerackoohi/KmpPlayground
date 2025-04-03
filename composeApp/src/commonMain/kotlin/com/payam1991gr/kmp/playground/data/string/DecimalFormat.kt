package com.payam1991gr.kmp.playground.data.string

import androidx.compose.ui.text.AnnotatedString

interface DecimalFormat {
    val annotatedString: AnnotatedString
    fun originalToTransformed(offset: Int): Int
    fun transformedToOriginal(offset: Int): Int
}
