package com.payam1991gr.kmp.playground.preview.screens.graphics.icons

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.data.icon.IconsImpl
import com.payam1991gr.kmp.playground.data.model.LazyData
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.view.effectOf
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.Icons
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State
import kotlinx.collections.immutable.persistentListOf

private val icons by lazy { IconsImpl() }
private val coreIcons by lazy { icons.core() }
private val extendedIcons by lazy { LazyData.Data(icons.syncExtended()) }

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
            coreIcons = coreIcons,
            extendedIcons = extendedIcons,
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
