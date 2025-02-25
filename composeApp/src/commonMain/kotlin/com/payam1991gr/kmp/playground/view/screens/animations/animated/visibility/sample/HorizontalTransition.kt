package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun HorizontalTransitionSample() {
    var visible by rememberBoolean()
    val tag = stringResource(Res.string.animations_animated_visibility_horizontal_transition)
    Module(
        label = tag,
        onToggle = { visible = !visible },
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = expandHorizontally { 20 },
            exit = shrinkHorizontally(
                animationSpec = tween(),
                shrinkTowards = Alignment.End,
            ) { it / 4 }
        ) {
            Module.Content(tag)
        }
    }
}

fun CodeEditor.appendHorizontalTransitionSample() {
    appendComposable()
    line { `fun`; blue { " HorizontalTransitionSample" }; normal { "() {" } }
    line(1) { `var`; normal { " visible " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_visibility_horizontal_transition" }; normal { ")" } }
    line(1) { normal { "Module(" } }
    line(2) { cyan { "label = " }; normal { "tag," } }
    line(2) { cyan { "onToggle = " }; normal { "{ visible = !visible }," } }
    line(1) { normal { ") {" } }
    line(2) { normal { "AnimatedVisibility(" } }
    line(3) { cyan { "visible = " }; normal { "visible," } }
    line(3) { cyan { "enter = " }; normal { "expandHorizontally { " }; cyan { "20 " }; normal { "}," } }
    line(3) { cyan { "exit = " }; normal { "shrinkHorizontally(" } }
    line(4) { cyan { "animationSpec = " }; normal { "tween()," } }
    line(4) { cyan { "shrinkTowards = " }; normal { "Alignment." }; purple { "End" }; normal { "," } }
    line(3) { normal { ") { it / " }; cyan { "4 " }; normal { "}" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Module.Content(tag)" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
