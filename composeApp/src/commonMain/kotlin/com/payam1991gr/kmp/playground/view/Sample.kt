package com.payam1991gr.kmp.playground.view

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.appendComposable
import com.payam1991gr.kmp.playground.view.sample.`fun`
import com.payam1991gr.kmp.playground.view.sample.`true`

fun CodeEditor.appendRememberBoolean() {
    appendComposable()
    line { `fun`; blue { " rememberBoolean" }; normal { "(init: Boolean = " }; `true`; normal { ") = remember { mutableStateOf(init) }" } }
    line()
}
