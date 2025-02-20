package com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendItem() {
    appendComposable()
    line { `fun`; blue { " Item" }; normal { "(label: String, color: Color) = Surface(" } }
    line(1) { cyan { "shadowElevation = 4" }; normal { "." }; purple { "dp" }; normal { "," } }
    line(1) { cyan { "modifier = " }; normal { "Modifier" } }
    line(2) { normal { "." }; blue { "border" }; normal { "(" }; cyan { "1" }; normal { "." }; purple { "dp" }; normal { ", MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "outline" }; normal { ")" } }
    line(2) { normal { "." }; blue { "aspectRatio" }; normal { "(" }; cyan { "1f" }; normal { ")" } }
    line { normal { ") {" } }
    line(1) { normal { "Column {" } }
    line(2) { normal { "Surface(" }; cyan { "shadowElevation = 4" }; normal { "." }; purple { "dp" }; normal { ") {" } }
    line(3) { normal { "Text(" } }
    line(4) { normal { "label," } }
    line(4) { cyan { "color = " };normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "onPrimary" }; normal { "," } }
    line(4) { cyan { "textAlign = " }; normal { "TextAlign." }; purple { "Center" }; normal { "," } }
    line(4) { cyan { "style = " }; normal { "MaterialTheme." }; purple { "typography" }; normal { "." }; purple { "labelMedium" }; normal { "," } }
    line(4) { cyan { "modifier = " }; normal { "Modifier" } }
    line(5) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(5) { normal { "." }; blue { "background" }; normal { "(MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "primary" }; normal { ")" } }
    line(5) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "4" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(3) { normal { ")" } }
    line(2) { normal { "}" } }
    line(2) { normal { "HorizontalDivider(" }; cyan { "color = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "outline" }; normal { ")" } }
    line(2) { normal { "Box(" } }
    line(3) { normal { "Modifier" } }
    line(4) { normal { "." }; blue { "background" }; normal { "(color)" } }
    line(4) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(4) { normal { "." }; blue { "weight" }; normal { "(" }; cyan { "1f" }; normal { ")" } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
