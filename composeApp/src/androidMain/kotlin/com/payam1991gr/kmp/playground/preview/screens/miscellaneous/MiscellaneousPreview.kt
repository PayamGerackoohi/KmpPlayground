package com.payam1991gr.kmp.playground.preview.screens.miscellaneous

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.data.model.MiscellaneousItem
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.Miscellaneous
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.MiscellaneousScreen.State
import kotlinx.collections.immutable.toPersistentList

@Composable
fun MiscellaneousPreview() = preview {
    Miscellaneous().Content(
        State(
            items = MiscellaneousItem.entries.toPersistentList(),
        ) {},
        Modifier,
    )
}
