package com.payam1991gr.kmp.playground.view

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.intl.Locale

val LocalTestMode = staticCompositionLocalOf { false }

val isGerman get() = Locale.current == Locale("de")
