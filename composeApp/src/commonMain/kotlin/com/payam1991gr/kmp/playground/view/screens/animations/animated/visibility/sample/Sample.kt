package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendRememberVisible() {
    appendComposable()
    line { `fun`; blue { " rememberBoolean" }; normal { "(init: Boolean = " }; `true`; normal { ") = remember { mutableStateOf(init) }" } }
    line()
}

fun CodeEditor.appendModule() {
    appendComposable()
    line { `fun`; blue { " Module" }; normal { "(" } }
    line(1) { normal { "label: String," } }
    line(1) { normal { "onToggle: () -> Unit," } }
    line(1) { normal { "colors: Module.Colors = Module.Defaults.colors()," } }
    line(1) { normal { "animator: " }; composable; normal { " () -> Unit," } }
    line { normal { ") = Column(Modifier." }; blue { "fillMaxWidth" }; normal { "()) {" } }
    line(1) { normal { "TextButton(" } }
    line(2) { cyan { "onClick = " }; normal { "onToggle," } }
    line(2) { cyan { "colors = " }; normal { "ButtonDefaults.textButtonColors(" } }
    line(3) { cyan { "containerColor = " }; normal { "colors." }; purple { "header" }; normal { "," } }
    line(3) { cyan { "contentColor = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "onSecondary" }; normal { "," } }
    line(2) { normal { ")," } }
    line(2) { cyan { "shape = " }; normal { "RoundedCornerShape(" }; cyan { "topStart = 32" }; normal { "." }; purple { "dp" }; normal { ", " }; cyan { "topEnd = 32" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(2) { cyan { "modifier = " }; normal { "Modifier" } }
    line(3) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(3) { normal { "." }; blue { "height" }; normal { "(" }; cyan { "48" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(1) { normal { ") {" } }
    line(2) { normal { "Text(" } }
    line(3) { normal { "label," } }
    line(3) { cyan { "maxLines = 1" }; normal { "," } }
    line(3) { cyan { "overflow = " }; normal { "TextOverflow." }; purple { "Ellipsis" }; normal { "," } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line(1) { normal { "animator()" } }
    line { normal { "}" } }
    line()
    line { `object`; normal { " Module {" } }
    line(1) { immutable }
    line(1) { `class`; normal { " Colors(" }; `val`; purple { " header" }; normal { ": Color)" } }
    line()
    line(1) { `object`; normal { " Defaults {" } }
    appendComposable(2)
    line(2) { `fun`; blue { " colors" }; normal { "(header: Color = MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "secondary" }; normal { "): Colors = Colors(header)" } }
    line(1) { normal { "}" } }
    line()
    appendComposable(1)
    line(1) { `fun`; blue { " Content" }; normal { "(tag: String, cornerRadius: Dp = " }; cyan { "0" }; normal { "." }; purple { "dp" }; normal { ") {" } }
    line(2) { normal { "Box(" } }
    line(3) { normal { "Modifier" } }
    line(4) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(4) { normal { "." }; blue { "clip" }; normal { "(" } }
    line(5) { normal { "RoundedCornerShape(" } }
    line(6) { cyan { "bottomStart = " }; normal { "cornerRadius," } }
    line(6) { cyan { "bottomEnd = " }; normal { "cornerRadius," } }
    line(5) { normal { ")" } }
    line(4) { normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "RandomImage(" } }
    line(4) { cyan { "contentDescription = " }; green { "\"" }; orange { "\$" }; normal { "tag." }; green { "Content\"" }; normal { "," } }
    line(4) { cyan { "modifier = " }; normal { "Modifier." }; blue { "moduleSize" }; normal { "()" } }
    line(3) { normal { ")" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendModuleSize() {
    line { `fun`;normal { " Modifier." }; blue { "moduleSize" }; normal { "(ratio: Float = " }; cyan { "2f" }; normal { ") = " }; `this` }
    line(1) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(1) { normal { "." }; blue { "aspectRatio" }; normal { "(ratio)" } }
    line()
}
