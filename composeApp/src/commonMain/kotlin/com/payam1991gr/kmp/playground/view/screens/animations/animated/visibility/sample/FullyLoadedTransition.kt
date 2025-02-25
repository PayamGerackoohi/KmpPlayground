package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.TransformOrigin
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.ContentList
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.animations_animated_visibility_fully_loaded_transition
import org.jetbrains.compose.resources.stringResource

@Composable
fun FullyLoadedTransitionSample() = ContentList {
    var visible by rememberBoolean()
    val tag = stringResource(Res.string.animations_animated_visibility_fully_loaded_transition)
    Module(
        label = tag,
        onToggle = { visible = !visible },
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically(initialOffsetY = { -40 }) +
                    expandVertically(expandFrom = Alignment.Top) +
                    scaleIn(transformOrigin = TransformOrigin(.5f, 0f)) +
                    fadeIn(initialAlpha = .3f),
            exit = slideOutVertically() +
                    shrinkVertically() +
                    fadeOut() +
                    scaleOut(targetScale = 1.2f)
        ) {
            Module.Content(tag)
        }
    }
}

fun CodeEditor.appendFullyLoadedTransitionSample() {
    appendComposable()
    line { `fun`; blue { " FullyLoadedTransitionSample" }; normal { "() = ContentList {" } }
    line(1) { `var`; normal { " visible " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_visibility_fully_loaded_transition" }; normal { ")" } }
    line(1) { normal { "Module(" } }
    line(2) { cyan { "label = " }; normal { "tag," } }
    line(2) { cyan { "onToggle = " }; normal { "{ visible = !visible }," } }
    line(1) { normal { ") {" } }
    line(2) { blue { "AnimatedVisibility" }; normal { "(" } }
    line(3) { cyan { "visible = " }; normal { "visible," } }
    line(3) { cyan { "enter = " }; normal { "slideInVertically(" }; cyan { "initialOffsetY = " }; normal { "{ -" }; cyan { "40" }; normal { " }) +" } }
    line(5) { normal { "expandVertically(" }; cyan { "expandFrom = " }; normal { "Alignment." }; purple { "Top" }; normal { ") +" } }
    line(5) { normal { "scaleIn(" }; cyan { "transformOrigin = " }; normal { "TransformOrigin(" }; cyan { ".5f" }; normal { ", " }; cyan { "0f" }; normal { ")) +" } }
    line(5) { normal { "fadeIn(" }; cyan { "initialAlpha = .3f" }; normal { ")," } }
    line(3) { cyan { "exit = " }; normal { "slideOutVertically() +" } }
    line(5) { normal { "shrinkVertically() +" } }
    line(5) { normal { "fadeOut() +" } }
    line(5) { normal { "scaleOut(" }; cyan { "targetScale = 1.2f" }; normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Module.Content(tag)" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
