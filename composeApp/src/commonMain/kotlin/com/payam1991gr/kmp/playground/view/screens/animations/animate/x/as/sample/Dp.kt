package com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun DpSample() {
    var collapsed by rememberBoolean()
    val tag = stringResource(Res.string.animations_animate_dp)
    Module(tag, { collapsed = !collapsed }) {
        val height by animateDpAsState(if (collapsed) 100.dp else 150.dp)
        Box(
            Modifier
                .fillMaxWidth()
                .requiredHeight(height)
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .content(tag, collapsed.toState())
        )
    }
}

@Composable
private fun Boolean.toState() = stringResource(
    if (this) Res.string.collapsed
    else Res.string.expanded
)

fun CodeEditor.appendDpSample() {
    appendComposable()
    line { `fun`; blue { " DpSample" }; normal { "() {" } }
    line(1) { `var`; normal { " collapsed " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animate_dp" }; normal { ")" } }
    line(1) { normal { "Module(tag, { collapsed = !collapsed }) {" } }
    line(2) { `val`; normal { " height " }; `by`; normal { " animateDpAsState(" }; `if`; normal { " (collapsed) " }; cyan { "100" }; normal { "." }; purple { "dp " }; `else`; cyan { " 150" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(2) { normal { "Box(" } }
    line(3) { normal { "Modifier" } }
    line(4) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(4) { normal { "." }; blue { "requiredHeight" }; normal { "(height)" } }
    line(4) { normal { "." }; blue { "background" }; normal { "(MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "tertiaryContainer" }; normal { ")" } }
    line(4) { normal { "." }; blue { "content" }; normal { "(tag, collapsed." }; blue { "toState" }; normal { "())" } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
    appendBoolean_toState()
}

@Suppress("FunctionName")
private fun CodeEditor.appendBoolean_toState() {
    appendComposable()
    line { orange { "private fun " }; normal { "Boolean." }; blue { "toState" }; normal { "() = stringResource(" } }
    line(1) { `if`; normal { " (" }; `this`; normal { ") Res.string." }; purple { "collapsed" } }
    line(1) { `else`; normal { " Res.string." }; purple { "expanded" } }
    line { normal { ")" } }
    line()
}
