package com.payam1991gr.kmp.playground.ui.screens.components.carousel

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.HorizontalUncontainedCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.ui.module.RandomImage
import com.payam1991gr.kmp.playground.ui.module.SamplePage
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Description
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Header
import com.payam1991gr.kmp.playground.ui.module.editor.CodePanel
import com.payam1991gr.kmp.playground.ui.module.editor.rememberCodeEditor
import com.payam1991gr.kmp.playground.ui.screens.components.carousel.CarouselScreen.State
import com.payam1991gr.kmp.playground.ui.screens.components.carousel.CarouselScreen.State.Event
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
    fun Preview() {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
        ) {
            item { Header("Horizontal Multi-Browse Carousel") }
            item { Description(stringResource(Res.string.horizontal_multi_browse_carousel)) }
            item { HorizontalMultiBrowseCarouselSample() }
            item { Header("Horizontal Uncontained Carousel") }
            item { Description(stringResource(Res.string.horizontal_uncontained_carousel)) }
            item { HorizontalUncontainedCarouselSample() }
        }
    }

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
            line()
            appendHorizontalUncontainedCarouselSample()
            line()
        }
    )
}
