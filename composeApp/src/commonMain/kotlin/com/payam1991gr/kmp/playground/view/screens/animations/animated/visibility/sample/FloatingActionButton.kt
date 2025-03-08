package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.moduleSize
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun FloatingActionButtonSample() {
    var expanded by rememberBoolean(false)
    val tag = stringResource(Res.string.animations_animated_visibility_floating_action_button)
    Module(tag, { expanded = !expanded }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .moduleSize()
                .content(tag)
        ) {
            FloatingActionButton({ expanded = !expanded }) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = stringResource(Res.string.favorite),
                        tint = MaterialTheme.colorScheme.surface,
                        modifier = Modifier.semantics {
                            stateDescription = if (expanded) "Expanded" else "Collapsed"
                        }
                    )
                    AnimatedVisibility(expanded) {
                        Text(
                            stringResource(Res.string.favorite),
                            color = MaterialTheme.colorScheme.surface,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

fun CodeEditor.appendFloatingActionButtonSample() {
    appendComposable()
    line { `fun`; blue { " FloatingActionButtonSample" }; normal { "() {" } }
    line(1) { `var`; normal { " expanded " }; `by`; normal { " remember { mutableStateOf(" }; _false; normal { ") }" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_visibility_floating_action_button" }; normal { ")" } }
    line(1) { normal { "Module(tag, { expanded = !expanded }) {" } }
    line(2) { normal { "Column(" } }
    line(3) { cyan { "horizontalAlignment = " }; normal { "Alignment." }; purple { "CenterHorizontally" }; normal { "," } }
    line(3) { cyan { "verticalArrangement = " }; normal { "Arrangement." }; purple { "Center" }; normal { "," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier" } }
    line(4) { normal { "." }; blue { "moduleSize" }; normal { "()" } }
    line(4) { normal { "." }; blue { "testTag" }; normal { "(" }; green { "\"" }; orange { "\$" }; normal { "tag" }; green { ".Content\"" }; normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "FloatingActionButton({ expanded = !expanded }) {" } }
    line(4) { normal { "Row(" } }
    line(5) { cyan { "verticalAlignment = " }; normal { "Alignment." }; purple { "CenterVertically" }; normal { "," } }
    line(5) { cyan { "modifier = " }; normal { "Modifier." }; blue { "padding" }; normal { "(" }; cyan { "horizontal = 16" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { ") {" } }
    line(5) { normal { "Icon(" } }
    line(6) { cyan { "imageVector = " }; normal { "Icons." }; purple { "Default" }; normal { "." }; purple { "Favorite" }; normal { "," } }
    line(6) { cyan { "contentDescription = " }; normal { "stringResource(Res.string." }; purple { "favorite" }; normal { ")," } }
    line(6) { cyan { "tint = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "surface" }; normal { "," } }
    line(6) { cyan { "modifier = " }; normal { "Modifier." }; blue { "semantics" }; normal { " {" } }
    line(7) { purple { "stateDescription = " }; `if`; normal { " (expanded) " }; green { "\"Expanded\" " }; `else`; green { " \"Collapsed\"" } }
    line(6) { normal { "}" } }
    line(5) { normal { ")" } }
    line(5) { blue { "AnimatedVisibility" }; normal { "(expanded) {" } }
    line(6) { normal { "Text(" } }
    line(7) { normal { "stringResource(Res.string." }; purple { "favorite" }; normal { ")," } }
    line(7) { cyan { "color = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "surface" }; normal { "," } }
    line(7) { cyan { "modifier = " }; normal { "Modifier." }; blue { "padding" }; normal { "(" }; cyan { "start = 8" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(6) { normal { ")" } }
    line(5) { normal { "}" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
