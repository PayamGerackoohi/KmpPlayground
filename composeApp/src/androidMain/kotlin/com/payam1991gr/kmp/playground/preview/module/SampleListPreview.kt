package com.payam1991gr.kmp.playground.preview.module

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.ui.module.SampleList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@SimpleDayNightPreview
@Composable
fun SampleListPreview() = preview {
    SampleList(
        samples = List(3) { "Item $it" }.toPersistentList(),
//        samples = persistentListOf(
//            "Item 1",
//            "Item 1 12313 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1",
//            "Item 1",
//            "Item 1 12313 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1 1",
//            "Item 1",
//            "Item 1",
//            ),
        nameOf = { it },
        onClick = {},
    )
}
