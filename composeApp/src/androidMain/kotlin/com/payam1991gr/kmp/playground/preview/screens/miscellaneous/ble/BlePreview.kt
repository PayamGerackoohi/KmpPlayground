package com.payam1991gr.kmp.playground.preview.screens.miscellaneous.ble

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.ble.Ble
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.ble.BleScreen.State
import kotlinx.collections.immutable.persistentListOf

@Composable
fun Ble_Preview_Preview() = preview {
    Ble().Content(
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
fun Ble_Code_Preview() = preview {
    Ble().Content(
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
