package com.payam1991gr.kmp.playground.preview.screens.components.picker.datetime

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.DateTimePicker
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.DateTimePickerScreen.State
import kotlinx.collections.immutable.persistentListOf

@Composable
fun DateTimePicker_Preview_Preview() = preview {
    DateTimePicker().Content(
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
fun DateTimePicker_Code_Preview() = preview {
    DateTimePicker().Content(
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
