package com.payam1991gr.kmp.playground.preview.screens.graphics.color.scheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.ColorScheme
import com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.ColorSchemeScreen.State
import kotlinx.collections.immutable.persistentListOf

@SinglePreview
//@SimpleDayNightPreview
@Composable
fun ColorScheme_Preview_Preview() = preview {
    ColorScheme().Content(
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
fun ColorScheme_Code_Preview() = preview {
    ColorScheme().Content(
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
