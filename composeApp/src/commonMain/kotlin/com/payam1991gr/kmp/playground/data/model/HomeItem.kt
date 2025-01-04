package com.payam1991gr.kmp.playground.data.model

import androidx.compose.ui.graphics.Color
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class HomeItem(val labelRes: StringResource, val iconRes: DrawableResource, val color: Color) {
    Components(Res.string.home_components, Res.drawable.ic_components, Color(0xFF00a000)),
    Animations(Res.string.home_animations, Res.drawable.ic_animation, Color.Red),
    Graphics(Res.string.home_graphics, Res.drawable.ic_graphics, Color.Blue),
    Io(Res.string.home_io, Res.drawable.ic_io, Color.Magenta),
    Miscellaneous(Res.string.home_miscellaneous, Res.drawable.ic_miscellaneous, Color(0xFF00A0A0)),
}
