package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.animations_animated_visibility_slide_transition
import org.jetbrains.compose.resources.stringResource

@Composable
fun SlideTransitionSample() {
    var visible by rememberBoolean()
    val tag = stringResource(Res.string.animations_animated_visibility_slide_transition)
    Module(
        label = tag,
        onToggle = { visible = !visible },
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInHorizontally(tween(durationMillis = 200)) { -it / 3 } +
                    fadeIn(tween(200)),
            exit = slideOutHorizontally(spring(stiffness = Spring.StiffnessHigh)) { 200 } +
                    fadeOut(),
            modifier = Modifier.clipToBounds()
        ) {
            Module.Content(tag)
        }
    }
}

fun CodeEditor.appendSlideTransitionSample() {
    appendComposable()
    line { `fun`; blue { "SlideTransitionSample" }; normal { "() {" } }
    line(1) { `var`; normal { " visible " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_visibility_slide_transition" }; normal { ")" } }
    line(1) { normal { "Module(" } }
    line(2) { cyan { "label = " }; normal { "tag," } }
    line(2) { cyan { "onToggle = " }; normal { "{ visible = !visible }," } }
    line(1) { normal { ") {" } }
    line(2) { normal { "AnimatedVisibility(" } }
    line(3) { cyan { "visible = " }; normal { "visible," } }
    line(3) { cyan { "enter = " }; normal { "slideInHorizontally(tween(" }; cyan { "durationMillis = 200" }; normal { ")) { -it / " }; cyan { "3" }; normal { " } +" } }
    line(5) { normal { "fadeIn(tween(" }; cyan { "200" }; normal { "))," } }
    line(3) { cyan { "exit = " }; normal { "slideOutHorizontally(spring(" }; cyan { "stiffness = " }; normal { "Spring." }; purple { "StiffnessHigh" }; normal { ")) { " }; cyan { "200" }; normal { " } +" } }
    line(5) { normal { "fadeOut()," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier." }; blue { "clipToBounds" }; normal { "()" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Module.Content(tag)" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
