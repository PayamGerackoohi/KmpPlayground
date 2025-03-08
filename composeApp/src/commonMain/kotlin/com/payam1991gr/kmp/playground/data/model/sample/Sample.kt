package com.payam1991gr.kmp.playground.data.model.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

@Suppress("FunctionName")
fun CodeEditor.appendTimePickerState_time() {
    appendExperimentalMaterial3Api()
    line { `fun`; normal { " TimePickerState." }; blue { "time" }; normal { "() = Time(" }; purple { "hour" }; normal { ", " }; purple { "minute" }; normal { ")" } }
    line()
}

fun CodeEditor.appendTime() {
    line { orange { "data class " }; normal { "Time(" }; `val`; purple { " hour" }; normal { ": Int = " }; cyan { "0" }; normal { ", " }; orange { "val " }; purple { "minute" }; normal { ": Int = " }; cyan { "0" }; normal { ")" } }
    line()
}

fun CodeEditor.appendConverters() {
    appendConverter()
    appendIntConverter()
    appendFloatConverter()
    appendByteArrayConverter()
}

fun CodeEditor.appendConverter() {
    line { `interface`; normal { " Converter<" }; teal { "T" }; normal { "> {" } }
    line(1) { `fun`; blue { " from" }; normal { "(data: " }; teal { "T" }; normal { "): String" } }
    line(1) { `fun`; normal { " String." }; blue { "toData" }; normal { "(): Data<" }; teal { "T" }; normal { ">" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendIntConverter() {
    line { `class`; normal { " IntConverter : Converter<Int> {" } }
    line(1) { orange { "override fun " }; blue { "from" }; normal { "(data: Int): String = data.toString()" } }
    line(1) { orange { "override fun " }; normal { "String." }; blue { "toData" }; normal { "(): Data<Int> = buildData {" } }
    line(2) { blue { "trim" }; normal { "()." }; blue { "toIntOrNull" }; normal { "() ?: error() ?: " }; cyan { "0" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendFloatConverter() {
    line { `class`; normal { " FloatConverter : Converter<Float> {" } }
    line(1) { orange { "override fun " }; blue { "from" }; normal { "(data: Float): String = data.toString()" } }
    line(1) { orange { "override fun " }; normal { "String." }; blue { "toData" }; normal { "(): Data<Float> = buildData {" } }
    line(2) { blue { "trim" }; normal { "()." }; blue { "toFloatOrNull" }; normal { "() ?: error() ?: " }; cyan { "0f" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendByteArrayConverter() {
    line { `class`; normal { " ByteArrayConverter : Converter<ByteArray> {" } }
    line(1) { orange { "override fun " }; blue { "from" }; normal { "(data: ByteArray): String = data." }; blue { "joinToString" }; normal { "(" }; green { "\",\"" }; normal { ")" } }
    line(1) { orange { "override fun " }; normal { "String." }; blue { "toData" }; normal { "(): Data<ByteArray> = buildData {" } }
    line(2) { `if`; normal { " (" }; blue { "isBlank" }; normal { "()) byteArrayOf()" } }
    line(2) { `else`; blue { " split" }; normal { "(" }; green { "\",\"" }; normal { ")" } }
    line(3) { normal { "." }; blue { "mapNotNull" }; normal { " { it." }; blue { "trim" }; normal { "()." }; blue { "toByteOrNull" }; normal { "() ?: error() }" } }
    line(3) { normal { "." }; blue { "toByteArray" }; normal { "()" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
