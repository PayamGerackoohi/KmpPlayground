package com.payam1991gr.kmp.playground.preview.screens.io.database

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.io.database.Database
import com.payam1991gr.kmp.playground.ui.screens.io.database.DatabaseScreen.State
import kotlinx.collections.immutable.persistentListOf

@Composable
fun Database_Preview_Preview() = preview {
    Database().Content(
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
fun Database_Code_Preview() = preview {
    Database().Content(
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
