package com.payam1991gr.kmp.playground.preview.screens.animations.crossfade

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.SinglePreview
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.animations.crossfade.Crossfade
import com.payam1991gr.kmp.playground.view.screens.animations.crossfade.CrossfadeScreen.State
import kotlinx.collections.immutable.persistentListOf

@SinglePreview
@Composable
fun Crossfade_Preview_Preview() = preview {
    Crossfade().Content(
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
fun Crossfade_Code_Preview() = preview {
    Crossfade().Content(
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
