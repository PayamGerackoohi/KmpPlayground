package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.moduleSize
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun RowSample() {
    var index by remember { mutableStateOf(0) }
    val colors = listOf(Color.Red, Color.Green, Color.Blue)
    val tag = stringResource(Res.string.animations_animated_visibility_row)
    fun onClick() {
        index = (index + 1) % colors.size
    }
    Module(tag, ::onClick) {
        Row(
            Modifier
                .moduleSize()
                .clickable { onClick() }
                .content(tag)
                .semantics { stateDescription = "State ${index + 1}" }
        ) {
            colors.forEachIndexed { i, color ->
                AnimatedVisibility(i <= index) {
                    Box(
                        Modifier
                            .fillMaxWidth(1f / (3 - i))
                            .fillMaxHeight()
                            .background(color)
                    )
                }
            }
        }
    }
}

fun CodeEditor.appendRowSample() {
    appendComposable()
    line { `fun`; blue { " RowSample" }; normal { "() {" } }
    line(1) { `var`; normal { " index " }; `by`; normal { " remember { mutableStateOf(" }; cyan { "0" }; normal { ") }" } }
    line(1) { `val`; normal { " colors = listOf(Color." }; purple { "Red" }; normal { ", Color." }; purple { "Green" }; normal { ", Color." }; purple { "Blue" }; normal { ")" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_visibility_row" }; normal { ")" } }
    line(1) { `fun`; blue { " onClick" }; normal { "() {" } }
    line(2) { normal { "index = (index + " }; cyan { "1" }; normal { ") % colors." }; purple { "size" } }
    line(1) { normal { "}" } }
    line(1) { normal { "Module(tag, ::onClick) {" } }
    line(2) { normal { "Row(" } }
    line(3) { normal { "Modifier" } }
    line(4) { normal { "." }; blue { "moduleSize" }; normal { "()" } }
    line(4) { normal { "." }; blue { "clickable" }; normal { " { onClick() }" } }
    line(4) { normal { "." }; blue { "testTag" }; normal { "(" }; green { "\"" }; orange { "\$" }; normal { "tag" }; green { ".Content\"" }; normal { ")" } }
    line(4) { normal { "." }; blue { "semantics" }; normal { " { " }; purple { "stateDescription" }; normal { " = " }; green { "\"State " }; orange { "\${" }; normal { "index + " }; cyan { "1" }; orange { "}" }; green { "\"" }; normal { " }" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "colors." }; blue { "forEachIndexed" }; normal { " { i, color ->" } }
    line(4) { blue { "AnimatedVisibility" }; normal { "(i <= index) {" } }
    line(5) { normal { "Box(" } }
    line(6) { normal { "Modifier" } }
    line(7) { normal { "." }; blue { "fillMaxWidth" }; normal { "(" }; cyan { "1f" }; normal { " / (" }; cyan { "3" }; normal { " - i))" } }
    line(7) { normal { "." }; blue { "fillMaxHeight" }; normal { "()" } }
    line(7) { normal { "." }; blue { "background" }; normal { "(color)" } }
    line(5) { normal { ")" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
