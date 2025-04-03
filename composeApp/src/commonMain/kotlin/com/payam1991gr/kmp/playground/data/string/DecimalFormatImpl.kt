package com.payam1991gr.kmp.playground.data.string

import androidx.compose.ui.text.AnnotatedString

class DecimalFormatImpl(text: String) : DecimalFormat {
    private val size = text.length
    private val remainder = size % 3
    private val shift3 = shift(3)
    private val shift4 = shift(4)
    private val lastTransformedOffset = originalToTransformed(size)
    override val annotatedString = AnnotatedString(text.decimalFormat())

    private fun shift(base: Int) = if (remainder == 0) 0 else base - remainder

    override fun originalToTransformed(offset: Int): Int =
        if (offset == size) originalToTransformed(offset - 1) + 1
        else offset + (offset + shift3) / 3

    override fun transformedToOriginal(offset: Int): Int =
        if (offset == lastTransformedOffset) size
        else offset - (offset + shift4) / 4
}
