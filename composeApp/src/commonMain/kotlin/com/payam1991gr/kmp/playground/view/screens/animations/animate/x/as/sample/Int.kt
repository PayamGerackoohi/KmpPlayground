package com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource
import kotlin.math.ceil

@Composable
fun IntSample(maxCount: Int = 100, spacing: Dp = 2.dp) {
    var isSelected by rememberBoolean(false)
    val count by animateIntAsState(
        if (isSelected) maxCount else 0,
        animationSpec = tween(1000),
    )
    val tag = stringResource(Res.string.animations_animate_int)
    Module(tag, { isSelected = !isSelected }) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(8.dp)
                .content(tag, "Selected: $isSelected, Count: $count")
        ) {
            Bar(count, maxCount, spacing, Modifier.weight(1f))
            Text(
                "$count%".padStart(4, ' '),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Monospace,
            )
        }
    }
}

@Composable
private fun Bar(
    count: Int,
    maxCount: Int,
    spacing: Dp,
    modifier: Modifier = Modifier,
    ratio: Float = .4f,
) {
    val spacingPx = LocalDensity.current.run { spacing.toPx() }
    val color = MaterialTheme.colorScheme.tertiaryContainer
    val m = remember(maxCount) { ceil(maxCount * ratio).toInt() }
    val c = remember(count) { ceil(count * ratio).toInt() }
    Box(modifier
        .height(32.dp)
        .drawWithCache {
            onDrawBehind {
                val (width, height) = size
                val w = (width - spacingPx * (m - 1)) / m
                var x = 0f
                val size = Size(w, height)
                val shift = w + spacingPx
                repeat(c) {
                    drawRect(color, Offset(x, 0f), size)
                    x += shift
                }
            }
        })
}

fun CodeEditor.appendIntSample() {
    appendComposable()
    line { `fun`; blue { " IntSample" }; normal { "(maxCount: Int = " }; cyan { "100" }; normal { ", spacing: Dp = " }; cyan { "2" }; normal { "." }; purple { "dp" }; normal { ") {" } }
    line(1) { `var`; normal { " isSelected " }; `by`; normal { " rememberBoolean(" }; `false`; normal { ")" } }
    line(1) { `val`; normal { " count " }; `by`; normal { " animateIntAsState(" } }
    line(2) { `if`; normal { " (isSelected) maxCount " }; `else`; cyan { " 0" }; normal { "," } }
    line(2) { cyan { "animationSpec = " }; normal { "tween(" }; cyan { "1000" }; normal { ")," } }
    line(1) { normal { ")" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animate_int" }; normal { ")" } }
    line(1) { normal { "Module(tag, { isSelected = !isSelected }) {" } }
    line(2) { normal { "Row(" } }
    line(3) { cyan { "horizontalArrangement = " }; normal { "Arrangement.spacedBy(" }; cyan { "8" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(3) { cyan { "verticalAlignment = " }; normal { "Alignment." }; purple { "CenterVertically" }; normal { "," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier" } }
    line(4) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "8" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { "." }; blue { "content" }; normal { "(tag, " }; green { "\"Selected: " }; orange { "\$" }; normal { "isSelected" }; green { ", Count: " }; orange { "\$" }; normal { "count" }; green { "\"" }; normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Bar(count, maxCount, spacing, Modifier." }; blue { "weight" }; normal { "(" }; cyan { "1f" }; normal { "))" } }
    line(3) { normal { "Text(" } }
    line(4) { green { "\"" }; orange { "\$" }; normal { "count" }; green { "%\"" }; normal { "." }; blue { "padStart" }; normal { "(" }; cyan { "4" }; normal { ", " }; green { "' '" }; normal { ")," } }
    line(4) { cyan { "textAlign = " }; normal { "TextAlign." }; purple { "Center" }; normal { "," } }
    line(4) { cyan { "fontFamily = " }; normal { "FontFamily." }; purple { "Monospace" }; normal { "," } }
    line(3) { normal { ")" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
    appendBar()
}

private fun CodeEditor.appendBar() {
    appendComposable()
    line { orange { "private fun " }; blue { "Bar" }; normal { "(" } }
    line(1) { normal { "count: Int," } }
    line(1) { normal { "maxCount: Int," } }
    line(1) { normal { "spacing: Dp," } }
    line(1) { normal { "modifier: Modifier = Modifier," } }
    line(1) { normal { "ratio: Float = " }; cyan { ".4f" }; normal { "," } }
    line { normal { ") {" } }
    line(1) { `val`; normal { " spacingPx = " }; purple { "LocalDensity" }; normal { "." }; purple { "current" }; normal { "." }; blue { "run" }; normal { " { spacing." }; blue { "toPx" }; normal { "() }" } }
    line(1) { `val`; normal { " color = MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "tertiaryContainer" } }
    line(1) { `val`; normal { " m = remember(maxCount) { ceil(maxCount * ratio).toInt() }" } }
    line(1) { `val`; normal { " c = remember(count) { ceil(count * ratio).toInt() }" } }
    line(1) { normal { "Box(modifier" } }
    line(2) { normal { "." }; blue { "height" }; normal { "(" }; cyan { "32" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(2) { normal { "." }; blue { "drawWithCache" }; normal { "{" } }
    line(3) { normal { "onDrawBehind {" } }
    line(4) { `val`; normal { " (width, height) = " }; purple { "size" } }
    line(4) { `val`; normal { " w = (width - spacingPx * (m - " }; cyan { "1" }; normal { ")) / m" } }
    line(4) { `var`; normal { " x = " }; cyan { "0f" } }
    line(4) { `val`; normal { " size = Size(w, height)" } }
    line(4) { `val`; normal { " shift = w + spacingPx" } }
    line(4) { normal { "repeat(c) {" } }
    line(5) { normal { "drawRect(color, Offset(x, " }; cyan { "0f" }; normal { "), size)" } }
    line(5) { normal { "x += shift" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "})" } }
    line { normal { "}" } }
    line()
}
