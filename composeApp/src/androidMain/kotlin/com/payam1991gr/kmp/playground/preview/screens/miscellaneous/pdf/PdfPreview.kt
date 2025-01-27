package com.payam1991gr.kmp.playground.preview.screens.miscellaneous.pdf

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.pdf.Pdf
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.pdf.PdfScreen.State
import kotlinx.collections.immutable.persistentListOf

@Composable
fun Pdf_Preview_Preview() = preview {
    Pdf().Content(
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
fun Pdf_Code_Preview() = preview {
    Pdf().Content(
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
