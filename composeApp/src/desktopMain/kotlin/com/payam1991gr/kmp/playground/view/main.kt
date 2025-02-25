package com.payam1991gr.kmp.playground.view

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState

fun main() = application {
    App().apply {
        Window(
            title = "KMP Playground",
            state = rememberWindowState(
                placement = WindowPlacement.Fullscreen,
            ),
            onCloseRequest = ::exitApplication,
        ) {
                Start()
        }
    }
}
