package com.payam1991gr.kmp.playground.preview.screens.graphics.charts

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.graphics.charts.Charts
import com.payam1991gr.kmp.playground.view.screens.graphics.charts.ChartsScreen.State
import kotlinx.collections.immutable.persistentListOf

@Composable
fun Charts_Preview_Preview() = preview {
    Charts().Content(
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
fun Charts_Code_Preview() = preview {
    Charts().Content(
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
