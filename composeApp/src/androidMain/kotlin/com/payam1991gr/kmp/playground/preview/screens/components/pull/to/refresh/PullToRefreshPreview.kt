package com.payam1991gr.kmp.playground.preview.screens.components.pull.to.refresh

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefresh
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefreshScreen.State
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.state.rememberPtrState
import kotlinx.collections.immutable.persistentListOf

@OptIn(ExperimentalMaterial3Api::class)
@SinglePreview
@Composable
fun PullToRefresh_Preview_Preview() = preview {
    PullToRefresh().Content(
        State(
            showCode = false,
            toolbarActions = persistentListOf(
                Action.Back,
                Action.Preview,
            ),
            linearProgressIndicator = rememberPtrState(),
            pullToRefreshBox = rememberPtrState(),
            scaling = rememberPtrState(),
            customBehavior = rememberPtrState(),
        ) {},
        Modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PullToRefresh_Code_Preview() = preview {
    PullToRefresh().Content(
        State(
            showCode = true,
            toolbarActions = persistentListOf(
                Action.Back,
                Action.Code,
            ),
            linearProgressIndicator = rememberPtrState(),
            pullToRefreshBox = rememberPtrState(),
            scaling = rememberPtrState(),
            customBehavior = rememberPtrState(),
        ) {},
        Modifier,
    )
}
