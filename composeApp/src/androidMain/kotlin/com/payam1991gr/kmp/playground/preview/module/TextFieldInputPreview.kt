package com.payam1991gr.kmp.playground.preview.module

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.data.model.textfield.IntConverter
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.view.module.TextFieldInput
import com.payam1991gr.kmp.playground.view.theme.KmpPlaygroundTheme

@SinglePreview
@Composable
fun TextFieldInputPreview() = KmpPlaygroundTheme {
    TextFieldInput(
        title = "Title",
        initialData = 123,
        converter = IntConverter(),
        modifier = Modifier.padding(16.dp)
    ) {}
}
