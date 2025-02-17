package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.ContentList
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibility
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibility.Module
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.animations_animated_visibility_boolean_visible_param_no_receiver
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimatedVisibility.BooleanVisibleParamNoReceiverSample(
    initHeaderColor: Color = MaterialTheme.colorScheme.secondary,
) = ContentList {
    var visible by rememberBoolean()
    var headerColor by remember { mutableStateOf(initHeaderColor) }
    val tag = stringResource(
        Res.string.animations_animated_visibility_boolean_visible_param_no_receiver
    )
    Module(
        label = tag,
        colors = Module.Defaults.colors(headerColor),
        onToggle = { visible = !visible },
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut() + scaleOut(),
        ) {
            val cornerRadius by transition.animateDp {
                when (it) {
                    EnterExitState.PreEnter -> 0.dp
                    EnterExitState.Visible -> 32.dp
                    EnterExitState.PostExit -> 32.dp
                }
            }
            val localHeaderColor by transition.animateColor {
                when (it) {
                    EnterExitState.PreEnter -> MaterialTheme.colorScheme.tertiary
                    EnterExitState.Visible -> MaterialTheme.colorScheme.secondary
                    EnterExitState.PostExit -> MaterialTheme.colorScheme.tertiary
                }
            }
            headerColor = localHeaderColor
            Module.Content(tag, cornerRadius)
        }
    }
}

fun CodeEditor.appendBooleanVisibleParamNoReceiverSample() {
    appendComposable()
    line { `fun`; normal { " AnimatedVisibility." }; blue { "BooleanVisibleParamNoReceiverSample" }; normal { "(" } }
    line(1) { normal { "initHeaderColor: Color = MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "secondary" }; normal { "," } }
    line { normal { ") = ContentList {" } }
    line(1) { `var`; normal { " visible " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `var`; normal { " headerColor " }; `by`; normal { " remember { mutableStateOf(initHeaderColor) }" } }
    line(1) { `val`; normal { " tag = stringResource(" } }
    line(2) { normal { "Res.string." }; purple { "animations_animated_visibility_boolean_visible_param_no_receiver" } }
    line(1) { normal { ")" } }
    line(1) { normal { "Module(" } }
    line(2) { cyan { "label = " }; normal { "tag," } }
    line(2) { cyan { "colors = " }; normal { "Module.Defaults.colors(headerColor)," } }
    line(2) { cyan { "onToggle = " }; normal { "{ visible = !visible }," } }
    line(1) { normal { ") {" } }
    line(2) { blue { "AnimatedVisibility" }; normal { "(" } }
    line(3) { cyan { "visible = " }; normal { "visible," } }
    line(3) { cyan { "enter = " }; normal { "fadeIn()," } }
    line(3) { cyan { "exit = " }; normal { "fadeOut() + scaleOut()," } }
    line(2) { normal { ") {" } }
    line(3) { `val`; normal { " cornerRadius " }; `by`; purple { " transition" }; normal { "." }; blue { "animateDp " }; normal { "{" } }
    line(4) { `when`; normal { " (it) {" } }
    line(5) { normal { "EnterExitState." }; purple { "PreEnter " }; normal { "-> " }; cyan { "0" }; normal { "." }; purple { "dp" } }
    line(5) { normal { "EnterExitState." }; purple { "Visible " }; normal { "-> " }; cyan { "32" }; normal { "." }; purple { "dp" } }
    line(5) { normal { "EnterExitState." }; purple { "PostExit " }; normal { "-> " }; cyan { "32" }; normal { "." }; purple { "dp" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(3) { `val`; normal { " localHeaderColor " }; `by`; purple { " transition" }; normal { "." }; blue { "animateColor " }; normal { "{" } }
    line(4) { `when`; normal { " (it) {" } }
    line(5) { normal { "EnterExitState." }; purple { "PreEnter" }; normal { " -> MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "tertiary" } }
    line(5) { normal { "EnterExitState." }; purple { "Visible" }; normal { " -> MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "secondary" } }
    line(5) { normal { "EnterExitState." }; purple { "PostExit" }; normal { " -> MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "tertiary" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(3) { normal { "headerColor = localHeaderColor" } }
    line(3) { normal { "Module.Content(tag, cornerRadius)" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
