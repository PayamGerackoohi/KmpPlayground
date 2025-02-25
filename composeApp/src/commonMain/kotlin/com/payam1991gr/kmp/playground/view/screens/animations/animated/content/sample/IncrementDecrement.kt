package com.payam1991gr.kmp.playground.view.screens.animations.animated.content.sample

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun IncrementDecrementSample() {
    var count by remember { mutableStateOf(0) }
    val tag = stringResource(Res.string.animations_animated_content_increment_decrement)
    Module(tag, { count = 0 }) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
                .content(tag, count.toString())
        ) {
            val cornerRadius = 24.dp
            Icon(
                Icons.Default.Remove,
                stringResource(Res.string.decrement),
                tint = MaterialTheme.colorScheme.onTertiaryContainer,
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = cornerRadius,
                            bottomStart = cornerRadius,
                        )
                    )
                    .background(color = MaterialTheme.colorScheme.tertiaryContainer)
                    .clickable { count-- }
                    .minimumInteractiveComponentSize()
            )
            AnimatedContent(
                targetState = count,
                transitionSpec = {
                    if (targetState > initialState) {
                        slideInVertically { -it } + fadeIn() togetherWith
                                slideOutVertically { it } + fadeOut()
                    } else {
                        slideInVertically { it } + fadeIn() togetherWith
                                slideOutVertically { -it } + fadeOut()
                    }.using(SizeTransform(clip = false))
                }
            ) { targetCount ->
                Text(
                    "$targetCount",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.width(64.dp)
                )
            }
            Icon(
                Icons.Default.Add,
                stringResource(Res.string.increment),
                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topEnd = cornerRadius,
                            bottomEnd = cornerRadius,
                        )
                    )
                    .background(color = MaterialTheme.colorScheme.secondaryContainer)
                    .clickable { count++ }
                    .minimumInteractiveComponentSize()
            )
        }
    }
}

fun CodeEditor.appendIncrementDecrementSample() {
    appendComposable()
    line { `fun`; blue { " IncrementDecrementSample" }; normal { "() {" } }
    line(1) { `var`; normal { " count " }; `by`; normal { " remember { mutableStateOf(" }; cyan { "0" }; normal { ") }" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_content_increment_decrement" }; normal { ")" } }
    line(1) { normal { "Module(tag, { count = " }; cyan { "0" }; normal { " }) {" } }
    line(2) { normal { "Row(" } }
    line(3) { cyan { "horizontalArrangement =" }; normal { " Arrangement." }; purple { "Center" }; normal { "," } }
    line(3) { cyan { "verticalAlignment =" }; normal { " Alignment." }; purple { "CenterVertically" }; normal { "," } }
    line(3) { cyan { "modifier =" }; normal { " Modifier" } }
    line(4) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(4) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "top = 16" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { "." }; blue { "content" }; normal { "(tag, count.toString())" } }
    line(2) { normal { ") {" } }
    line(3) { `val`; normal { " cornerRadius = " }; cyan { "24" }; normal { "." }; purple { "dp" } }
    line(3) { normal { "Icon(" } }
    line(4) { normal { "Icons." }; purple { "Default" }; normal { "." }; purple { "Remove" }; normal { "," } }
    line(4) { normal { "stringResource(Res.string." }; purple { "decrement" }; normal { ")," } }
    line(4) { cyan { "tint =" }; normal { " MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "onTertiaryContainer" }; normal { "," } }
    line(4) { cyan { "modifier =" }; normal { " Modifier" } }
    line(5) { normal { "." }; blue { "clip" }; normal { "(" } }
    line(6) { normal { "RoundedCornerShape(" } }
    line(7) { cyan { "topStart =" }; normal { " cornerRadius," } }
    line(7) { cyan { "bottomStart =" }; normal { " cornerRadius," } }
    line(6) { normal { ")" } }
    line(5) { normal { ")" } }
    line(5) { normal { "." }; blue { "background" }; normal { "(" }; cyan { "color =" }; normal { " MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "tertiaryContainer" }; normal { ")" } }
    line(5) { normal { "." }; blue { "clickable" }; normal { " { count-- }" } }
    line(5) { normal { "." }; blue { "minimumInteractiveComponentSize" }; normal { "()" } }
    line(3) { normal { ")" } }
    line(3) { normal { "AnimatedContent(" } }
    line(4) { cyan { "targetState =" }; normal { " count," } }
    line(4) { cyan { "transitionSpec =" }; normal { " {" } }
    line(5) { `if`; normal { " (" }; purple { "targetState" }; normal { " > " }; purple { "initialState" }; normal { ") {" } }
    line(6) { normal { "slideInVertically { -it } + fadeIn() " }; blue { "togetherWith" } }
    line(8) { normal { "slideOutVertically { it } + fadeOut()" } }
    line(5) { normal { "} " }; `else`; normal { " {" } }
    line(6) { normal { "slideInVertically { it } + fadeIn() " }; blue { "togetherWith" } }
    line(8) { normal { "slideOutVertically { -it } + fadeOut()" } }
    line(5) { normal { "}." }; blue { "using" }; normal { "(SizeTransform(" }; cyan { "clip = " }; `false`; normal { "))" } }
    line(4) { normal { "}" } }
    line(3) { normal { ") { targetCount ->" } }
    line(4) { normal { "Text(" } }
    line(5) { green { "\"" }; orange { "\$" }; normal { "targetCount" }; green { "\"" }; normal { "," } }
    line(5) { cyan { "textAlign =" }; normal { " TextAlign." }; purple { "Center" }; normal { "," } }
    line(5) { cyan { "style =" }; normal { " MaterialTheme." }; purple { "typography" }; normal { "." }; purple { "titleLarge" }; normal { "," } }
    line(5) { cyan { "modifier =" }; normal { " Modifier." }; blue { "width" }; normal { "(" }; cyan { "64" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { ")" } }
    line(3) { normal { "}" } }
    line(3) { normal { "Icon(" } }
    line(4) { normal { "Icons." }; purple { "Default" }; normal { "." }; purple { "Add" }; normal { "," } }
    line(4) { normal { "stringResource(Res.string." }; purple { "increment" }; normal { ")," } }
    line(4) { cyan { "tint =" }; normal { " MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "onSecondaryContainer" }; normal { "," } }
    line(4) { cyan { "modifier = " }; normal { "Modifier" } }
    line(5) { normal { "." }; blue { "clip" }; normal { "(" } }
    line(6) { normal { "RoundedCornerShape(" } }
    line(7) { cyan { "topEnd =" }; normal { " cornerRadius," } }
    line(7) { cyan { "bottomEnd =" }; normal { " cornerRadius," } }
    line(6) { normal { ")" } }
    line(5) { normal { ")" } }
    line(5) { normal { "." }; blue { "background" }; normal { "(" }; cyan { "color =" }; normal { " MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "secondaryContainer" }; normal { ")" } }
    line(5) { normal { "." }; blue { "clickable" }; normal { " { count++ }" } }
    line(5) { normal { "." }; blue { "minimumInteractiveComponentSize" }; normal { "()" } }
    line(3) { normal { ")" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
