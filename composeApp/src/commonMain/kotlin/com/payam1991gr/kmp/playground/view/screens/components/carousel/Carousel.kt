package com.payam1991gr.kmp.playground.view.screens.components.carousel

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.RandomImage
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Description
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Header
import com.payam1991gr.kmp.playground.view.module.SamplePage.preview
import com.payam1991gr.kmp.playground.view.module.editor.CodePanel
import com.payam1991gr.kmp.playground.view.module.editor.rememberCodeEditor
import com.payam1991gr.kmp.playground.view.screens.components.carousel.CarouselScreen.State
import com.payam1991gr.kmp.playground.view.screens.components.carousel.CarouselScreen.State.Event
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

class Carousel : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) {
        SamplePage(
            showCode = state.showCode,
            titleRes = Res.string.components_carousel,
            actions = state.toolbarActions,
            onClick = { state.event(Event.OnToolbarAction(it)) },
            code = { Code() },
            preview = { Preview() }
        )
    }

    @Composable
    fun Preview() = preview(
        { Header("Horizontal Multi-Browse Carousel") },
        { Description(stringResource(Res.string.horizontal_multi_browse_carousel)) },
        { HorizontalMultiBrowseCarouselSample() },

        { Header("Horizontal Uncontained Carousel") },
        { Description(stringResource(Res.string.horizontal_uncontained_carousel)) },
        { HorizontalUncontainedCarouselSample() },
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HorizontalMultiBrowseCarouselSample() {
        HorizontalMultiBrowseCarousel(
            state = rememberCarouselState { 10 },
            preferredItemWidth = 200.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) { index ->
            RandomImage(
                contentDescription = "Image $index",
                modifier = Modifier.size(200.dp)
                    .maskClip(MaterialTheme.shapes.medium),
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HorizontalUncontainedCarouselSample() {
        HorizontalUncontainedCarousel(
            state = rememberCarouselState { 10 },
            itemWidth = 200.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp),
        ) { index ->
            RandomImage(
                contentDescription = "Image ${10 + index}",
                modifier = Modifier.size(200.dp)
                    .maskClip(MaterialTheme.shapes.medium),
            )
        }
    }

    @Composable
    fun Code() = CodePanel(
        rememberCodeEditor {
            appendHorizontalMultiBrowseCarouselSample()
            appendHorizontalUncontainedCarouselSample()
        }
    )
}
