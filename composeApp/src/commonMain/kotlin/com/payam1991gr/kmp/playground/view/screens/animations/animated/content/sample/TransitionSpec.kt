package com.payam1991gr.kmp.playground.view.screens.animations.animated.content.sample

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.ACTScope
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContent
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContent.CartState
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedContent.TransitionSpecSample() {
    var state by remember { mutableStateOf(CartState.Expanded) }
    val tag = stringResource(Res.string.animations_animated_content_transition_spec)
    Module(tag, {
        state = when (state) {
            CartState.Expanded -> CartState.Collapsed
            CartState.Collapsed -> CartState.Expanded
        }
    }) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .clipToBounds()
                .content(tag, state.toString())
        ) {
            AnimatedContent(
                targetState = state,
                transitionSpec = { enterExitTransform() using sizeTransform() }
            ) {
                when (it) {
                    CartState.Expanded -> ExpandedItem()
                    CartState.Collapsed -> CollapsedItem()
                }
            }
        }
    }
}

private fun enterExitTransform() = fadeIn(
    animationSpec = tween(150, delayMillis = 150)
) togetherWith fadeOut(
    animationSpec = tween(150)
)

private fun ACTScope<CartState>.sizeTransform() = SizeTransform { initialSize, targetSize ->
    if (CartState.Collapsed isTransitioningTo CartState.Expanded) keyframes {
        durationMillis = 300
        IntSize(targetSize.width, initialSize.height + 200) at 150
    } else keyframes {
        durationMillis = 300
        IntSize(
            initialSize.width,
            (initialSize.height + targetSize.height) / 2
        ) at 150
    }
}

fun CodeEditor.appendTransitionSpecSample() {
    appendComposable()
    line { `fun`; normal { " AnimatedContent." }; blue { "TransitionSpecSample" }; normal { "() {" } }
    line(1) { `var`; normal { " state " }; `by`; normal { " remember { mutableStateOf(CartState." }; purple { "Expanded" }; normal { ") }" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_content_transition_spec" }; normal { ")" } }
    line(1) { normal { "Module(tag, {" } }
    line(2) { normal { "state = " }; `when`; normal { " (state) {" } }
    line(3) { normal { "CartState." }; purple { "Expanded" }; normal { " -> CartState." }; purple { "Collapsed" } }
    line(3) { normal { "CartState." }; purple { "Collapsed" }; normal { " -> CartState." }; purple { "Expanded" } }
    line(2) { normal { "}" } }
    line(1) { normal { ")} {" } }
    line(2) { normal { "Box(" } }
    line(3) { cyan { "contentAlignment =" }; normal { " Alignment." }; purple { "Center" }; normal { "," } }
    line(3) { cyan { "modifier =" }; normal { "Modifier" } }
    line(4) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(4) { normal { "." }; blue { "height" }; normal { "(" }; cyan { "200" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { "." }; blue { "background" }; normal { "(MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "surfaceVariant" }; normal { ")" } }
    line(4) { normal { "." }; blue { "clipToBounds" }; normal { "()" } }
    line(4) { normal { "." }; blue { "content" }; normal { "(tag, state.toString())" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "AnimatedContent(" } }
    line(4) { cyan { "targetState =" }; normal { " state," } }
    line(4) { cyan { "transitionSpec =" }; normal { " { enterExitTransform() " }; blue { "using sizeTransform" }; normal { "() }" } }
    line(3) { normal { ") {" } }
    line(4) { `when`; normal { " (it) {" } }
    line(5) { normal { "CartState." }; purple { "Expanded" }; normal { " -> ExpandedItem()" } }
    line(5) { normal { "CartState." }; purple { "Collapsed" }; normal { " -> CollapsedItem()" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
    appendEnterExitTransform()
    appendACTScope_CartState_sizeTransform()
}

private fun CodeEditor.appendEnterExitTransform() {
    line { orange { "private fun" }; blue { " enterExitTransform" }; normal { "() = fadeIn(" } }
    line(1) { cyan { "animationSpec =" }; normal { " tween(" }; cyan { "150" }; normal { ", " }; cyan { "delayMillis = 150" }; normal { ")" } }
    line { normal { ") " }; blue { "togetherWith" }; normal { " fadeOut(" } }
    line(1) { cyan { "animationSpec =" }; normal { " tween(" }; cyan { "150" }; normal { ")" } }
    line { normal { ")" } }
    line()
}

@Suppress("FunctionName")
private fun CodeEditor.appendACTScope_CartState_sizeTransform() {
    line { orange { "private fun" }; normal { " ACTScope<CartState>." }; blue { "sizeTransform" }; normal { "() = SizeTransform { initialSize, targetSize ->" } }
    line(1) { `if`; normal { " (CartState." }; purple { "Collapsed" }; blue { " isTransitioningTo" }; normal { " CartState." }; purple { "Expanded" }; normal { ") keyframes {" } }
    line(2) { purple { "durationMillis" }; normal { " = " }; cyan { "300" } }
    line(2) { normal { "IntSize(targetSize." }; purple { "width" }; normal { ", initialSize." }; purple { "height" }; normal { " + " }; cyan { "200" }; normal { ") " }; blue { "at" }; cyan { " 150" } }
    line(1) { normal { "}" } }
    line(2) { purple { "durationMillis" }; normal { " = " }; cyan { "300" } }
    line(2) { normal { "IntSize(" } }
    line(3) { normal { "initialSize." }; purple { "width" }; normal { "," } }
    line(3) { normal { "(initialSize." }; purple { "height" }; normal { " + targetSize." }; purple { "height" }; normal { ") / " }; cyan { "2" } }
    line(2) { normal { ") " }; blue { "at" }; cyan { " 150" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
