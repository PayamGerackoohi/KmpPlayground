package com.payam1991gr.kmp.playground.preview.screens.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.Dialog
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogScreen.State
import kotlinx.collections.immutable.persistentListOf

@SinglePreview
//@SimpleDayNightPreview
@Composable
fun Dialog_Preview_Preview() = preview {
    Dialog().Content(
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

//@SinglePreview
@Composable
fun Dialog_Code_Preview() = preview {
    Dialog().Content(
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
