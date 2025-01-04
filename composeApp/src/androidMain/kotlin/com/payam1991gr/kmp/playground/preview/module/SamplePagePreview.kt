package com.payam1991gr.kmp.playground.preview.module

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.module.SamplePage
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import kmpplayground.composeapp.generated.resources.*
import kotlinx.collections.immutable.persistentListOf

@SinglePreview
@Composable
fun SamplePage_Preview_Preview() = preview {
    SamplePage(
        showCode = false,
        titleRes = Res.string.home_components,
        actions = persistentListOf(Action.Back, Action.Code),
        preview = {
            Text(
                text = "Sample Page - Preview",
                Modifier.align(Alignment.Center),
            )
        },
        code = {},
    )
}

@SinglePreview
@Composable
fun SamplePage_Code_Preview() = preview {
    SamplePage(
        showCode = true,
        titleRes = Res.string.home_components,
        actions = persistentListOf(Action.Back, Action.Preview),
        preview = {},
        code = {
            Text(
                text = "Sample Page - Code",
                Modifier.align(Alignment.Center),
            )
        },
    )
}
