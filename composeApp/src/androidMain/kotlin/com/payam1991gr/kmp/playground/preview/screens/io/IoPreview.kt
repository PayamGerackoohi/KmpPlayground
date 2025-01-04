package com.payam1991gr.kmp.playground.preview.screens.io

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.data.model.IoItem
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.screens.io.Io
import com.payam1991gr.kmp.playground.ui.screens.io.IoScreen.State
import kotlinx.collections.immutable.toPersistentList

@Composable
fun IoPreview() = preview {
    Io().Content(
        State(
            items = IoItem.entries.toPersistentList(),
        ) {},
        Modifier,
    )
}
