package com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample

import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.animateOffsetAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.moduleSize
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import kotlin.math.roundToInt

@Composable
fun OffsetSample() {
    var selected by rememberBoolean()
    val tag = stringResource(Res.string.animations_animate_offset)
    Module(tag, { selected = !selected }) {
        val offset by animateOffsetAsState(
            (if (selected) 0f else -40f).let { Offset(it, it) }
        )
        val intOffset by animateIntOffsetAsState(
            (if (selected) 0 else 100).let { IntOffset(it, it) }
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .moduleSize()
                .clipToBounds()
                .background(MaterialTheme.colorScheme.surfaceContainerHighest)
                .padding(16.dp)
                .content(tag, selected.toState())
        ) {
            Item(
                MaterialTheme.colorScheme.secondaryContainer,
                Modifier.offset { intOffset }
            )
            Item(
                MaterialTheme.colorScheme.tertiaryContainer,
                Modifier.offset { offset.run { IntOffset(x.roundToInt(), y.roundToInt()) } }
            )
        }
    }
}

private fun Boolean.toState() = if (this) "State 1" else "State 2"

@Composable
private fun RowScope.Item(color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier
            .weight(1f)
            .fillMaxHeight()
            .background(color)
    )
}

fun CodeEditor.appendOffsetSample() {
    appendComposable()
    line { `fun`; blue { " OffsetSample" }; normal { "() {" } }
    line(1) { `var`; normal { " selected " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animate_offset" }; normal { ")" } }
    line(1) { normal { "Module(tag, { selected = !selected }) {" } }
    line(2) { `val`; normal { " offset " }; `by`; normal { " animateOffsetAsState(" } }
    line(3) { normal { "(" }; `if`; normal { " (selected) " }; cyan { "0f " }; `else`; normal { " -" }; cyan { "40f" }; normal { ")." }; blue { "let" }; normal { " { Offset(it, it) }" } }
    line(2) { normal { ")" } }

    line(2) { `val`; normal { " intOffset " }; `by`; normal { " animateIntOffsetAsState(" } }
    line(3) { normal { "(" }; `if`; normal { " (selected) " }; cyan { "0 " }; `else`; cyan { "100" }; normal { ")." }; blue { "let" }; normal { " { IntOffset(it, it) }" } }
    line(2) { normal { ")" } }
    line(2) { normal { "Row(" } }
    line(3) { cyan { "horizontalArrangement = " }; normal { "Arrangement.spacedBy(" }; cyan { "16" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier" } }
    line(4) { normal { "." }; blue { "moduleSize" }; normal { "()" } }
    line(4) { normal { "." }; blue { "clipToBounds" }; normal { "()" } }
    line(4) { normal { "." }; blue { "background" }; normal { "(MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "surfaceContainerHighest" }; normal { ")" } }
    line(4) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "16" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { "." }; blue { "content" }; normal { "(tag, selected." }; blue { "toState" }; normal { "())" } }
    line(2) { normal { ") {" } }
    line(3) { blue { "Item" }; normal { "(" } }
    line(4) { normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "secondaryContainer" }; normal { "," } }
    line(4) { normal { "Modifier." }; blue { "offset" }; normal { " { intOffset }" } }
    line(3) { normal { ")" } }
    line(3) { blue { "Item" }; normal { "(" } }
    line(4) { normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "tertiaryContainer" }; normal { "," } }
    line(4) { normal { "Modifier." }; blue { "offset" }; normal { " { offset." }; blue { "run " }; normal { "{ IntOffset(" }; purple { "x" }; normal { "." }; blue { "roundToInt" }; normal { "(), " }; purple { "y" }; normal { "." }; blue { "roundToInt" }; normal { "()) } }" } }
    line(3) { normal { ")" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
    appendBoolean_toState()
    appendRowScope_Item()
}

@Suppress("FunctionName")
private fun CodeEditor.appendBoolean_toState() {
    line { orange { "private fun " }; normal { "Boolean." }; blue { "toState" }; normal { "() = " }; `if`; normal { " (" }; `this`; normal { ") " }; green { "\"State 1\" " }; `else`; green { " \"State 2\"" } }
    line()
}

@Suppress("FunctionName")
private fun CodeEditor.appendRowScope_Item() {
    appendComposable()
    line { orange { "private fun " }; normal { "RowScope." }; blue { "Item" }; normal { "(color: Color, modifier: Modifier = Modifier) {" } }
    line(1) { normal { "Box(" } }
    line(2) { normal { "modifier" } }
    line(3) { normal { "." }; blue { "weight" }; normal { "(" }; cyan { "1f" }; normal { ")" } }
    line(3) { normal { "." }; blue { "fillMaxHeight" }; normal { "()" } }
    line(3) { normal { "." }; blue { "background" }; normal { "(color)" } }
    line(1) { normal { ")" } }
    line { normal { "}" } }
    line()
}
