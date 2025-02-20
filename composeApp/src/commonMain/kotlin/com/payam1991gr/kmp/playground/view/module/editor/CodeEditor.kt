package com.payam1991gr.kmp.playground.view.module.editor

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString

interface CodeEditor {
    companion object {
        val gray = Color(0xFF4C5058)
    }

    val lines: Int
    fun CodeEditor.line(tabs: Int = 0, block: CodeEditor.() -> Unit = {}): Any
    fun CodeEditor.color(color: Color, block: () -> String): Any
    fun CodeEditor.color(color: Long, block: () -> String): Any
    fun CodeEditor.normal(block: () -> String): Any
    fun CodeEditor.yellow(block: () -> String): Any
    fun CodeEditor.orange(block: () -> String): Any
    fun CodeEditor.gray(block: () -> String): Any
    fun CodeEditor.blue(block: () -> String): Any
    fun CodeEditor.cyan(block: () -> String): Any
    fun CodeEditor.purple(block: () -> String): Any
    fun CodeEditor.green(block: () -> String): Any
    fun CodeEditor.lavender(block: () -> String): Any
    fun CodeEditor.teal(block: () -> String): Any
}

typealias CodeEditorData = Pair<AnnotatedString, Int>

/**
 * An editor to feed data to the code panel
 * @sample com.payam1991gr.kmp.playground.view.screens.components.carousel.sample.appendHorizontalMultiBrowseCarouselSample
 */
@Composable
fun rememberCodeEditor(block: CodeEditor.() -> Unit): CodeEditorData {
    val normalColor = MaterialTheme.colorScheme.onBackground
    return remember { codeEditor(normalColor, block) }
}

fun codeEditor(
    normalColor: Color,
    block: CodeEditor.() -> Unit,
): CodeEditorData = CodeEditorImpl(normalColor).run {
    buildAnnotatedString {
        builder = this
        block()
    } to lines
}
