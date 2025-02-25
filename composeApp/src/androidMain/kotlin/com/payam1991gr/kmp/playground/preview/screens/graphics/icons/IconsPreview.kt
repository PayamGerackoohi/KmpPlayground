package com.payam1991gr.kmp.playground.preview.screens.graphics.icons

import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.data.model.IconData
import com.payam1991gr.kmp.playground.data.model.LazyData
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.effectOf
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.Icons
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State
import kotlinx.collections.immutable.persistentListOf

@Suppress("SpellCheckingInspection")
@SinglePreview
@Composable
fun Icons_Preview_Preview() = preview {
    Icons().Content(
        State(
            showCode = false,
            toolbarActions = persistentListOf(
                Action.Back,
                Action.Code,
            ),
            coreIcons = persistentListOf(
                IconData("AccountBox", Default.AccountBox),
                IconData("AccountCircle", Default.AccountCircle),
                IconData("AddCircle", Default.AddCircle),
                IconData("Add", Default.Add),
            ),
            extendedIcons = LazyData.Data(
                persistentListOf(
                    IconData("_1k", Default._1k),
                    IconData("_1kPlus", Default._1kPlus),
                    IconData("_1xMobiledata", Default._1xMobiledata),
                    IconData("_2k", Default._2k),
                )
            ),
            effectOf(),
        ) {},
        Modifier,
    )
}

@Composable
fun Icons_Code_Preview() = preview {
    Icons().Content(
        State(
            showCode = true,
            toolbarActions = persistentListOf(
                Action.Back,
                Action.Preview,
            ),
            coreIcons = persistentListOf(),
            extendedIcons = LazyData.Loading,
            effectOf(),
        ) {},
        Modifier,
    )
}
