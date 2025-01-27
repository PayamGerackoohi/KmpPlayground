package com.payam1991gr.kmp.playground.preview.screens.animations

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.data.model.AnimationItem
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.screens.animations.Animations
import com.payam1991gr.kmp.playground.view.screens.animations.AnimationsScreen.State
import kotlinx.collections.immutable.toPersistentList

@Composable
fun AnimationsPreview() = preview {
    Animations().Content(
        State(
            AnimationItem.entries.toPersistentList(),
        ) {},
        Modifier,
    )
}
