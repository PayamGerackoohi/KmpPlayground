package com.payam1991gr.kmp.playground.data.model.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.appendExperimentalMaterial3Api

@Suppress("FunctionName")
fun CodeEditor.appendTimePickerState_time() {
    appendExperimentalMaterial3Api()
    line { orange { "fun " }; normal { "TimePickerState." }; blue { "time" }; normal { "() = Time(" }; purple { "hour" }; normal { ", " }; purple { "minute" }; normal { ")" } }
    line()
}

fun CodeEditor.appendTime() {
    line { orange { "data class " }; normal { "Time(" }; orange { "val " }; purple { "hour" }; normal { ": Int = " }; cyan { "0" }; normal { ", " }; orange { "val " }; purple { "minute" }; normal { ": Int = " }; cyan { "0" }; normal { ")" } }
    line()
}
