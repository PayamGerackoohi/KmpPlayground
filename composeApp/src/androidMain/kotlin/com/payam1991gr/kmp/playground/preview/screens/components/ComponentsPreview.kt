package com.payam1991gr.kmp.playground.preview.screens.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.payam1991gr.kmp.playground.data.model.Component
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.screens.components.Components
import com.payam1991gr.kmp.playground.ui.screens.components.ComponentsScreen.State
import kotlinx.collections.immutable.persistentListOf

//@SinglePreview
//@LocalesPreview
//@Preview(name = "German", locale = "de", showBackground = true, apiLevel = 33)
//@LocalesPreview
@ScreensPreview
//@Preview(
//    name = "1-Phone-ExtraSmall-Portrait",
//    device = "id:2.7in QVGA",
//    showBackground = true,
//    apiLevel = 33,
//)
@Composable
fun ComponentsPreview() = preview {
    Components().Content(
        State(
            components = persistentListOf(
                Component.Carousel,
                Component.PullToRefresh,
                Component.Dialog,
                Component.DateTimePicker,
            )
        ) {},
        Modifier,
    )
}
