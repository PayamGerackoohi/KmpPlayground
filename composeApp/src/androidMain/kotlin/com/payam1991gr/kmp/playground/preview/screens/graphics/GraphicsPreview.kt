package com.payam1991gr.kmp.playground.preview.screens.graphics

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.data.model.GraphicItem
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.screens.graphics.Graphics
import com.payam1991gr.kmp.playground.ui.screens.graphics.GraphicsScreen.State
import kotlinx.collections.immutable.toPersistentList

@Composable
fun GraphicsPreview() = preview {
    Graphics().Content(
        State(
            items = GraphicItem.entries.toPersistentList(),
        ) {},
        Modifier,
    )
}
