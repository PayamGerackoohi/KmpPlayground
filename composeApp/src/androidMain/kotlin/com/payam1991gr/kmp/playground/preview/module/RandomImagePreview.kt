package com.payam1991gr.kmp.playground.preview.module

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.module.RandomImage

@ScreensPreview
@Composable
fun RandomImage_Preview() = preview {
    LazyVerticalGrid(columns = GridCells.Adaptive(200.dp)) {
        items(60) { RandomImage(Modifier.aspectRatio(1f)) }
    }
}
