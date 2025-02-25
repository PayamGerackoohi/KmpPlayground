package com.payam1991gr.kmp.playground.preview.screens.animations.animated.content

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContent
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContentScreen.State
import kotlinx.collections.immutable.persistentListOf

@SinglePreview
@Composable
fun AnimatedContent_Preview_Preview() = preview {
    AnimatedContent().Content(
        State(
            showCode = false,
            toolbarActions = persistentListOf(
                Action.Back,
                Action.Code,
            ),
        ) {},
        Modifier,
    )
}

@Composable
fun AnimatedContent_Code_Preview() = preview {
    AnimatedContent().Content(
        State(
            showCode = true,
            toolbarActions = persistentListOf(
                Action.Back,
                Action.Preview,
            ),
        ) {},
        Modifier,
    )
}
