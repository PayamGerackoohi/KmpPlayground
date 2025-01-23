package com.payam1991gr.kmp.playground.data.time.sample

import com.payam1991gr.kmp.playground.ui.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.ui.sample.appendComposable

@Suppress("FunctionName")
fun CodeEditor.appendTime_format() {
    appendComposable()
    line { orange { "fun " }; normal { "Time." }; blue { "format" }; normal { "() = stringResource(" } }
    line(1) { normal { "Res.string." }; purple { "components_time_picker_time_template" }; normal { "," } }
    line(1) { purple { "hour" }; normal { "." }; blue { "toPaddedString" }; normal { "()," } }
    line(1) { purple { "minute" }; normal { "." }; blue { "toPaddedString" }; normal { "()," } }
    line { normal { ")" } }
    line()
}
