package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.moduleSize
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun AnimateEnterExitPartialContentSample() {
    var visible by rememberBoolean()
    val shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
    val tag = stringResource(
        Res.string.animations_animated_visibility_animate_enter_exit_partial_content
    )
    Module(tag, { visible = !visible }) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(),
            exit = fadeOut(),
        ) {
            Box(
                Modifier
                    .moduleSize()
                    .background(MaterialTheme.colorScheme.scrim, shape)
            ) {
                Box(
                    Modifier
                        .fillMaxSize()
                        .align(Alignment.TopStart)
                        .animateEnterExit(
                            enter = slideInVertically(),
                            exit = slideOutVertically(),
                        )
                        .clip(shape)
                        .background(Color.White)
                ) {
                    Module.Content(tag)
                }
            }
        }
    }
}

fun CodeEditor.appendAnimateEnterExitPartialContentSample() {
    appendComposable()
    line { `fun`; blue { "AnimateEnterExitPartialContentSample" }; normal { "() {" } }
    line(1) { `var`; normal { " visible " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " shape = RoundedCornerShape(" }; cyan { "bottomStart = 16" }; normal { "." }; purple { "dp" }; normal { ", " }; cyan { "bottomEnd = 16" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(1) { `val`; normal { " tag = stringResource(" } }
    line(2) { normal { "Res.string." }; purple { "animations_animated_visibility_animate_enter_exit_partial_content" } }
    line(1) { normal { ")" } }
    line(1) { normal { "Module(tag, { visible = !visible }) {" } }
    line(2) { normal { "AnimatedVisibility(" } }
    line(3) { cyan { "visible = " }; normal { "visible," } }
    line(3) { cyan { "enter = " }; normal { "fadeIn()," } }
    line(3) { cyan { "exit = " }; normal { "fadeOut()," } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Box(" } }
    line(4) { normal { "Modifier" } }
    line(5) { normal { "." }; blue { "moduleSize" }; normal { "()" } }
    line(5) { normal { "." }; blue { "background" }; normal { "(MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "scrim" }; normal { ", shape)" } }
    line(3) { normal { ") {" } }
    line(4) { normal { "Box(" } }
    line(5) { normal { "Modifier" } }
    line(6) { normal { "." }; blue { "fillMaxSize" }; normal { "()" } }
    line(6) { normal { "." }; blue { "align" }; normal { "(Alignment." }; purple { "TopStart" }; normal { ")" } }
    line(6) { normal { "." }; blue { "animateEnterExit" }; normal { "(" } }
    line(7) { cyan { "enter = " }; normal { "slideInVertically()," } }
    line(7) { cyan { "exit = " }; normal { "slideOutVertically()," } }
    line(6) { normal { ")" } }
    line(6) { normal { "." }; blue { "clip" }; normal { "(shape)" } }
    line(6) { normal { "." }; blue { "background" }; normal { "(Color." }; purple { "White" }; normal { ")" } }
    line(4) { normal { ") {" } }
    line(5) { normal { "Module.Content(tag)" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
