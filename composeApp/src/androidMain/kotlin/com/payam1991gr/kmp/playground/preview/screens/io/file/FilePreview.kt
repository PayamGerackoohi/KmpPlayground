package com.payam1991gr.kmp.playground.preview.screens.io.file

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.io.file.File
import com.payam1991gr.kmp.playground.view.screens.io.file.FileScreen.State
import kotlinx.collections.immutable.persistentListOf

@Composable
fun File_Preview_Preview() = preview {
    File().Content(
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
fun File_Code_Preview() = preview {
    File().Content(
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
