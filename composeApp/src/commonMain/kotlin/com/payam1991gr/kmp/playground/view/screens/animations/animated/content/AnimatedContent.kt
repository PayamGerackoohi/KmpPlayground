package com.payam1991gr.kmp.playground.view.screens.animations.animated.content

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.EmptySample
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.preview
import com.payam1991gr.kmp.playground.view.module.editor.codes
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContentScreen.State
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContentScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.sample.*
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*

typealias ACTScope<S> = AnimatedContentTransitionScope<S>

class AnimatedContent : Ui<State> {
    enum class ContentState { Foo, Bar, Baz }
    enum class NestedMenuState { Level1, Level2, Level3 }
    enum class CartState { Expanded, Collapsed }

    @Composable
    override fun Content(state: State, modifier: Modifier) = SamplePage(
        showCode = state.showCode,
        titleRes = Res.string.animations_animated_content,
        actions = state.toolbarActions,
        onClick = { state.event(Event.OnToolbarAction(it)) },
        preview = { Preview() },
        code = { Code() },
    )

    @Composable
    fun Preview() = preview(
        { SimpleSample() },
        { IncrementDecrementSample() },
        { TransitionSpecSample() },
        { TransitionExtensionSample() },
        { SlideIntoContainerSample() },
        { EmptySample() },
        gridCells = Module.cells,
    )

    @Composable
    fun Item(size: Dp, color: Color) = Box(Modifier.size(size).background(color))

    @Composable
    fun SmallItem() = Item(50.dp, Color.Magenta)

    @Composable
    fun CollapsedItem() = Item(100.dp, Color.Red)

    @Composable
    fun ExpandedItem() = Item(150.dp, Color.Blue)

    @Composable
    fun Code() = codes(
        Res.string.shared to { appendShared() },
        Res.string.animations_animated_content_simple to { appendSimpleSample() },
        Res.string.animations_animated_content_increment_decrement to { appendIncrementDecrementSample() },
        Res.string.animations_animated_content_transition_spec to { appendTransitionSpecSample() },
        Res.string.animations_animated_content_transition_extension to { appendTransitionExtensionSample() },
        Res.string.animations_animated_content_slide_into_container to { appendSlideIntoContainerSample() },
    )
}
