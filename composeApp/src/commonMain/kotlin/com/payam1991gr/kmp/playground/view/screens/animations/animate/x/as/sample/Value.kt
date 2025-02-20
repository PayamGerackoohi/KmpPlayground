package com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample

import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.animation.core.TwoWayConverter
import androidx.compose.animation.core.animateValueAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

data class MySize(val width: Dp, val height: Dp)

@Composable
fun ValueSample() {
    var isEnabled by rememberBoolean()
    val tag = stringResource(Res.string.animations_animate_value)
    Module(tag, { isEnabled = !isEnabled }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .content(tag, if (isEnabled) "State 1" else "State 2")
        ) {
            val mySize = remember(isEnabled) {
                (if (isEnabled) 150 else 100).dp.let { MySize(it, it) }
            }
            val animSize by animateValueAsState(
                mySize, TwoWayConverter(
                    convertToVector = { AnimationVector2D(it.width.value, it.height.value) },
                    convertFromVector = { MySize(it.v1.dp, it.v2.dp) },
                )
            )
            Box(
                Modifier
                    .size(animSize.width, animSize.height)
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            )
        }
    }
}

fun CodeEditor.appendValueSample() {
    appendMySize()
    appendComposable()
    line { `fun`; blue { " ValueSample" }; normal { "() {" } }
    line(1) { `var`; normal { " isEnabled " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animate_value" }; normal { ")" } }
    line(1) { normal { "Module(tag, { isEnabled = !isEnabled }) {" } }
    line(2) { normal { "Column(" } }
    line(3) { cyan { "horizontalAlignment = " }; normal { "Alignment." }; purple { "CenterHorizontally" }; normal { "," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier" } }
    line(4) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(4) { normal { "." }; blue { "content" }; normal { "(tag, " }; `if`; normal { " (isEnabled) " }; green { "\"State 1\" " }; `else`; green { " \"State 2\")" } }
    line(2) { normal { ") {" } }
    line(3) { `val`; normal { " mySize = remember(isEnabled) {" } }
    line(4) { normal { "(" }; `if`; normal { " (isEnabled) " }; cyan { "150 " }; `else`; cyan { " 100" }; normal { ")." }; purple { "dp" }; normal { "." }; blue { "let" }; normal { " { MySize(it, it) }" } }
    line(3) { normal { "}" } }
    line(3) { `val`; normal { " animSize " }; `by`; normal { " animateValueAsState(" } }
    line(4) { normal { "mySize, TwoWayConverter(" } }
    line(5) { cyan { "convertToVector = " }; normal { "{ AnimationVector2D(it." }; purple { "width" }; normal { "." }; purple { "value" }; normal { ", it." }; purple { "height" }; normal { "." }; purple { "value" }; normal { ") }," } }
    line(5) { cyan { "convertFromVector = " }; normal { "{ MySize(it." }; purple { "v1" }; normal { "." }; purple { "dp" }; normal { ", it." }; purple { "v2" }; normal { "." }; purple { "dp" }; normal { ") }," } }
    line(4) { normal { ")" } }
    line(3) { normal { ")" } }
    line(3) { normal { "Box(" } }
    line(4) { normal { "Modifier" } }
    line(5) { normal { "." }; blue { "size" }; normal { "(animSize." }; purple { "width" }; normal { ", animSize." }; purple { "height" }; normal { ")" } }
    line(5) { normal { "." }; blue { "background" }; normal { "(MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "tertiaryContainer" } }
    line(3) { normal { ")" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

private fun CodeEditor.appendMySize() {
    line { orange { "data class " }; normal { "MySize(" }; `val`; purple { " width" }; normal { ": Dp, " }; `val`; purple { " height" }; normal { ": Dp)" } }
    line()
}
