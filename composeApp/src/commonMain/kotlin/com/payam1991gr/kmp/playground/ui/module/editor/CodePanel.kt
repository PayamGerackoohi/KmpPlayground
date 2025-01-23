package com.payam1991gr.kmp.playground.ui.module.editor

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CodePanel(editor: CodeEditorData) {
    val (code, lines) = editor
    val strokeWidth = LocalDensity.current.run { 1.dp.toPx() }
    CompositionLocalProvider(
        LocalLayoutDirection provides LayoutDirection.Ltr,
        LocalTextStyle provides TextStyle(fontFamily = FontFamily.Monospace),
    ) {
        Row(
            Modifier.fillMaxSize()
                .horizontalScroll(rememberScrollState())
                .verticalScroll(rememberScrollState())
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
