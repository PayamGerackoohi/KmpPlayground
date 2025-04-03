package com.payam1991gr.kmp.playground.preview.module

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.data.math.Factor
import com.payam1991gr.kmp.playground.preview.SinglePreview
import com.payam1991gr.kmp.playground.view.module.MathFactors

@SinglePreview
@Composable
fun MathFactors_Preview() {
    MathFactors(Factor.parse("2*3^2*5*7^4"))
}
