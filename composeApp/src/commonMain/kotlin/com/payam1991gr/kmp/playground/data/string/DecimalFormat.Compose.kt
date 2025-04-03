package com.payam1991gr.kmp.playground.data.string

import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

val decimalFormatTransformation = VisualTransformation {
    DecimalFormatImpl(it.text).let { format ->
        TransformedText(format.annotatedString, object : OffsetMapping {
            override fun originalToTransformed(offset: Int) = format.originalToTransformed(offset)
            override fun transformedToOriginal(offset: Int) = format.transformedToOriginal(offset)
        })
    }
}
