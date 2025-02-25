package com.payam1991gr.kmp.playground.view.screens.animations.animated.content.sample

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
fun AnimatedContent.TransitionExtensionSample() {
    var state by remember { mutableStateOf(CartState.Collapsed) }
    val tag = stringResource(Res.string.animations_animated_content_transition_extension)
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
                .content(tag, state.toString())
        ) {
            val transition = updateTransition(state, "CartOpenTransition")
            val cornerSize by transition.animateDp(
                label = "cartCornerSize",
                transitionSpec = {
                    when {
                        CartState.Expanded isTransitioningTo CartState.Collapsed ->
                            tween(durationMillis = 400, delayMillis = 100)

                        else -> tween(durationMillis = 500)
                    }
                }
            ) { if (it == CartState.Expanded) 0.dp else 24.dp }
            val shape = CutCornerShape(topStart = cornerSize)
            Surface(
                Modifier
                    .shadow(8.dp, shape)
                    .clip(shape),
                color = MaterialTheme.colorScheme.surfaceVariant,
            ) {
                transition.AnimatedContent(
                    transitionSpec = {
                        (enterExitTransition() using sizeTransform()).apply {
                            targetContentZIndex = when (targetState) {
                                CartState.Expanded -> 1f
                                CartState.Collapsed -> 2f
                            }
                        }
                    }
                ) {
                    when (it) {
                        CartState.Expanded -> ExpandedItem()
                        CartState.Collapsed -> CollapsedItem()
                    }
                }
            }
        }
    }
}

private fun enterExitTransition() = fadeIn(
    animationSpec = tween(250, delayMillis = 250)
) togetherWith fadeOut(
    animationSpec = tween(250)
)

private fun ACTScope<CartState>.sizeTransform() = SizeTransform { initialSize, targetSize ->
    if (CartState.Collapsed isTransitioningTo CartState.Expanded) keyframes {
        durationMillis = 500
        IntSize(targetSize.width * 2, initialSize.height) at 250
    } else keyframes {
        durationMillis = 500
        IntSize((initialSize.width + targetSize.width) / 2, initialSize.height) at 250
    }
}

