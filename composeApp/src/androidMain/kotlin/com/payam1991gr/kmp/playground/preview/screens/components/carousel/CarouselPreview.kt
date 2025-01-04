package com.payam1991gr.kmp.playground.preview.screens.components.carousel

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.preview
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.components.carousel.Carousel
import com.payam1991gr.kmp.playground.ui.screens.components.carousel.CarouselScreen.State
import kotlinx.collections.immutable.persistentListOf

@SinglePreview
@Composable
fun Carousel_Preview_Preview() = preview {
    Carousel().Content(
        State(
            false,
            persistentListOf(
                Action.Back,
                Action.Code,
            ),
        ) {},
        Modifier,
    )
}

//@SinglePreview
//@SimpleDayNightPreview
@Composable
fun Carousel_Code_Preview() = preview {
    Carousel().Content(
        State(
            true,
            persistentListOf(
                Action.Back,
                Action.Preview,
            ),
        ) {},
        Modifier,
    )
}
