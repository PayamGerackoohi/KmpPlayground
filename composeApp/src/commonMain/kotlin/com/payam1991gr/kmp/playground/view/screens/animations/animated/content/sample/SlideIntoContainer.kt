package com.payam1991gr.kmp.playground.view.screens.animations.animated.content.sample

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.ACTScope
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContent
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContent.NestedMenuState
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedContent.SlideIntoContainerSample() {
    var state by remember { mutableStateOf(NestedMenuState.Level1) }
    val tag = stringResource(Res.string.animations_animated_content_slide_into_container)
    Module(tag, {
        state = when (state) {
            NestedMenuState.Level1 -> NestedMenuState.Level2
            NestedMenuState.Level2 -> NestedMenuState.Level3
            NestedMenuState.Level3 -> NestedMenuState.Level1
        }
    }) {
        Column(
            Modifier
                .fillMaxWidth()
                .content(tag, state.toString())
        ) {
            AnimatedContent(
                state,
                transitionSpec = {
                    enterExitTransition().apply {
                        targetContentZIndex = when (targetState) {
                            NestedMenuState.Level1 -> 1f
                            NestedMenuState.Level2 -> 2f
                            NestedMenuState.Level3 -> 3f
                        }
                    }
                }
            ) {
                when (it) {
                    NestedMenuState.Level1 -> SmallItem()
                    NestedMenuState.Level2 -> CollapsedItem()
                    NestedMenuState.Level3 -> ExpandedItem()
                }
            }
        }
    }
}

private val animationSpec = tween<IntOffset>(500)

private fun ACTScope<NestedMenuState>.enterExitTransition() = if (initialState < targetState)
    slideIntoContainer(towards = SlideDirection.Left, animationSpec) togetherWith
            ExitTransition.KeepUntilTransitionsFinished
else slideIntoContainer(towards = SlideDirection.Right, animationSpec) { offsetForFullSlide ->
    offsetForFullSlide / 2
} togetherWith slideOutOfContainer(towards = SlideDirection.Right, animationSpec)

fun CodeEditor.appendSlideIntoContainerSample() {
    appendComposable()
    line { `fun`; normal { " AnimatedContent." }; blue { "SlideIntoContainerSample" }; normal { "() {" } }
    line(1) { `var`; normal { " state " }; `by`; normal { " remember { mutableStateOf(NestedMenuState." }; purple { "Level1" }; normal { ") }" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_content_slide_into_container" }; normal { ")" } }
    line(1) { normal { "Module(tag, {" } }
    line(2) { normal { "state = " }; `when`; normal { " (state) {" } }
    line(3) { normal { "NestedMenuState." }; purple { "Level1" }; normal { " -> NestedMenuState." }; purple { "Level2" } }
    line(3) { normal { "NestedMenuState." }; purple { "Level2" }; normal { " -> NestedMenuState." }; purple { "Level3" } }
    line(3) { normal { "NestedMenuState." }; purple { "Level3" }; normal { " -> NestedMenuState." }; purple { "Level1" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}) {" } }
    line(2) { normal { "Column(" } }
    line(3) { normal { "Modifier" } }
    line(4) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(4) { normal { "." }; blue { "content" }; normal { "(tag, state.toString())" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "AnimatedContent(" } }
    line(4) { normal { "state," } }
    line(4) { cyan { "transitionSpec =" }; normal { " {" } }
    line(5) { blue { "enterExitTransition" }; normal { "()." }; blue { "apply" }; normal { " {" } }
    line(6) { purple { "targetContentZIndex" }; normal { " = " }; `when`; normal { " (" }; purple { "targetState" }; normal { ") {" } }
    line(7) { normal { "NestedMenuState." }; purple { "Level1" }; normal { " -> " }; cyan { "1f" } }
    line(7) { normal { "NestedMenuState." }; purple { "Level2" }; normal { " -> " }; cyan { "2f" } }
    line(7) { normal { "NestedMenuState." }; purple { "Level3" }; normal { " -> " }; cyan { "3f" } }
    line(6) { normal { "}" } }
    line(5) { normal { "}" } }
    line(4) { normal { "}" } }
    line(3) { normal { ") {" } }
    line(4) { `when`; normal { " (it) {" } }
    line(5) { normal { "NestedMenuState." }; purple { "Level1" }; normal { " -> SmallItem()" } }
    line(5) { normal { "NestedMenuState." }; purple { "Level2" }; normal { " -> CollapsedItem()" } }
    line(5) { normal { "NestedMenuState." }; purple { "Level3" }; normal { " -> ExpandedItem()" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
    appendAnimationSpec()
    appendACTScope_NestedMenuState_enterExitTransition()
}

fun CodeEditor.appendAnimationSpec() {
    line { orange { "private val" }; purple { " animationSpec" }; normal { " = tween<IntOffset>(" }; cyan { "500" }; normal { ")" } }
    line()
}

@Suppress("FunctionName")
fun CodeEditor.appendACTScope_NestedMenuState_enterExitTransition() {
    line { orange { "private fun" }; normal { " ACTScope<NestedMenuState>." }; blue { "enterExitTransition" }; normal { "() = " }; `if`; normal { " (" }; purple { "initialState" }; normal { " < " }; purple { "targetState" }; normal { ")" } }
    line(1) { normal { "slideIntoContainer(" }; cyan { "towards =" }; normal { " SlideDirection." }; purple { "Left" }; normal { ", " }; purple { "animationSpec" }; normal { ") " }; blue { "togetherWith" } }
    line(3) { normal { "ExitTransition." }; purple { "KeepUntilTransitionsFinished" } }
    line { `else`; normal { " slideIntoContainer(" }; cyan { "towards =" }; normal { " SlideDirection." }; purple { "Right" }; normal { ", " }; purple { "animationSpec" }; normal { ") { offsetForFullSlide ->" } }
    line(1) { normal { "offsetForFullSlide / " }; cyan { "2" } }
    line { normal { "} " }; blue { "togetherWith" }; normal { " slideOutOfContainer(" }; cyan { "towards =" }; normal { " SlideDirection." }; purple { "Right" }; normal { ", " }; purple { "animationSpec" }; normal { ")" } }
    line()
}
