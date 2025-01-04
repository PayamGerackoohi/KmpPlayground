package com.payam1991gr.kmp.playground.preview.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.data.model.HomeItem
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.screens.home.Home
import com.payam1991gr.kmp.playground.ui.screens.home.HomeScreen.State
import kotlinx.collections.immutable.toPersistentList

//@SinglePreview
//@LocalesPreview
@ScreensPreview
//@SimpleDayNightPreview
@Composable
fun Home_Preview() = preview {
    Home().Content(
        state = State(
            items = HomeItem.entries.toPersistentList()
        ) {},
        modifier = Modifier,
    )
}
