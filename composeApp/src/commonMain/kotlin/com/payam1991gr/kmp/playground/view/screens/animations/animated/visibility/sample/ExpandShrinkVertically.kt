package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibility
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibility.Module
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedVisibility.ExpandShrinkVerticallySample() {
    var visible by rememberBoolean()
    val tag = stringResource(
        Res.string.animations_animated_visibility_expand_shrink_vertically
    )
    Module(tag, { visible = !visible }) {
        AnimatedVisibility(
            visible,
            enter = expandVertically(expandFrom = Alignment.Top) { 20 },
            exit = shrinkVertically(tween()) { it / 2 },
        ) {
            Module.Content(tag)
        }
    }
}

fun CodeEditor.appendExpandShrinkVerticallySample() {
    appendComposable()
    line { `fun`; normal { " AnimatedVisibility." }; blue { "ExpandShrinkVerticallySample" }; normal { "() {" } }
    line(1) { `var`; normal { " visible " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " tag = stringResource(" } }
    line(2) { normal { "Res.string." }; purple { "animations_animated_visibility_expand_shrink_vertically" } }
    line(1) { normal { ")" } }
    line(1) { normal { "Module(tag, { visible = !visible }) {" } }
    line(2) { normal { "AnimatedVisibility(" } }
    line(3) { normal { "visible," } }
    line(3) { cyan { "enter = " }; normal { "expandVertically(" }; cyan { "expandFrom = " }; normal { "Alignment." }; purple { "Top" }; normal { ") { " }; cyan { "20" }; normal { " }," } }
    line(3) { cyan { "exit = " }; normal { "shrinkVertically(tween()) { it / " }; cyan { "2" }; normal { " }," } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Module.Content(tag)" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
