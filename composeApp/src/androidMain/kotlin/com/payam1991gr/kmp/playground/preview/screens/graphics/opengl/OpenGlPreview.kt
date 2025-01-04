package com.payam1991gr.kmp.playground.preview.screens.graphics.opengl

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.graphics.opengl.OpenGl
import com.payam1991gr.kmp.playground.ui.screens.graphics.opengl.OpenGlScreen.State
import kotlinx.collections.immutable.persistentListOf

@Composable
fun OpenGl_Preview_Preview() = preview {
    OpenGl().Content(
        State(
            showCode = false,
            toolbarActions = persistentListOf(
                Action.Back,
                Action.Preview,
            ),
        ) {},
        Modifier,
    )
}

@Composable
fun OpenGl_Code_Preview() = preview {
    OpenGl().Content(
        State(
            showCode = true,
            toolbarActions = persistentListOf(
                Action.Back,
                Action.Code,
            ),
        ) {},
        Modifier,
    )
}
