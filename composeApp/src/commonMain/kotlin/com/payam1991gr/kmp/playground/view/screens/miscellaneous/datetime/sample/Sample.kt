package com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendAnalogClockCard() {
    appendComposable()
    line { orange { "private fun " }; blue { "AnalogClockCard" }; normal { "(now: Instant, labeledTimeZone: LabeledTimeZone) = Card {" } }
    line(1) { `val`; normal { " (timeZone, label) = labeledTimeZone" } }
    line(1) { `val`; normal { " zone = remember(now, timeZone) { TimeZoneData(label, timeZone." }; blue { "offsetStringAt" }; normal { "(now)) }" } }
    line(1) { `val`; normal { " (date, time) = remember(now) { now." }; blue { "toLocalDateTime" }; normal { "(timeZone)." }; blue { "run" }; normal { " { " }; purple { "date" }; blue { " to " }; purple { "time" }; normal { " } }" } }
    line(1) { `val`; normal { " dateString = remember(date) { date.toString() }" } }
    line(1) { `val`; normal { " timeString = remember(time) { " }; purple { "timeFormat" }; normal { ".format(time) }" } }
    line(1) { normal { "Column(" } }
    line(2) { cyan { "horizontalAlignment = " }; normal { "Alignment." }; purple { "CenterHorizontally" }; normal { "," } }
    line(2) { cyan { "verticalArrangement = " }; normal { "Arrangement.spacedBy(" }; cyan { "4" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(2) { cyan { "modifier = " }; normal { "Modifier" } }
    line(3) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "8" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(3) { normal { "." }; blue { "semantics" }; normal { " { " }; purple { "contentDescription" }; normal { " = " }; green { "\"Clock - " }; orange { "\${" }; normal { "zone." }; purple { "name" }; orange { "}" }; green { "\"" }; normal { " }" } }
    line(1) { normal { ") {" } }
    line(2) { normal { "AnalogClock(date, time, zone, " }; cyan { "tag =" }; normal { " zone." }; purple { "name" }; normal { ")" } }
    line(2) { normal { "Title(zone." }; purple { "name" }; normal { ")" } }
    line(2) { normal { "Date(dateString)" } }
    line(2) { normal { "Time(timeString)" } }
    line(2) { normal { "UtcOffset(zone." }; purple { "offset" }; normal { ")" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendTitle() {
    appendComposable()
    line { orange { "private fun" }; blue { " Title" }; normal { "(text: String) = Text(" } }
    line(1) { cyan { "text =" }; normal { " text," } }
    line(1) { cyan { "style =" }; purple { " typography.titleMedium" }; normal { "," } }
    line(1) { cyan { "maxLines = 1" }; normal { "," } }
    line(1) { cyan { "overflow =" }; normal { " TextOverflow." }; purple { "Ellipsis" }; normal { "," } }
    line { normal { ")" } }
    line()
}

fun CodeEditor.appendDate() {
    appendComposable()
    line { orange { "private fun" }; blue { " Date" }; normal { "(text: String) = Text(text, " }; cyan { "style =" }; purple { " typography" }; normal { "." }; purple { "bodyLarge" }; normal { ")" } }
    line()
}

fun CodeEditor.appendTime() {
    appendComposable()
    line { orange { "private fun" }; blue { " Time" }; normal { "(text: String) = Text(" } }
    line(1) { cyan { "text =" }; normal { " text," } }
    line(1) { cyan { "style =" }; purple { " typography" }; normal { "." }; purple { "titleSmall" }; normal { "," } }
    line(1) { cyan { "fontFamily =" }; normal { " FontFamily." }; purple { "Monospace" }; normal { "," } }
    line { normal { ")" } }
    line()
}

fun CodeEditor.appendUtcOffset() {
    appendComposable()
    line { orange { "private fun" }; blue { " UtcOffset" }; normal { "(text: String) = Text(" } }
    line(1) { cyan { "text =" }; normal { " text," } }
    line(1) { cyan { "style =" }; purple { " typography" }; normal { "." }; purple { "bodySmall" }; normal { "," } }
    line(1) { cyan { "modifier =" }; normal { " Modifier." }; blue { "semantics" }; normal { " { " }; purple { "contentDescription" }; normal { " = " }; green { "\"UTC Offset\"" }; normal { " }" } }
    line { normal { ")" } }
    line()
}
