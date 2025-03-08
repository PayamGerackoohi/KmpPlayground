package com.payam1991gr.kmp.playground.view.screens.animations.crossfade.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendCrossfadeSample() {
    appendComposable()
    line { `fun`; blue { " CrossfadeSample" }; normal { "() = ContentList {" } }
    line(1) { `var`; normal { " state " }; `by`; normal { " rememberBoolean()" } }
    line(1) { normal { "Button({ state = !state }) { Text(stringResource(Res.string." }; purple { "toggle" }; normal { ")) }" } }
    line(1) { normal { "Crossfade(state, Modifier." }; blue { "semantics" }; normal { " { " }; purple { "stateDescription" }; normal { " = string(state) }) {" } }
    line(2) { `if`; normal { " (it) RandomImage() " }; `else`; normal { " RandomImage()" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
