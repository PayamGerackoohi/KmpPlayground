package com.payam1991gr.kmp.playground.view.screens.animations.animated.content.sample

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContent.ContentState
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun SimpleSample() {
    var state by remember { mutableStateOf(ContentState.Foo) }
    val tag = stringResource(Res.string.animations_animated_content_simple)
    Module(tag, {
        state = when (state) {
            ContentState.Foo -> ContentState.Bar
            ContentState.Bar -> ContentState.Baz
            ContentState.Baz -> ContentState.Foo
        }
    }) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .content(tag, state.toString())
        ) {
            AnimatedContent(state) {
                when (it) {
                    ContentState.Foo -> Item(0xffffdb00, 200.dp)
                    ContentState.Bar -> Item(0xffff8100, 40.dp)
                    ContentState.Baz -> Item(0xffff4400, modifier = Modifier.size(80.dp, 20.dp))
                }
            }
        }
    }
}

@Composable
private fun Item(color: Long, size: Dp? = null, modifier: Modifier = Modifier) = Box(modifier
    .run { size?.let { size(it) } ?: this }
    .background(Color(color))
)

fun CodeEditor.appendSimpleSample() {
    appendComposable()
    line { `fun`; blue { " SimpleSample" }; normal { "() {" } }
    line(1) { `var`; normal { " state " }; `by`; normal { " remember { mutableStateOf(ContentState." }; purple { "Foo" }; normal { ") }" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_content_simple" }; normal { ")" } }
    line(1) { normal { "Module(tag, {" } }
    line(2) { normal { "state = " }; `when`; normal { " (state) {" } }
    line(3) { normal { "ContentState." }; purple { "Foo" }; normal { " -> ContentState." }; purple { "Bar" } }
    line(3) { normal { "ContentState." }; purple { "Bar" }; normal { " -> ContentState." }; purple { "Baz" } }
    line(3) { normal { "ContentState." }; purple { "Baz" }; normal { " -> ContentState." }; purple { "Foo" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}) {" } }
    line(2) { normal { "Box(" } }
    line(3) { cyan { "contentAlignment =" }; normal { " Alignment." }; purple { "Center" }; normal { "," } }
    line(3) { cyan { "modifier =" }; normal { " Modifier" } }
    line(4) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(4) { normal { "." }; blue { "content" }; normal { "(tag, state.toString())" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "AnimatedContent(state) {" } }
    line(4) { `when`; normal { " (it) {" } }
    line(5) { normal { "ContentState." }; purple { "Foo" }; normal { " -> Item(" }; cyan { "0xffffdb00" }; normal { ", " }; cyan { "200" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(5) { normal { "ContentState." }; purple { "Bar" }; normal { " -> Item(" }; cyan { "0xffff8100" }; normal { ", " }; cyan { "40" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(5) { normal { "ContentState." }; purple { "Baz" }; normal { " -> Item(" }; cyan { "0xffff4400" }; normal { ", " }; cyan { "80" }; normal { "." }; purple { "dp" }; normal { ", " }; cyan { "20" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
    appendItem()
}

private fun CodeEditor.appendItem() {
    appendComposable()
    line { orange { "private fun" }; blue { " Item" }; normal { "(color: Long, size: Dp? = " }; `null`; normal { ", modifier: Modifier = Modifier) = Box(modifier" } }
    line(1) { normal { "." }; blue { "run" }; normal { " { size?." }; blue { "let" }; normal { " { " }; blue { "size" }; normal { "(it) } ?: " }; `this`; normal { " }" } }
    line(1) { normal { "." }; blue { "background" }; normal { "(Color(color))" } }
    line { normal { ")" } }
    line()
}
