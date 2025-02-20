package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.expandIn
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.platform.testTag
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
fun ScaledEnterExitSample() {
    var visible by rememberBoolean()
    var showGreen by rememberBoolean()
    var showRed by rememberBoolean()
    val tag = stringResource(Res.string.animations_animated_visibility_scaled_enter_exit)
    Module(tag, { visible = !visible }) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically { -it },
            exit = slideOutVertically { -it },
            modifier = Modifier
                .clipToBounds()
                .content(tag)
        ) {
            Column(Modifier.moduleSize()) {
                Controls(
                    toggleGreen = { showGreen = !showGreen },
                    toggleRed = { showRed = !showRed },
                )
                Content(
                    tag = tag,
                    showGreen = showGreen,
                    showRed = showRed,
                )
            }
        }
    }
}

@Composable
private fun Controls(
    toggleGreen: () -> Unit,
    toggleRed: () -> Unit,
) = Row(
    horizontalArrangement = Arrangement.spacedBy(4.dp),
    modifier = Modifier.fillMaxWidth()
) {
    ControlButton(
        label = stringResource(Res.string.green),
        color = MaterialTheme.colorScheme.secondaryContainer,
        toggle = toggleGreen,
    )
    ControlButton(
        label = stringResource(Res.string.red),
        color = MaterialTheme.colorScheme.tertiaryContainer,
        toggle = toggleRed,
    )
}

@Composable
fun RowScope.ControlButton(label: String, color: Color, toggle: () -> Unit) {
    Button(
        onClick = toggle,
        colors = ButtonDefaults.buttonColors(containerColor = color),
        modifier = Modifier.weight(1f)
    ) {
        Text(label)
    }
}

@Composable
private fun Content(tag: String, showGreen: Boolean, showRed: Boolean) = Row(
    horizontalArrangement = Arrangement.spacedBy(4.dp),
) {
    ContentColumn(
        visible = showGreen,
        enter = scaleIn() + expandVertically(expandFrom = Alignment.CenterVertically),
        exit = scaleOut() + shrinkVertically(shrinkTowards = Alignment.CenterVertically),
        color = MaterialTheme.colorScheme.secondaryContainer,
        tag = "$tag.GreenBox",
    )
    ContentColumn(
        visible = showRed,
        enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                fadeIn() +
                expandIn(expandFrom = Alignment.TopStart),
        exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                fadeOut() +
                shrinkOut(shrinkTowards = Alignment.TopStart),
        color = MaterialTheme.colorScheme.tertiaryContainer,
        tag = "$tag.RedBox",
    )
}

@Composable
private fun RowScope.ContentColumn(
    visible: Boolean,
    enter: EnterTransition,
    exit: ExitTransition,
    color: Color,
    tag: String,
) = Column(Modifier.weight(1f)) {
    AnimatedVisibility(visible, Modifier.testTag(tag), enter, exit) { Item(color) }
}

@Composable
private fun Item(color: Color) = Box(
    Modifier
        .fillMaxSize()
        .background(color, RoundedCornerShape(24.dp))
)

fun CodeEditor.appendScaledEnterExitSample() {
    appendComposable()
    line { `fun`; blue { "ScaledEnterExitSample" }; normal { "() {" } }
    line(1) { `var`; normal { " visible " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `var`; normal { " showGreen " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `var`; normal { " showRed " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_visibility_scaled_enter_exit" }; normal { ")" } }
    line(1) { normal { "Module(tag, { visible = !visible }) {" } }
    line(2) { normal { "AnimatedVisibility(" } }
    line(3) { cyan { "visible = " }; normal { "visible," } }
    line(3) { cyan { "enter = " }; normal { "slideInVertically { -it }," } }
    line(3) { cyan { "exit = " }; normal { "slideOutVertically { -it }," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier" } }
    line(4) { normal { "." }; blue { "clipToBounds" }; normal { "()" } }
    line(4) { normal { "." }; blue { "testTag" }; normal { "(" }; green { "\"" }; orange { "\$" }; normal { "tag" }; green { ".Content\"" }; normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Column(Modifier." }; blue { "moduleSize" }; normal { "()) {" } }
    line(4) { normal { "Controls(" } }
    line(5) { cyan { "toggleGreen = " }; normal { "{ showGreen = !showGreen }," } }
    line(5) { cyan { "toggleRed = " }; normal { "{ showRed = !showRed }," } }
    line(4) { normal { ")" } }
    line(4) { normal { "Content(" } }
    line(5) { cyan { "tag = " }; normal { "tag," } }
    line(5) { cyan { "showGreen = " }; normal { "showGreen," } }
    line(5) { cyan { "showRed = " }; normal { "showRed," } }
    line(4) { normal { ")" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
