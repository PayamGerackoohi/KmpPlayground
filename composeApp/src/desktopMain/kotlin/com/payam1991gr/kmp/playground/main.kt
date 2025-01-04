package com.payam1991gr.kmp.playground

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KmpPlayground",
    ) {
        App().Start()
    }
}
