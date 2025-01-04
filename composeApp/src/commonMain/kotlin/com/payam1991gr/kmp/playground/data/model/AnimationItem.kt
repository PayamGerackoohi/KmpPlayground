package com.payam1991gr.kmp.playground.data.model

import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.StringResource

enum class AnimationItem(val labelRes: StringResource) {
    AnimatedContent(Res.string.animations_animated_content),
    AnimatedVisibility(Res.string.animations_animated_visibility),
    Crossfade(Res.string.animations_crossfade),
    AnimateXAs(Res.string.animations_animate_x_as),
}