fun CodeEditor.appendTransitionExtensionSample() {
    appendComposable()
    line { `fun`; normal { " AnimatedContent." }; blue { "TransitionExtensionSample" }; normal { "() {" } }
    line(1) { `var`; normal { " state " }; `by`; normal { " remember { mutableStateOf(CartState." }; purple { "Collapsed" }; normal { ") }" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_content_transition_extension" }; normal { ")" } }
    line(1) { normal { "Module(tag, {" } }
    line(2) { normal { "state = " }; `when`; normal { " (state) {" } }
    line(3) { normal { "CartState." }; purple { "Expanded" }; normal { " -> CartState." }; purple { "Collapsed" } }
    line(3) { normal { "CartState." }; purple { "Collapsed" }; normal { " -> CartState." }; purple { "Expanded" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}) {" } }
    line(2) { normal { "Box(" } }
    line(3) { cyan { "contentAlignment =" }; normal { " Alignment." }; purple { "Center" }; normal { "," } }
    line(3) { cyan { "modifier =" }; normal { " Modifier" } }
    line(4) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(4) { normal { "." }; blue { "content" }; normal { "(tag, state.toString())" } }
    line(2) { normal { ") {" } }
    line(3) { `val`; normal { " transition = updateTransition(state, " }; green { "\"CartOpenTransition\"" }; normal { ")" } }
    line(3) { `val`; normal { " cornerSize " }; `by`; normal { " transition." }; blue { "animateDp" }; normal { "(" } }
    line(4) { cyan { "label =" }; green { " \"cartCornerSize\"" }; normal { "," } }
    line(4) { cyan { "transitionSpec = " }; normal { "{" } }
    line(5) { `when`; normal { " {" } }
    line(6) { normal { "CartState." }; purple { "Expanded" }; blue { " isTransitioningTo" }; normal { " CartState." }; purple { "Collapsed" }; normal { " ->" } }
    line(7) { normal { "tween(" }; cyan { "durationMillis = 400" }; normal { ", " }; cyan { "delayMillis = 100" }; normal { ")" } }
    line()
    line(6) { `else`; normal { " -> tween(" }; cyan { "durationMillis = 500" }; normal { ")" } }
    line(5) { normal { "}" } }
    line(4) { normal { "}" } }
    line(3) { normal { ") { " }; `if`; normal { " (it == CartState." }; purple { "Expanded" }; normal { ") " }; cyan { "0" }; normal { "." }; purple { "dp " }; `else`; cyan { " 24" }; normal { "." }; purple { "dp" }; normal { " }" } }
    line(3) { `val`; normal { " shape = CutCornerShape(" }; cyan { "topStart = " }; normal { "cornerSize)" } }
    line(3) { normal { "Surface(" } }
    line(4) { normal { "Modifier" } }
    line(5) { normal { "." }; blue { "shadow" }; normal { "(" }; cyan { "8" }; normal { "." }; purple { "dp" }; normal { ", shape)" } }
    line(5) { normal { "." }; blue { "clip" }; normal { "(shape)," } }
    line(4) { cyan { "color =" }; normal { " MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "surfaceVariant" }; normal { "," } }
    line(3) { normal { ") {" } }
    line(4) { normal { "transition." }; blue { "AnimatedContent" }; normal { "(" } }
    line(5) { cyan { "transitionSpec =" }; normal { " {" } }
    line(6) { normal { "(enterExitTransition() " }; blue { "using sizeTransform" }; normal { "())." }; blue { "apply" }; normal { " {" } }
    line(7) { purple { "targetContentZIndex" }; normal { " = " }; `when`; normal { " (" }; purple { "targetState" }; normal { ") {" } }
    line(8) { normal { "CartState." }; purple { "Expanded" }; normal { " -> " }; cyan { "1f" } }
    line(8) { normal { "CartState." }; purple { "Collapsed" }; normal { " -> " }; cyan { "2f" } }
    line(7) { normal { "}" } }
    line(6) { normal { "}" } }
    line(5) { normal { "}" } }
    line(4) { normal { ") {" } }
    line(5) { `when`; normal { " (it) {" } }
    line(6) { normal { "CartState." }; purple { "Expanded" }; normal { " -> ExpandedItem()" } }
    line(6) { normal { "CartState." }; purple { "Collapsed" }; normal { " -> CollapsedItem()" } }
    line(5) { normal { "}" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
    appendEnterExitTransition()
    appendACTScope_CartState_sizeTransform()
}

private fun CodeEditor.appendEnterExitTransition() {
    line { orange { "private fun" }; blue { " enterExitTransition" }; normal { "() = fadeIn(" } }
    line(1) { cyan { "animationSpec =" }; normal { " tween(" }; cyan { "250" }; normal { ", " }; cyan { "delayMillis = 250" }; normal { ")" } }
    line { normal { ") " }; blue { "togetherWith" }; normal { " fadeOut(" } }
    line(1) { cyan { "animationSpec =" }; normal { " tween(" }; cyan { "250" }; normal { ")" } }
    line { normal { ")" } }
    line()
}

@Suppress("FunctionName")
private fun CodeEditor.appendACTScope_CartState_sizeTransform() {
    line { orange { "private fun" }; normal { " ACTScope<CartState>." }; blue { "sizeTransform" }; normal { "() = SizeTransform { initialSize, targetSize ->" } }
    line(1) { `if`; normal { " (CartState." }; purple { "Collapsed" }; blue { " isTransitioningTo" }; normal { " CartState." }; purple { "Expanded" }; normal { ") keyframes {" } }
    line(2) { purple { "durationMillis" }; normal { " = " }; cyan { "500" } }
    line(2) { normal { "IntSize(targetSize." }; purple { "width" }; normal { " * " }; cyan { "2" }; normal { ", initialSize." }; purple { "height" }; normal { ") " }; blue { "at" }; cyan { " 250" } }
    line(1) { normal { "} " }; `else`; normal { " keyframes {" } }
    line(2) { purple { "durationMillis" }; normal { " = " }; cyan { "500" } }
    line(2) { normal { "IntSize((initialSize." }; purple { "width" }; normal { " + targetSize." }; purple { "width" }; normal { ") / " }; cyan { "2" }; normal { ", initialSize." }; purple { "height" }; normal { ") " }; blue { "at" }; cyan { " 250" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
