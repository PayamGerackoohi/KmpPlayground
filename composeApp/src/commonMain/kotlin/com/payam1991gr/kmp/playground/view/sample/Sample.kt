package com.payam1991gr.kmp.playground.view.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor

val CodeEditor.optIn get() = yellow { "@OptIn" }
val CodeEditor.immutable get() = yellow { "@Immutable" }
val CodeEditor.composable get() = yellow { "@Composable" }
val CodeEditor.`class` get() = orange { "class" }
val CodeEditor.`object` get() = orange { "object" }
val CodeEditor.`fun` get() = orange { "fun" }
val CodeEditor.`this` get() = orange { "this" }
val CodeEditor.`val` get() = orange { "val" }
val CodeEditor.`var` get() = orange { "var" }
val CodeEditor.`by` get() = orange { "by" }
val CodeEditor.`when` get() = orange { "when" }
val CodeEditor.`if` get() = orange { "if" }
val CodeEditor.`else` get() = orange { "else" }
val CodeEditor.`null` get() = orange { "null" }
val CodeEditor.`get` get() = orange { "get" }
val CodeEditor.`return` get() = orange { "return" }
val CodeEditor.`is` get() = orange { "is" }
val CodeEditor.`in` get() = orange { "in" }
val CodeEditor.`out` get() = orange { "out" }
val CodeEditor.`interface` get() = orange { "interface" }
val CodeEditor.`as` get() = orange { "as" }
val CodeEditor.`typealias` get() = orange { "typealias" }

@Suppress("ObjectPropertyName")
val CodeEditor._true get() = orange { "true" } // `true` cannot compile to swift

@Suppress("ObjectPropertyName")
val CodeEditor._false get() = orange { "false" } // `false` cannot compile to swift

fun CodeEditor.appendExperimentalMaterial3Api() {
    line { optIn; normal { "(" }; yellow { "ExperimentalMaterial3Api" }; normal { "::" }; `class`; normal { ")" } }
}

fun CodeEditor.appendComposable(tabs: Int = 0) {
    line(tabs) { composable }
}

fun CodeEditor.appendBox() {
    line { `fun`; normal { " Any?." }; blue { "box" }; normal { "() = " }; `this`; normal { "?.toString() ?: " }; green { "\"☐\"" } }
    line()
}
