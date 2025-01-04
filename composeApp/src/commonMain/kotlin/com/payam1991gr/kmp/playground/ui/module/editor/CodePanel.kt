package com.payam1991gr.kmp.playground.ui.module.editor

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun CodePanel(editor: CodeEditorData) {
    val (code, lines) = editor
    val strokeWidth = LocalDensity.current.run { 1.dp.toPx() }
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
        Row(
            Modifier.fillMaxSize()
                .horizontalScroll(rememberScrollState())
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                horizontalAlignment = Alignment.End,
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
                    .padding(top = 14.dp)
                    .padding(horizontal = 8.dp)
            ) {
                repeat(lines) {
                    Text(
                        (it + 1).toString(),
                        color = CodeEditor.gray,
                        style = TextStyle(fontFamily = FontFamily.Monospace),
                        modifier = Modifier.height(24.dp)
                    )
                }
            }
            Text(code, Modifier.padding(8.dp))
        }
    }
}
