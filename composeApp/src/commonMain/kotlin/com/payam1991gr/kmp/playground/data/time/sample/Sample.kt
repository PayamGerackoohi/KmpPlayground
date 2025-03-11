package com.payam1991gr.kmp.playground.data.time.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

@Suppress("FunctionName")
fun CodeEditor.appendTime_format() {
    appendComposable()
    line { `fun`; normal { " Time." }; blue { "format" }; normal { "() = stringResource(" } }
    line(1) { normal { "Res.string." }; purple { "components_time_picker_time_template" }; normal { "," } }
    line(1) { purple { "hour" }; normal { "." }; blue { "toPaddedString" }; normal { "()," } }
    line(1) { purple { "minute" }; normal { "." }; blue { "toPaddedString" }; normal { "()," } }
    line { normal { ")" } }
    line()
}

fun CodeEditor.appendTimeZoneData() {
    line { orange { "data class" }; normal { " TimeZoneData(" }; `val`; purple { " name" }; normal { ": String, " }; `val`; purple { " offset" }; normal { ": String)" } }
    line()
    line { orange { "data class" }; normal { " LabeledTimeZone(" }; `val`; purple { " value" }; normal { ": TimeZone, " }; `val`; purple { " label" }; normal { ": String)" } }
    line()
    line { `fun`; normal { " TimeZone." }; blue { "offsetStringAt" }; normal { "(instant: Instant) = " }; blue { "offsetAt" }; normal { "(instant)." }; blue { "string" }; normal { "()" } }
    line()
    line { orange { "infix fun" }; normal { " TimeZone." }; blue { "label" }; normal { "(label: String) = LabeledTimeZone(" }; `this`; normal { ", label)" } }
    line()
    line { `fun`; normal { " UtcOffset." }; blue { "string" }; normal { "() = " }; `if`; normal { " (" }; `this`; normal { " == UtcOffset." }; purple { "ZERO" }; normal { ") " }; green { "\"+0 HRS\" " }; `else`; normal { " toString()" } }
    line()
    line { `fun`; normal { " UtcOffset." }; blue { "asLabeledTimeZone" }; normal { "() = " }; blue { "asTimeZone" }; normal { "()." }; blue { "run" }; normal { " { LabeledTimeZone(" }; `this`; normal { ", " }; purple { "id" }; normal { ") }" } }
    line()
}
