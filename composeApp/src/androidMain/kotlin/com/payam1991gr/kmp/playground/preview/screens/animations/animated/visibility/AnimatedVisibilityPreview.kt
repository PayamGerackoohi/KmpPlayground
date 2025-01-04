package com.payam1991gr.kmp.playground.preview.screens.animations.animated.visibility

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.visibility.AnimatedVisibility
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.visibility.AnimatedVisibilityScreen.State
import kotlinx.collections.immutable.persistentListOf

@Composable
fun AnimatedVisibility_Preview_Preview() = preview {
    AnimatedVisibility().Content(
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
fun AnimatedVisibility_Code_Preview() = preview {
    AnimatedVisibility().Content(
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
