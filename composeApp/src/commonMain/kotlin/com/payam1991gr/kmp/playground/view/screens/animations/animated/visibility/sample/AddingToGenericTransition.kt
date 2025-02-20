package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Suppress("SpellCheckingInspection")
private const val LONG_TEXT =
    """Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed eiusmod tempor incididunt labore et dolore magna aliqua.
Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor."""

@Composable
fun AddingToGenericTransitionSample() {
    var selected by rememberBoolean(false)
    val tag = stringResource(Res.string.animations_animated_visibility_adding_to_generic_transition)
    Module(tag, { selected = !selected }) {
        Box(
            Modifier
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainerHighest,
                    shape = RoundedCornerShape(bottomStart = 12.dp, bottomEnd = 12.dp)
                )
                .padding(8.dp)
        ) {
            val selectionTransition = updateTransition(selected)
            val borderColor by selectionTransition.animateColor { isSelected ->
                if (isSelected) Color(0xff03a9f4) else Color.White
            }
            val contentBackground by selectionTransition.animateColor { isSelected ->
                if (isSelected) Color(0xffdbf0fe) else Color.White
            }
            val elevation by selectionTransition.animateDp { isSelected ->
                if (isSelected) 10.dp else 2.dp
            }
            Surface(
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(2.dp, borderColor),
                color = contentBackground,
                elevation = elevation,
                modifier = Modifier.clickable { selected = !selected }
            ) {
                Column(Modifier.fillMaxWidth()) {
                    Header(tag)
                    selectionTransition.AnimatedVisibility(
                        visible = { it },
                        enter = expandVertically(),
                        exit = shrinkVertically()
                    ) {
                        Text(
                            LONG_TEXT,
                            color = Color.Black,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp)
                                .padding(bottom = 16.dp)
                                .semantics { contentDescription = "$tag.Content" }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun Header(tag: String) = Row(
    Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .semantics { contentDescription = "$tag.Header" },
    Arrangement.spacedBy(16.dp),
    Alignment.CenterVertically,
) {
    Box(
        Modifier
            .size(64.dp)
            .background(Color(0xffcdb7f6), CircleShape)
    )
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        TextLine()
        TextLine()
    }
}

@Composable
private fun TextLine() = Box(
    Modifier
        .fillMaxWidth()
        .height(24.dp)
        .background(Color.LightGray)
)

fun CodeEditor.appendAddingToGenericTransitionSample() {
    appendComposable()
    line { `fun`; blue { "AddingToGenericTransitionSample" }; normal { "() {" } }
    line(1) { `var`; normal { " selected " }; `by`; normal { " rememberBoolean(" }; `false`; normal { ")" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_visibility_adding_to_generic_transition" }; normal { ")" } }
    line(1) { normal { "Module(tag, { selected = !selected }) {" } }
    line(2) { normal { "Box(" } }
    line(3) { normal { "Modifier" } }
    line(4) { normal { "." }; blue { "background" }; normal { "(" } }
    line(5) { cyan { "color = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "surfaceContainerHighest" }; normal { "," } }
    line(5) { cyan { "shape = " }; normal { "RoundedCornerShape(" }; cyan { "bottomStart = 12" }; normal { "." }; purple { "dp" }; normal { ", " }; cyan { "bottomEnd = 12" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { ")" } }
    line(4) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "8" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { `val`; normal { " selectionTransition = updateTransition(selected)" } }
    line(3) { `val`; normal { " borderColor " }; `by`; normal { " selectionTransition." }; blue { "animateColor" }; normal { " { isSelected ->" } }
    line(4) { `if`; normal { " (isSelected) Color(" }; cyan { "0xff03a9f4" }; normal { ") " }; `else`; normal { " Color." }; purple { "White" } }
    line(3) { normal { "}" } }
    line(3) { `val`; normal { " contentBackground " }; `by`; normal { " selectionTransition." }; blue { "animateColor" }; normal { " { isSelected ->" } }
    line(4) { `if`; normal { " (isSelected) Color(" }; cyan { "0xffdbf0fe" }; normal { ") " }; `else`; normal { " Color." }; purple { "White" } }
    line(3) { normal { "}" } }
    line(3) { `val`; normal { " elevation " }; `by`; normal { " selectionTransition." }; blue { "animateDp" }; normal { " { isSelected ->" } }
    line(4) { `if`; normal { " (isSelected) " }; cyan { "10" }; normal { "." }; purple { "dp " }; `else`; cyan { " 2" }; normal { "." }; purple { "dp" } }
    line(3) { normal { "}" } }
    line(3) { normal { "Surface(" } }
    line(4) { cyan { "shape = " }; normal { "RoundedCornerShape(" }; cyan { "12" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(4) { cyan { "border = " }; normal { "BorderStroke(" }; cyan { "2" }; normal { "." }; purple { "dp" }; normal { ", borderColor)," } }
    line(4) { cyan { "color = " }; normal { "contentBackground," } }
    line(4) { cyan { "elevation = " }; normal { "elevation," } }
    line(4) { cyan { "modifier = " }; normal { "Modifier." }; blue { "clickable" }; normal { " { selected = !selected }" } }
    line(3) { normal { ") {" } }
    line(4) { normal { "Column(Modifier." }; blue { "fillMaxWidth" }; normal { "()) {" } }
    line(5) { normal { "Header(tag)" } }
    line(5) { normal { "selectionTransition." }; blue { "AnimatedVisibility" }; normal { "(" } }
    line(6) { cyan { "visible = " }; normal { "{ it }," } }
    line(6) { cyan { "enter = " }; normal { "expandVertically()," } }
    line(6) { cyan { "exit = " }; normal { "shrinkVertically()," } }
    line(5) { normal { ") {" } }
    line(6) { normal { "Text(" } }
    line(7) { purple { "LONG_TEXT" }; normal { "," } }
    line(7) { cyan { "color = " }; normal { "Color." }; purple { "Black" }; normal { "," } }
    line(7) { cyan { "modifier = " }; normal { "Modifier" } }
    line(8) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(8) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "horizontal = 16" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(8) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "bottom = 16" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(8) { normal { "." }; blue { "semantics" }; normal { " { " }; purple { "contentDescription" }; normal { " = " }; green { "\"" }; orange { "\$" }; normal { "tag" }; green { ".Content\"" }; normal { " }" } }
    line(6) { normal { ")" } }
    line(5) { normal { "}" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
    appendHeader()
    appendTextLine()
}

private fun CodeEditor.appendHeader() {
    appendComposable()
    line { orange { "private fun " }; blue { "Header" }; normal { "(tag: String) = Row(" } }
    line(1) { normal { "Modifier" } }
    line(2) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(2) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "16" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(2) { normal { "." }; blue { "semantics" }; normal { " { " }; purple { "contentDescription" }; normal { " = " }; green { "\"" }; orange { "\$" }; normal { "tag" }; green { ".Header\"" }; normal { " }," } }
    line(1) { normal { "Arrangement.spacedBy(" }; cyan { "16" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(1) { normal { "Alignment." }; purple { "CenterVertically" }; normal { "," } }
    line { normal { ") {" } }
    line(1) { normal { "Box(" } }
    line(2) { normal { "Modifier" } }
    line(3) { normal { "." }; blue { "size" }; normal { "(" }; cyan { "64" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(3) { normal { "." }; blue { "background" }; normal { "(Color(" }; cyan { "0xffcdb7f6" }; normal { "), " }; purple { "CircleShape" }; normal { ")" } }
    line(1) { normal { ")" } }
    line(1) { normal { "Column(" } }
    line(2) { cyan { "verticalArrangement = " }; normal { "Arrangement.spacedBy(" }; cyan { "16" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(1) { normal { ") {" } }
    line(2) { normal { "TextLine()" } }
    line(2) { normal { "TextLine()" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

private fun CodeEditor.appendTextLine() {
    appendComposable()
    line { orange { "private fun " }; blue { "TextLine" }; normal { "() = Box(" } }
    line(1) { normal { "Modifier" } }
    line(2) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(2) { normal { "." }; blue { "height" }; normal { "(" }; cyan { "24" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(2) { normal { "." }; blue { "background" }; normal { "(Color." }; purple { "LightGray" }; normal { ")" } }
    line { normal { ")" } }
    line()
}
