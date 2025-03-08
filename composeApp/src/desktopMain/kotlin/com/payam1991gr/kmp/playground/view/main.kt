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

//private fun ioTest() { // t o d o
//    println(System.getProperty("user.home")) // /Users/payam1991gr
//    println(FileSystemView.getFileSystemView().homeDirectory.absolutePath) // /Users/payam1991gr
//    println(FileSystemView.getFileSystemView().defaultDirectory) // /Users/payam1991gr

//    println("*** user.dir: ${System.getProperty("user.dir")}") // /Users/payam1991gr/Root/Projects/CrossPlatform/KMP/sample/KmpPlayground/composeApp

//    println(System.getProperty("java.io.tmpdir")) // /var/folders/70/shrp48fn7g9_wx7vnsrm5whc0000gn/T/
//    println(Files.createTempDirectory("tmpDirPrefix").toFile()) // /var/folders/70/shrp48fn7g9_wx7vnsrm5whc0000gn/T/tmpDirPrefix1769855337440258868

//    println(System.getenv("APPDATA")) // null
//    println(System.getenv("AppData")) // null

//    println(System.getProperty("os.name")) // Mac OS X
//    println("*** hostOs: $hostOs") //*** hostOs: MacOS
//    println("*** hostArch: $hostArch") //*** hostArch: X64
//    println("*** hostId: $hostId") //*** hostId: macos-x64
//    println("*** kotlinBackend: $kotlinBackend") //*** kotlinBackend: JVM
//}
// https://github.com/Syer10/Kotlin-Multiplatform-AppDirs/
