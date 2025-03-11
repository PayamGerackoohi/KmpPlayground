package com.payam1991gr.kmp.playground.data.time

import androidx.compose.animation.core.withInfiniteAnimationFrameMillis
import androidx.compose.ui.util.fastRoundToInt

fun frameGenImpl() = FrameGen { block ->
    withInfiniteAnimationFrameMillis {
        block((it / 1000f).fastRoundToInt())
    }
}
