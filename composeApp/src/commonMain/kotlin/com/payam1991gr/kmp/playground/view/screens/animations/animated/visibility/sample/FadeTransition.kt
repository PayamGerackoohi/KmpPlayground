package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibility
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibility.Module
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.animations_animated_visibility_fade_transition
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedVisibility.FadeTransitionSample() {
    var visible by rememberBoolean()
    val tag = stringResource(Res.string.animations_animated_visibility_fade_transition)
    Module(
        label = tag,
        onToggle = { visible = !visible },
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(initialAlpha = 0.4f),
            exit = fadeOut(animationSpec = tween(durationMillis = 250)),
        ) {
            Module.Content(tag)
        }
    }
}

fun CodeEditor.appendFadeTransitionSample() {
    appendComposable()
    line { `fun`; normal { " AnimatedVisibility." }; blue { "FadeTransitionSample" }; normal { "() {" } }
    line(1) { `var`; normal { " visible " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_visibility_fade_transition" }; normal { ")" } }
    line(1) { normal { "Module(" } }
    line(2) { cyan { "label = " }; normal { "tag," } }
    line(2) { cyan { "onToggle = " }; normal { "{ visible = !visible }," } }
    line(1) { normal { ") {" } }
    line(2) { normal { "AnimatedVisibility(" } }
    line(3) { cyan { "visible = " }; normal { "visible," } }
    line(3) { cyan { "enter = " }; normal { "fadeIn(" }; cyan { "initialAlpha = 0.4f" }; normal { ")," } }
    line(3) { cyan { "exit = " }; normal { "fadeOut(" }; cyan { "animationSpec = " }; normal { "tween(" }; cyan { "durationMillis = 250" }; normal { "))," } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Module.Content(tag)" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
