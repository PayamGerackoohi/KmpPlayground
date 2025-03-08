package com.payam1991gr.kmp.playground.preview.screens.io.datastore

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.io.datastore.Datastore
import com.payam1991gr.kmp.playground.view.screens.io.datastore.DatastoreScreen.State
import kotlinx.collections.immutable.persistentListOf

@SinglePreview
@Composable
fun Datastore_Preview_Preview() = preview {
    Datastore().Content(
        State(
            showCode = false,
            toolbarActions = persistentListOf(
                Action.Back,
                Action.Preview,
            ),
            int = 1,
            float = 2f,
            byteArray = byteArrayOf(3, 4),
        ) {},
        Modifier,
    )
}

@Composable
fun Datastore_Code_Preview() = preview {
    Datastore().Content(
        State(
            showCode = true,
            toolbarActions = persistentListOf(
                Action.Back,
                Action.Code,
            ),
            int = 0,
            float = 0f,
            byteArray = byteArrayOf(),
        ) {},
        Modifier,
    )
}
