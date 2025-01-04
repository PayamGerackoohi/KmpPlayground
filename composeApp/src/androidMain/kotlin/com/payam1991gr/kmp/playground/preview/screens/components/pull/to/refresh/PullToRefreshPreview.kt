package com.payam1991gr.kmp.playground.preview.screens.components.pull.to.refresh

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.components.pull.to.refresh.PullToRefresh
import com.payam1991gr.kmp.playground.ui.screens.components.pull.to.refresh.PullToRefreshScreen.State
import kotlinx.collections.immutable.persistentListOf

@Composable
fun PullToRefresh_Preview_Preview() = preview {
    PullToRefresh().Content(
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
fun PullToRefresh_Code_Preview() = preview {
    PullToRefresh().Content(
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
