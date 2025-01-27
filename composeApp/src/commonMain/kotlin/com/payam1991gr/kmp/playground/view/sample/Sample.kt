package com.payam1991gr.kmp.playground.view.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor

fun CodeEditor.appendExperimentalMaterial3Api() {
    line { yellow { "@OptIn" }; normal { "(" }; yellow { "ExperimentalMaterial3Api" }; normal { "::" }; orange { "class" }; normal { ")" } }
}

fun CodeEditor.appendComposable() {
    line { yellow { "@Composable" } }
}

fun CodeEditor.appendBox() {
    line { orange { "fun " }; normal { "Any?." }; blue { "box" }; normal { "() = " }; orange { "this" }; normal { "?.toString() ?: " }; green { "\"☐\"" } }
    line()
}
