package com.payam1991gr.kmp.playground.data.time

fun interface FrameGen {
    suspend fun frame(block: (Int) -> Unit)
}
