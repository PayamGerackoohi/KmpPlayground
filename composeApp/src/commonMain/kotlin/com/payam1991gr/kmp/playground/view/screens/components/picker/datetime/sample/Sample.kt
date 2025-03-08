package com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendResources() {
    line { orange { "private val " }; purple { "timestampStringRes = " }; normal { "Res.string." }; purple { "components_date_picker_timestamp_template" } }
    line { orange { "private val " }; purple { "dateStringRes = " }; normal { "Res.string." }; purple { "components_date_picker_date_template" } }
    line()
}

fun CodeEditor.appendDatePickerSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { `fun`; blue { " DatePickerSample" }; normal { "(state: State.DatePicker) = ContentList {" } }
    line(1) { normal { "rememberDatePickerState(" } }
    line(2) { cyan { "initialSelectedDateMillis = " }; normal { "state." }; purple { "date" }; normal { "." }; purple { "ms" }; normal { "," } }
    line(2) { cyan { "initialDisplayedMonthMillis = " }; normal { "state." }; purple { "initialDisplayDate" }; normal { "," } }
    line(1) { normal { ")." }; blue { "apply " }; normal { "{" } }
    line(2) { `val`; normal { " showModeToggle = rememberModeToggleSetting()" } }
    line(2) { normal { "Settings(showModeToggle)" } }
    line(2) { normal { "Text(stringResource(" }; purple { "timestampStringRes" }; normal { ", state." }; purple { "date" }; normal { "." }; purple { "ms" }; normal { "." }; blue { "box" }; normal { "()))" } }
    line(2) { normal { "Text(stringResource(" }; purple { "dateStringRes" }; normal { ", state." }; purple { "date" }; normal { "." }; purple { "text" }; normal { "." }; blue { "box" }; normal { "()))" } }
    line(2) { purple { "selectedDateMillis" }; normal { "." }; blue { "let " }; normal { "{ remember(it) { state." }; purple { "date" }; normal { "." }; purple { "ms " }; normal { "= it } }" } }
    line(2) { normal { "DatePicker(" } }
    line(3) { cyan { "state = " }; `this`; normal { "," } }
    line(3) { cyan { "showModeToggle = " }; normal { "showModeToggle." }; purple { "value" }; normal { "," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier." }; blue { "testTag" }; normal { "(" }; green { "\"DatePicker\"" }; normal { ")" } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendDateRangePickerSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { `fun`; blue { " DateRangePickerSample" }; normal { "(state: State.DateRangePicker) = ContentList {" } }
    line(1) { normal { "rememberDateRangePickerState(" } }
    line(2) { cyan { "initialSelectedStartDateMillis = " }; normal { "state." }; purple { "startDate" }; normal { "." }; purple { "ms" }; normal { "," } }
    line(2) { cyan { "initialSelectedEndDateMillis = " }; normal { "state." }; purple { "endDate" }; normal { "." }; purple { "ms" }; normal { "," } }
    line(2) { cyan { "initialDisplayedMonthMillis = " }; normal { "state." }; purple { "initialDisplayDate" }; normal { "," } }
    line(1) { normal { ")." }; blue { "apply" }; normal { " {" } }
    line(2) { purple { "selectedStartDateMillis" }; normal { "." }; blue { "let " }; normal { "{ remember(it) { state." }; purple { "startDate" }; normal { "." }; purple { "ms " }; normal { "= it } }" } }
    line(2) { purple { "selectedEndDateMillis" }; normal { "." }; blue { "let " }; normal { "{ remember(it) { state." }; purple { "endDate" }; normal { "." }; purple { "ms " }; normal { "= it } }" } }
    line(2) { `val`; normal { " showModeToggle = rememberModeToggleSetting()" } }
    line(2) { normal { "Settings(" } }
    line(3) { normal { "showModeToggle," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier" } }
    line(4) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(4) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "horizontal = 16" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { "." }; cyan { "testTag" }; normal { "(" }; green { "\"DateRangePicker.Settings\"" }; normal { ")" } }
    line(2) { normal { ")" } }
    line(2) { normal { "Text(stringResource(" }; purple { "timestampStringRes" }; normal { ", state.msRange()))" } }
    line(2) { normal { "Text(stringResource(" }; purple { "dateStringRes" }; normal { ", state.textRange()))" } }
    line(2) { normal { "DateRangePicker(" } }
    line(3) { cyan { "state = " }; `this`; normal { "," } }
    line(3) { cyan { "showModeToggle = " }; normal { "showModeToggle." }; purple { "value" }; normal { "," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier" } }
    line(4) { normal { "." }; blue { "height" }; normal { "(" }; cyan { "450" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { "." }; blue { "testTag" }; normal { "(" }; green { "\"DateRangePicker\"" }; normal { ")" } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendTimePickerSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { `fun`; blue { " TimePickerSample" }; normal { "(state: State.TimeState) = " }; green { "\"TimePicker\"" }; normal { "." }; blue { "let " }; normal { "{ tag ->" } }
    line(1) { normal { "TimeSample(state, tag) {" } }
    line(2) { normal { "TimePicker(" }; `this`; normal { ", Modifier." }; blue { "testTag" }; normal { "(tag))" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendTimeInputSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { `fun`; blue { " TimeInputSample" }; normal { "(state: State.TimeState) = " }; green { "\"TimeInput\"" }; normal { "." }; blue { "let " }; normal { "{ tag ->" } }
    line(1) { normal { "TimeSample(state, tag) {" } }
    line(2) { normal { "TimeInput(" }; `this`; normal { ", Modifier." }; blue { "testTag" }; normal { "(tag))" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendTimeSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { orange { "private fun " }; blue { "TimeSample" }; normal { "(" } }
    line(1) { normal { "state: State.TimeState," } }
    line(1) { normal { "tag: String," } }
    line(1) { normal { "content: " }; yellow { "@Composable " }; normal { "TimePickerState.() -> Unit," } }
    line { normal { ") = ContentList {" } }
    line(1) { `val`; normal { " is24Hour = rememberIs24HourSetting()" } }
    line(1) { normal { "Settings(" } }
    line(2) { normal { "is24Hour," } }
    line(2) { cyan { "modifier = " }; normal { "Modifier" } }
    line(3) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(3) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "horizontal = 16" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(3) { normal { "." }; blue { "testTag" }; normal { "(" }; green { "\"" }; orange { "\$" }; normal { "tag" }; green { ".Settings\"" }; normal { ")" } }
    line(1) { normal { ")" } }
    line(1) { normal { "state." }; purple { "time" }; normal { "." }; blue { "run " }; normal { "{" } }
    line(2) { normal { "rememberTimePickerState(" } }
    line(3) { cyan { "initialHour = " }; purple { "hour" }; normal { "," } }
    line(3) { cyan { "initialMinute = " }; purple { "minute" }; normal { "," } }
    line(2) { normal { ")" } }
    line(1) { normal { "}." }; blue { "apply " }; normal { "{" } }
    line(2) { normal { "remember(" }; purple { "hour" }; normal { ") { state." }; purple { "event" }; normal { "(State.TimeState.Event.OnHourChanged(" }; purple { "hour" }; normal { ")) }" } }
    line(2) { normal { "remember(" }; purple { "minute" }; normal { ") { state." }; purple { "event" }; normal { "(State.TimeState.Event.OnMinuteChanged(" }; purple { "minute" }; normal { ")) }" } }
    line(2) { purple { "is24hour " }; normal { "= is24Hour." }; purple { "value" } }
    line(2) { normal { "Text(" } }
    line(3) { normal { "state." }; purple { "time" }; normal { "." }; blue { "format" }; normal { "())" } }
    line(3) { cyan { "modifier = " }; normal { "Modifier." }; blue { "testTag" }; green { "(\"" }; orange { "\$" }; normal { "tag" }; green { ".Text\"" }; normal { ")" } }
    line(2) { normal { ")" } }
    line(2) { normal { "Crossfade(is24Hour." }; purple { "value" }; normal { ") { content() }" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendRememberModeToggleSetting() {
    appendComposable()
    line { `fun`; blue { " rememberModeToggleSetting" }; normal { "() = rememberSetting(" }; _true; normal { ") {" } }
    line(1) { `if`; normal { " (it) Res.string." }; purple { "components_date_picker_mode_toggle" } }
    line(1) { `else`; normal { " Res.string." }; purple { "components_date_picker_no_mode_toggle" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendRememberIs24HourSetting() {
    appendComposable()
    line { orange { "private fun " }; blue { "rememberIs24HourSetting" }; normal { "() = rememberSetting {" } }
    line(1) { `if`; normal { " (it) Res.string." }; purple { "components_time_picker_24_hour" } }
    line(1) { `else`; normal { " Res.string." }; purple { "components_time_picker_12_hour" } }
    line { normal { "}" } }
    line()
}

@Suppress("FunctionName")
fun CodeEditor.appendState_TimeState() {
    line { orange { "sealed interface " }; normal { "State {" } }
    line(1) { orange { "data class " }; normal { "TimeState(" } }
    line(2) { `val`; purple { " time" }; normal { ": Time," } }
    line(2) { `val`; purple { " event" }; normal { ": (Event) -> Unit," } }
    line(1) { normal { ") {" } }
    line(2) { orange { "sealed interface " }; normal { "Event {" } }
    line(3) { orange { "data class " }; normal { "OnHourChanged(" }; `val`; purple { " hour" }; normal { ": Int) : Event" } }
    line(3) { orange { "data class " }; normal { "OnMinuteChanged(" }; `val`; purple { " minute" }; normal { ": Int) : Event" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
