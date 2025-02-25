package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterExitState
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.moduleSize
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.animations_animated_visibility_scope_animate_enter_exit
import kmpplayground.composeapp.generated.resources.animations_animated_visibility_scope_animate_enter_exit_transition_finished
import org.jetbrains.compose.resources.stringResource

@Composable
fun ScopeAnimateEnterExitSample() {
    val state = remember { MutableTransitionState(true) }
    val tag = stringResource(Res.string.animations_animated_visibility_scope_animate_enter_exit)
    Module(
        label = tag,
        onToggle = { state.targetState = !state.targetState },
    ) {
        Box(
            Modifier
                .background(
                    MaterialTheme.colorScheme.surfaceContainerHighest,
                    RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
                )
        ) {
            AnimatedVisibility(
                visibleState = state,
                modifier = Modifier.fillMaxSize(),
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Box {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(4.dp),
                        modifier = Modifier
                            .moduleSize()
                            .padding(4.dp)
                            .content(tag)
                    ) {
                        listOf(
                            0xffff6f69, 0xff6fff69, 0xff6f69ff,
                            0xffffcc5c, 0xffccff5c, 0xffcc5cff,
                        ).forEach {
                            Item(Modifier.weight(1f), backgroundColor = Color(it))
                        }
                    }
                    FloatingActionButton(
                        onClick = {},
                        backgroundColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(20.dp)
                    ) {
                        Icon(
                            Icons.Default.Favorite,
                            contentDescription = null,
                        )
                    }
                }
            }
            AnimatedVisibility(
                visible = state.run { targetState && isIdle },
                enter = expandVertically(),
                exit = fadeOut(animationSpec = tween(50)),
                modifier = Modifier.align(Alignment.Center)
            ) {
                Text(
                    stringResource(Res.string.animations_animated_visibility_scope_animate_enter_exit_transition_finished),
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(4.dp),
                        )
                        .padding(horizontal = 4.dp)
                )
            }
        }
    }
}

@Composable
private fun AnimatedVisibilityScope.Item(modifier: Modifier, backgroundColor: Color) {
    val scale by transition.animateFloat { enterExitState ->
        when (enterExitState) {
            EnterExitState.PreEnter -> .9f
            EnterExitState.Visible -> 1f
            EnterExitState.PostExit -> .5f
        }
    }
    Box(
        modifier
            .fillMaxHeight()
            .animateEnterExit(
                enter = slideInVertically(initialOffsetY = { it }),
                exit = ExitTransition.None,
            )
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            }
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
    )
}

