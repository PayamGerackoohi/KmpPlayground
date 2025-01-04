package com.payam1991gr.kmp.playground.preview.screens.animations.crossfade

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.animations.crossfade.Crossfade
import com.payam1991gr.kmp.playground.ui.screens.animations.crossfade.CrossfadeScreen.State
import kotlinx.collections.immutable.persistentListOf

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
