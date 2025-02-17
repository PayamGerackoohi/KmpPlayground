package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.IntSize
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibility
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibility.Module
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedVisibility.ExpandInShrinkOutSample() {
    var visible by rememberBoolean()
    val tag = stringResource(
        Res.string.animations_animated_visibility_expand_in_shrink_out
    )
    Module(tag, { visible = !visible }) {
        AnimatedVisibility(
            visible,
            enter = expandIn(
                animationSpec = tween(300, easing = LinearOutSlowInEasing),
                expandFrom = Alignment.BottomStart,
            ) {
                IntSize(50, 50)
            },
            exit = shrinkOut(
                tween(300, easing = FastOutSlowInEasing),
                shrinkTowards = Alignment.CenterStart,
            ) { (width, height) ->
                IntSize(width / 10, height / 5)
            }
        ) {
            Module.Content(tag)
        }
    }
}

fun CodeEditor.appendExpandInShrinkOutSample() {
    appendComposable()
    line { `fun`; normal { " AnimatedVisibility." }; blue { "ExpandInShrinkOutSample" }; normal { "() {" } }
    line(1) { `var`; normal { " visible " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " tag = stringResource(" } }
    line(2) { normal { "Res.string." }; purple { "animations_animated_visibility_expand_in_shrink_out" } }
    line(1) { normal { ")" } }
    line(1) { normal { "Module(tag, { visible = !visible }) {" } }
    line(2) { normal { "AnimatedVisibility(" } }
    line(3) { normal { "visible," } }
    line(3) { cyan { "enter = " }; normal { "expandIn(" } }
    line(4) { cyan { "animationSpec = " }; normal { "tween(" }; cyan { "300" }; normal { ", " }; cyan { "easing = " }; purple { "LinearOutSlowInEasing" }; normal { ")," } }
    line(4) { cyan { "expandFrom = " }; normal { "Alignment." }; purple { "BottomStart" }; normal { "," } }
    line(3) { normal { ") {" } }
    line(4) { normal { "IntSize(" }; cyan { "50" }; normal { ", " }; cyan { "50" }; normal { ")" } }
    line(3) { normal { "}," } }
    line(3) { cyan { "exit = " }; normal { "shrinkOut(" } }
    line(4) { normal { "tween(" }; cyan { "300" }; normal { ", " }; cyan { "easing = " }; purple { "FastOutSlowInEasing" }; normal { ")," } }
    line(4) { cyan { "shrinkTowards = " }; normal { "Alignment." }; purple { "CenterStart" }; normal { "," } }
    line(3) { normal { ") { (width, height) ->" } }
    line(4) { normal { "IntSize(width / " }; cyan { "10" }; normal { ", height / " }; cyan { "5" }; normal { ")" } }
    line(3) { normal { "}" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Module.Content(tag)" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