fun CodeEditor.appendScopeAnimateEnterExitSample() {
    appendComposable()
    line { `fun`; blue { " ScopeAnimateEnterExitSample" }; normal { "() {" } }
    line(1) { `val`; normal { " state = remember { MutableTransitionState(" }; `true`; normal { ") }" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_visibility_scope_animate_enter_exit" }; normal { ")" } }
    line(1) { normal { "Module(" } }
    line(2) { cyan { "label = " }; normal { "tag," } }
    line(2) { cyan { "onToggle = " }; normal { "{ state." }; purple { "targetState" }; normal { " = !state." }; purple { "targetState" }; normal { " }," } }
    line(1) { normal { ") {" } }
    line(2) { normal { "Box(" } }
    line(3) { normal { "Modifier" } }
    line(4) { normal { "." }; blue { "background" }; normal { "(" } }
    line(5) { normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "surfaceContainerHighest" }; normal { "," } }
    line(5) { normal { "RoundedCornerShape(" }; cyan { "bottomStart = 16" }; normal { "." }; purple { "dp" }; normal { ", " }; cyan { "bottomEnd = 16" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(4) { normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "AnimatedVisibility(" } }
    line(4) { cyan { "visibleState = " }; normal { "state," } }
    line(4) { cyan { "modifier = " }; normal { "Modifier." }; blue { "fillMaxSize" }; normal { "()," } }
    line(4) { cyan { "enter = " }; normal { "fadeIn()," } }
    line(4) { cyan { "exit = " }; normal { "fadeOut()," } }
    line(3) { normal { ") {" } }
    line(4) { normal { "Box(" } }
    line(5) { normal { "Row(" } }
    line(6) { cyan { "horizontalArrangement = " }; normal { "Arrangement.spacedBy(" }; cyan { "4" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(6) { cyan { "modifier = " }; normal { "Modifier" } }
    line(7) { normal { "." }; blue { "moduleSize" }; normal { "()" } }
    line(7) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "4" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(7) { normal { "." }; blue { "testTag" }; normal { "(" }; green { "\"" }; orange { "\$" }; normal { "tag" }; green { ".Content\"" }; normal { ")" } }
    line(5) { normal { ") {" } }
    line(6) { normal { "listOf(" } }
    line(7) { cyan { "0xffff6f69" }; normal { ", " }; cyan { "0xff6fff69" }; normal { ", " }; cyan { "0xff6f69ff" }; normal { "," } }
    line(7) { cyan { "0xffffcc5c" }; normal { ", " }; cyan { "0xffccff5c" }; normal { ", " }; cyan { "0xffcc5cff" }; normal { "," } }
    line(6) { normal { ")." }; blue { "forEach" }; normal { " {" } }
    line(7) { blue { "Item" }; normal { "(Modifier." }; blue { "weight" }; normal { "(" }; cyan { "1f" }; normal { "), " }; cyan { "backgroundColor = " }; normal { "Color(it))" } }
    line(6) { normal { "}" } }
    line(5) { normal { "}" } }
    line(5) { normal { "FloatingActionButton(" } }
    line(6) { cyan { "onClick = " }; normal { "{}," } }
    line(6) { cyan { "backgroundColor = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "primary" }; normal { "," } }
    line(6) { cyan { "contentColor = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "onPrimary" }; normal { "," } }
    line(6) { cyan { "modifier = " }; normal { "Modifier" } }
    line(7) { normal { "." }; blue { "align" }; normal { "(Alignment." }; purple { "BottomEnd" }; normal { ")" } }
    line(7) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "20" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(5) { normal { ") {" } }
    line(6) { normal { "Icon(" } }
    line(7) { normal { "Icons." }; purple { "Default" }; normal { "." }; purple { "Favorite" }; normal { "," } }
    line(7) { cyan { "contentDescription = " }; `null`; normal { "," } }
    line(6) { normal { ")" } }
    line(5) { normal { "}" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(3) { normal { "AnimatedVisibility(" } }
    line(4) { cyan { "visible = " }; normal { "state." }; blue { "run" }; normal { " { " }; purple { "targetState" }; normal { " && " }; purple { "isIdle" }; normal { " }," } }
    line(4) { cyan { "enter = " }; normal { "expandVertically()," } }
    line(4) { cyan { "exit = " }; normal { "fadeOut(" }; cyan { "animationSpec = " }; normal { "tween(" }; cyan { "50" }; normal { "))," } }
    line(4) { cyan { "modifier = " }; normal { "Modifier." }; blue { "align" }; normal { "(Alignment." }; purple { "Center" }; normal { ")" } }
    line(3) { normal { ") {" } }
    line(4) { normal { "Text(" } }
    line(5) { normal { "stringResource(Res.string." }; purple { "animations_animated_visibility_scope_animate_enter_exit_transition_finished" }; normal { ")," } }
    line(5) { cyan { "color = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "onSurface" }; normal { "," } }
    line(5) { cyan { "modifier = " }; normal { "Modifier" } }
    line(6) { normal { "." }; blue { "background" }; normal { "(" } }
    line(7) { cyan { "color = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "surface" }; normal { "," } }
    line(7) { cyan { "shape = " }; normal { "RoundedCornerShape(" }; cyan { "4" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(6) { normal { ")" } }
    line(6) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "horizontal = 4" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { ")" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
    appendItem()
}

private fun CodeEditor.appendItem() {
    appendComposable()
    line { orange { "private fun " }; normal { "AnimatedVisibilityScope." }; blue { "Item" }; normal { "(modifier: Modifier, backgroundColor: Color) {" } }
    line(1) { `val`; normal { " scale " }; `by`; purple { " transition" }; normal { "." }; blue { "animateFloat" }; normal { " { enterExitState ->" } }
    line(2) { `when`; normal { " (enterExitState) {" } }
    line(3) { normal { "EnterExitState." }; purple { "PreEnter" }; normal { " -> " }; cyan { ".9f" } }
    line(3) { normal { "EnterExitState." }; purple { "Visible" }; normal { " -> " }; cyan { "1f" } }
    line(3) { normal { "EnterExitState." }; purple { "PostExit" }; normal { " -> " }; cyan { ".5f" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line(1) { normal { "Box(" } }
    line(2) { normal { "modifier" } }
    line(3) { normal { "." }; blue { "fillMaxHeight" }; normal { "()" } }
    line(3) { normal { "." }; blue { "animateEnterExit" }; normal { "(" } }
    line(4) { cyan { "enter = " }; normal { "slideInVertically(" }; cyan { "initialOffsetY = " }; normal { "{ it })," } }
    line(4) { cyan { "exit = " }; normal { "ExitTransition." }; purple { "None" }; normal { "," } }
    line(3) { normal { ")" } }
    line(3) { normal { "." }; blue { "graphicsLayer" }; normal { " {" } }
    line(4) { purple { "scaleX" }; normal { " = scale" } }
    line(4) { purple { "scaleY" }; normal { " = scale" } }
    line(3) { normal { "}" } }
    line(3) { normal { "." }; blue { "clip" }; normal { "(RoundedCornerShape(" }; cyan { "16" }; normal { "." }; purple { "dp" }; normal { "))" } }
    line(3) { normal { "." }; blue { "background" }; normal { "(backgroundColor)" } }
    line(1) { normal { ")" } }
    line { normal { "}" } }
    line()
}
