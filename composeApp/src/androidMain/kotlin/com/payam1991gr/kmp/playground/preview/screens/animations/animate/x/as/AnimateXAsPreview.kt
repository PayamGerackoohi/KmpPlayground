package com.payam1991gr.kmp.playground.preview.screens.animations.animate.x.`as`

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.AnimateXAs
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.AnimateXAsScreen.State
import kotlinx.collections.immutable.persistentListOf

@SinglePreview
@Composable
fun AnimateXAs_Preview_Preview() = preview {
    AnimateXAs().Content(
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
fun AnimateXAs_Code_Preview() = preview {
    AnimateXAs().Content(
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
