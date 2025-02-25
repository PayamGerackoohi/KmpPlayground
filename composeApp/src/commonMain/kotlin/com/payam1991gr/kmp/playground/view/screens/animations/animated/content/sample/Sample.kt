package com.payam1991gr.kmp.playground.view.screens.animations.animated.content.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendShared() {
    appendComposable()
    line { `fun`; blue { " Item" }; normal { "(size: Dp, color: Color) = Box(Modifier." }; blue { "size" }; normal { "(size)." }; blue { "background" }; normal { "(color))" } }
    line()
    line { `fun`; blue { " SmallItem" }; normal { "() = Item(" }; cyan { "50" }; normal { "." }; purple { "dp" }; normal { ", Color." }; purple { "Magenta" }; normal { ")" } }
    line()
    line { `fun`; blue { " CollapsedItem" }; normal { "() = Item(" }; cyan { "100" }; normal { "." }; purple { "dp" }; normal { ", Color." }; purple { "Red" }; normal { ")" } }
    line()
    line { `fun`; blue { " ExpandedItem" }; normal { "() = Item(" }; cyan { "150" }; normal { "." }; purple { "dp" }; normal { ", Color." }; purple { "Blue" }; normal { ")" } }
    line()
}
