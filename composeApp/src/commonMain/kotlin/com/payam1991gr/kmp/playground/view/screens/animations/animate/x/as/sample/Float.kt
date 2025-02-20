package com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.moduleSize
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun FloatSample() {
    var visible by rememberBoolean()
    val tag = stringResource(Res.string.animations_animate_float)
    Module(tag, { visible = !visible }) {
        val alpha by animateFloatAsState(if (visible) 1f else 0f)
        Box(
            Modifier
                .moduleSize()
                .graphicsLayer { this.alpha = alpha }
                .background(MaterialTheme.colorScheme.tertiaryContainer)
                .content(tag, visible.toState())
        )
    }
}

@Composable
private fun Boolean.toState() = stringResource(
    if (this) Res.string.opaque
    else Res.string.transparent
)

fun CodeEditor.appendFloatSample() {
    appendComposable()
    line { `fun`; blue { " FloatSample" }; normal { "() {" } }
    line(1) { `var`; normal { " visible " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animate_float" }; normal { ")" } }
    line(1) { normal { "Module(tag, { visible = !visible }) {" } }
    line(2) { `val`; normal { " alpha " }; `by`; normal { " animateFloatAsState(" }; `if`; normal { " (visible) " }; cyan { "1f " }; `else`; cyan { " 0f" }; normal { ")" } }
    line(2) { normal { "Box(" } }
    line(3) { normal { "Modifier" } }
    line(4) { normal { "." }; blue { "moduleSize" }; normal { "()" } }
    line(4) { normal { "." }; blue { "graphicsLayer " }; normal { "{ " }; `this`; normal { "." }; purple { "alpha" }; normal { " = alpha }" } }
    line(4) { normal { "." }; blue { "background" }; normal { "(MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "tertiaryContainer" }; normal { ")" } }
    line(4) { normal { "." }; blue { "content" }; normal { "(tag, visible." }; blue { "toState" }; normal { "())" } }
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
    line(1) { `if`; normal { " (" }; `this`; normal { ") Res.string." }; purple { "opaque" } }
    line(1) { `else`; normal { " Res.string." }; purple { "transparent" } }
    line { normal { ")" } }
    line()
}
