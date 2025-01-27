package com.payam1991gr.kmp.playground.preview.screens.io.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.io.api.Api
import com.payam1991gr.kmp.playground.view.screens.io.api.ApiScreen.State
import kotlinx.collections.immutable.persistentListOf

@Composable
fun Api_Preview_Preview() = preview {
    Api().Content(
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
fun Api_Code_Preview() = preview {
    Api().Content(
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
