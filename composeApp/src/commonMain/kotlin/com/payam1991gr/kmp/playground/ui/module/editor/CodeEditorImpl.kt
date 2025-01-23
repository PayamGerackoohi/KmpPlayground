package com.payam1991gr.kmp.playground.ui.module.editor

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString.Builder
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.withStyle

class CodeEditorImpl(private val normalColor: Color) : CodeEditor {
    lateinit var builder: Builder
    private var _lines = 0
    override val lines get() = _lines

    override fun CodeEditor.line(tabs: Int, block: CodeEditor.() -> Unit) = builder.apply {
        if (_lines != 0) append("\n")
        repeat(tabs) { append("    ") }
        block()
        _lines++
    }

    override fun CodeEditor.color(color: Color, block: () -> String) = builder.apply {
        withStyle(SpanStyle(color)) { append(block()) }
    }

    override fun CodeEditor.color(color: Long, block: () -> String) = color(Color(color), block)
    override fun CodeEditor.normal(block: () -> String) = color(normalColor, block)
    override fun CodeEditor.yellow(block: () -> String) = color(0xFFC2B84D, block)
    override fun CodeEditor.orange(block: () -> String) = color(0xFFD99167, block)
    override fun CodeEditor.gray(block: () -> String) = color(0xFF4C5058, block)
    override fun CodeEditor.blue(block: () -> String) = color(0xFF58A6FF, block)
    override fun CodeEditor.cyan(block: () -> String) = color(0xFF52BED9, block)
    override fun CodeEditor.purple(block: () -> String) = color(0xFFE083D7, block)
    override fun CodeEditor.green(block: () -> String) = color(0xFF75C574, block)
    override fun CodeEditor.lavender(block: () -> String) = color(0xFFC9C9FF, block)
    override fun CodeEditor.teal(block: () -> String) = color(0xFF008080, block)
}
