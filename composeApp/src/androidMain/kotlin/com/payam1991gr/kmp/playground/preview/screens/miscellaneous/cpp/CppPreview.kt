package com.payam1991gr.kmp.playground.preview.screens.miscellaneous.cpp

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.cpp.Cpp
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.cpp.CppScreen.State
import kotlinx.collections.immutable.persistentListOf

@Composable
fun Cpp_Preview_Preview() = preview {
    Cpp().Content(
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
fun Cpp_Code_Preview() = preview {
    Cpp().Content(
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
