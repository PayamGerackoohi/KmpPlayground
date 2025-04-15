package com.payam1991gr.kmp.playground.preview.screens.io.api

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.data.model.RemoteData.Data
import com.payam1991gr.kmp.playground.data.model.RemoteData.Error
import com.payam1991gr.kmp.playground.data.model.RemoteData.Init
import com.payam1991gr.kmp.playground.data.model.WrittenNumber
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.io.api.Api
import com.payam1991gr.kmp.playground.view.screens.io.api.ApiScreen.State
import io.ktor.http.HttpStatusCode
import kotlinx.collections.immutable.persistentListOf

@SinglePreview
@Composable
fun Api_Preview_Preview() = preview {
    Api().Content(
        State(
            showCode = false,
            toolbarActions = persistentListOf(Action.Back, Action.Preview),
            true,
            "http://server.com",
            Error(HttpStatusCode.BadGateway),
            Data(List(4) { WrittenNumber.of(it) }, HttpStatusCode.OK.value),
        ) {},
        Modifier,
    )
}

@Composable
fun Api_Code_Preview() = preview {
    Api().Content(
        State(
            showCode = true,
            toolbarActions = persistentListOf(Action.Back, Action.Code),
            true,
            "",
            Init,
            Init,
        ) {},
        Modifier,
    )
}
