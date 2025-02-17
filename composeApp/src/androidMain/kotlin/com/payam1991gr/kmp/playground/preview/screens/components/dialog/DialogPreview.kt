package com.payam1991gr.kmp.playground.preview.screens.components.dialog

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.components.dialog.Dialog
import com.payam1991gr.kmp.playground.view.screens.components.dialog.DialogScreen.State
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.state.rememberDatePickerState
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
            rememberDatePickerState(),
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
            rememberDatePickerState(),
        ) {},
        Modifier,
    )
}
