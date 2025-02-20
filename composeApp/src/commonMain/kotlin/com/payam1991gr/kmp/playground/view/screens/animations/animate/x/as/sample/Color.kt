package com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.moduleSize
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun ColorSample() {
    var isPrimary by rememberBoolean(true)
    val tag = stringResource(Res.string.animations_animate_color)
    Module(tag, { isPrimary = !isPrimary }) {
        val color by animateColorAsState(
            if (isPrimary) MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.tertiaryContainer
        )
        Box(
            Modifier
                .moduleSize()
                .background(color)
                .content(tag, if (isPrimary) "State 1" else "State 2")
        )
    }
}

fun CodeEditor.appendColorSample() {
    appendComposable()
    line { `fun`; blue { " ColorSample" }; normal { "() {" } }
    line(1) { `var`; normal { " isPrimary " }; `by`; normal { " rememberBoolean(" }; `true`; normal { ")" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animate_color" }; normal { ")" } }
    line(1) { normal { "Module(tag, { isPrimary = !isPrimary }) {" } }
    line(2) { `val`; normal { " color " }; `by`; normal { " animateColorAsState(" } }
    line(3) { `if`; normal { " (isPrimary) MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "secondaryContainer" } }
    line(3) { `else`; normal { " MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "tertiaryContainer" } }
    line(2) { normal { ")" } }
    line(2) { normal { "Box(" } }
    line(3) { normal { "Modifier" } }
    line(4) { normal { "." }; blue { "moduleSize" }; normal { "()" } }
    line(4) { normal { "." }; blue { "background" }; normal { "(color)" } }
    line(4) { normal { "." }; blue { "content" }; normal { "(tag, " }; `if`; normal { " (isPrimary) " }; green { "\"State 1\" " }; `else`; green { " \"State 2\"" }; normal { ")" } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
