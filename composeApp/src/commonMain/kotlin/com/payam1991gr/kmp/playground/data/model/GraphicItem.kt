package com.payam1991gr.kmp.playground.data.model

import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource

enum class GraphicItem(val labelRes: StringResource) {
    Charts(Res.string.graphic_charts),
    ColorScheme(Res.string.graphic_color_scheme),
    OpenGL(Res.string.graphic_opengl),
}
