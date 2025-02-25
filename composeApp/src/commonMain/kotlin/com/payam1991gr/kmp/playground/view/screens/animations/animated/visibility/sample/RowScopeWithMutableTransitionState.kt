package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.moduleSize
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun RowScopeWithMutableTransitionStateSample() {
    var visible by rememberBoolean()
    val colors = listOf(
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.surfaceContainer,
        MaterialTheme.colorScheme.tertiaryContainer,
    )
    val tag = stringResource(
        Res.string.animations_animated_visibility_row_scope_with_mutable_transition_state
    )
    Module(tag, { visible = !visible }) {
        Row(
            Modifier
                .moduleSize()
                .content(tag)
                .semantics { stateDescription = if (visible) "Expanded" else "Collapsed" }
        ) {
            repeat(3) {
                AnimatedVisibility(
                    visibleState = remember {
                        MutableTransitionState(initialState = false)
                    }.apply {
                        targetState = visible
                    },
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth(1f / (3 - it))
                            .fillMaxHeight()
                            .background(colors[it])
                    )
                }
            }
        }
    }
}

fun CodeEditor.appendRowScopeWithMutableTransitionStateSample() {
    appendComposable()
    line { `fun`; blue { " RowScopeWithMutableTransitionStateSample" }; normal { "() {" } }
    line(1) { `var`; normal { " visible " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " colors = listOf(" } }
    line(2) { normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "primaryContainer" }; normal { "," } }
    line(2) { normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "surfaceContainer" }; normal { "," } }
    line(2) { normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "tertiaryContainer" }; normal { "," } }
    line(1) { normal { ")" } }
    line(1) { `val`; normal { " tag = stringResource(" } }
    line(2) { normal { "Res.string." }; purple { "animations_animated_visibility_row_scope_with_mutable_transition_state" } }
    line(1) { normal { ")" } }
    line(1) { normal { "Module(tag, { visible = !visible }) {" } }
    line(2) { normal { "Row(" } }
    line(3) { normal { "Modifier" } }
    line(4) { normal { "." }; blue { "moduleSize" }; normal { "()" } }
    line(4) { normal { "." }; blue { "testTag" }; normal { "(" }; green { "\"" }; orange { "\$" }; normal { "tag" }; green { ".Content\"" }; normal { ")" } }
    line(4) { normal { "." }; blue { "semantics" }; normal { " { " }; purple { "stateDescription" }; normal { " = " }; `if`; normal { " (visible) " }; green { "\"Expanded\" " }; `else`; green { " \"Collapsed\"" }; normal { " }" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "repeat(" }; cyan { "3" }; normal { ") {" } }
    line(4) { blue { "AnimatedVisibility" }; normal { "(" } }
    line(5) { cyan { "visibleState = " }; normal { "remember {" } }
    line(6) { normal { "MutableTransitionState(" }; cyan { "initialState = " }; `false`; normal { ")" } }
    line(5) { normal { "}." }; blue { "apply" }; normal { " {" } }
    line(6) { purple { "targetState" }; normal { " = visible" } }
    line(5) { normal { "}," } }
    line(4) { normal { ") {" } }
    line(5) { normal { "Box(" } }
    line(6) { normal { "Modifier" } }
    line(7) { normal { "." }; blue { "fillMaxWidth" }; normal { "(" }; cyan { "1f" }; normal { " / (" }; cyan { "3" }; normal { " - it))" } }
    line(7) { normal { "." }; blue { "fillMaxHeight" }; normal { "()" } }
    line(7) { normal { "." }; blue { "background" }; normal { "(colors[it])" } }
    line(5) { normal { ")" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
