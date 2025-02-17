package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.unit.IntOffset
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibility
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibility.Module
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedVisibility.SlideInOutSample() {
    var visible by rememberBoolean()
    val tag = stringResource(Res.string.animations_animated_visibility_slide_in_out)
    Module(tag, { visible = !visible }) {
        AnimatedVisibility(
            visible,
            enter = slideIn(tween(300, easing = LinearOutSlowInEasing)) { fullSize ->
                IntOffset(fullSize.width / 4, 100)
            },
            exit = slideOut(tween(300, easing = FastOutSlowInEasing)) {
                IntOffset(-180, 50)
            },
            modifier = Modifier.clipToBounds()
        ) {
            Module.Content(tag)
        }
    }
}

fun CodeEditor.appendSlideInOutSample() {
    appendComposable()
    line { `fun`; normal { " AnimatedVisibility." }; blue { "SlideInOutSample" }; normal { "() {" } }
    line(1) { `var`; normal { " visible " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_visibility_slide_in_out" }; normal { ")" } }
    line(1) { normal { "Module(tag, { visible = !visible }) {" } }
    line(2) { normal { "AnimatedVisibility(" } }
    line(3) { normal { "visible," } }
    line(3) { cyan { "enter = " }; normal { "slideIn(tween(" }; cyan { "300" }; normal { ", " }; cyan { "easing = " }; purple { "LinearOutSlowInEasing" }; normal { ")) { fullSize ->" } }
    line(4) { normal { "IntOffset(fullSize." }; purple { "width" }; normal { " / " }; cyan { "4" }; normal { ", " }; cyan { "100" }; normal { ")" } }
    line(3) { normal { "}," } }
    line(3) { cyan { "exit = " }; normal { "slideOut(tween(" }; cyan { "300" }; normal { ", " }; cyan { "easing = " }; purple { "FastOutSlowInEasing" }; normal { ")) {" } }
    line(4) { normal { "IntOffset(-" }; cyan { "180" }; normal { ", " }; cyan { "50" }; normal { ")" } }
    line(3) { normal { "}," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier." }; blue { "clipToBounds" }; normal { "()" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Module.Content(tag)" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
