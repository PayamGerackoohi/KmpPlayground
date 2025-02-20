package com.payam1991gr.kmp.playground.view.module.editor

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

/**
 * An editor to feed data to the code panel with multi-file view
 * @sample com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibility.Code
 */
@Composable
fun codes(vararg contents: Pair<StringResource, CodeEditor.() -> Unit>) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(1.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(contents) { (header, content) ->
            var visible by remember { mutableStateOf(false) }
            CodeHeader(stringResource(header)) { visible = !visible }
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically(),
            ) {
                CodePanel(rememberCodeEditor { content() }, selfScroll = false)
            }
        }
    }
}

@Composable
fun CodeHeader(header: String, onToggle: () -> Unit) = TextButton(
    onClick = onToggle,
    colors = ButtonDefaults.textButtonColors(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
    ),
    shape = RectangleShape,
    modifier = Modifier.fillMaxWidth()
) {
    Text(
        text = header,
        color = MaterialTheme.colorScheme.onSecondaryContainer,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(12.dp)
    )
}

@Composable
fun CodePanel(editor: CodeEditorData, selfScroll: Boolean = true) {
    val (code, lines) = editor
    val strokeWidth = LocalDensity.current.run { 1.dp.toPx() }
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Ltr,
        LocalTextStyle provides TextStyle(fontFamily = FontFamily.Monospace),
    ) {
        Row(
            if (selfScroll)
                Modifier.fillMaxSize()
                    .horizontalScroll(rememberScrollState())
                    .verticalScroll(rememberScrollState())
            else
                Modifier.fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .drawBehind {
                        val (w, h) = size
                        drawLine(
                            color = CodeEditor.gray,
                            start = Offset(w, 0f),
                            end = Offset(w, h),
                            strokeWidth = strokeWidth,
                        )
                    }
                    .padding(8.dp)
            ) {
                Text(
                    List(lines) { (it + 1).toString() }.joinToString("\n"),
                    color = CodeEditor.gray,
                    textAlign = TextAlign.End,
                    lineHeight = 24.sp,
                )
            }
            Text(
                code,
                lineHeight = 24.sp,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
