package com.payam1991gr.kmp.playground.view

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.appendComposable
import com.payam1991gr.kmp.playground.view.sample.`fun`
import com.payam1991gr.kmp.playground.view.sample._true

fun CodeEditor.appendRememberBoolean() {
    appendComposable()
    line { `fun`; blue { " rememberBoolean" }; normal { "(init: Boolean = " }; _true; normal { ") = remember { mutableStateOf(init) }" } }
    line()
}
