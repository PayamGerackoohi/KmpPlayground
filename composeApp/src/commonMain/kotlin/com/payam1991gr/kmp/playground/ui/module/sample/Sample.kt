package com.payam1991gr.kmp.playground.ui.module.sample

import com.payam1991gr.kmp.playground.ui.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.ui.sample.appendComposable

fun CodeEditor.appendContentList() {
    appendComposable()
    line { orange { "fun " }; blue { "ContentList" }; normal { "(" } }
    line(1) { normal { "modifier: Modifier = Modifier," } }
    line(1) { normal { "content: " }; yellow { "@Composable " }; normal { "ColumnScope.() -> Unit," } }
    line { normal { ") = Column(" } }
    line(1) { cyan { "horizontalAlignment = " }; normal { "Alignment." }; purple { "CenterHorizontally" }; normal { "," } }
    line(1) { cyan { "verticalArrangement = " }; normal { "Arrangement.spacedBy(" }; cyan { "8" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(1) { cyan { "modifier = " }; normal { "Modifier." }; blue { "fillMaxWidth" }; normal { "()," } }
    line(1) { cyan { "content = " }; normal { "content," } }
    line { normal { ")" } }
    line()
}
