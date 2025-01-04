package com.payam1991gr.kmp.playground.preview.screens.io.datastore

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.io.datastore.Datastore
import com.payam1991gr.kmp.playground.ui.screens.io.datastore.DatastoreScreen.State
import kotlinx.collections.immutable.persistentListOf

@Composable
fun Datastore_Preview_Preview() = preview {
    Datastore().Content(
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
fun Datastore_Code_Preview() = preview {
    Datastore().Content(
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
