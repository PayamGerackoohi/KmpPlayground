@file:Suppress("FunctionName")

package com.payam1991gr.kmp.playground.view.screens.io.api.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendUI() {
    appendTypealias()
    appendString_FakeModeSetting()
    appendHostInput()
    appendRequestCard()
    appendRemoteData_String_GetHome()
    appendRemoteData_Numbers_GetWrittenNumbers()
    appendRangeSlider()
    appendRequest()
    appendStatus()
    appendInt_isOk()
    appendConnectingToServer()
    appendUrl_string()
}

fun CodeEditor.appendTypealias() {
    line { `typealias`; normal { " FRange = ClosedFloatingPointRange<Float>" } }
    line()
}

fun CodeEditor.appendString_FakeModeSetting() {
    appendComposable()
    line { orange { "private fun" }; normal { " State." }; blue { "FakeModeSetting" }; normal { "() = rememberSetting(" }; purple { "shouldUseRealApi" }; normal { ") {" } }
    line(1) { `if`; normal { " (it) Res." }; purple { "string.io_api_real_server" } }
    line(1) { `else`; normal { " Res.string." }; purple { "io_api_fake_server" } }
    line { normal { "}." }; blue { "apply" }; normal { " {" } }
    line(1) { normal { "LaunchedEffect(" }; purple { "value" }; normal { ") { " }; purple { "event" }; normal { "(Event.OnServerModeChanged(" }; purple { "value" }; normal { ")) }" } }
    line(1) { normal { "Settings(" }; `this`; normal { ")" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendHostInput() {
    appendComposable()
    line { orange { "private fun" }; blue { " HostInput" }; normal { "(host: String, event: (Event) -> Unit) = TextField(" } }
    line(1) { cyan { "value =" }; normal { " host," } }
    line(1) { cyan { "onValueChange =" }; normal { " { event(Event.OnHostChanged(it)) }," } }
    line(1) { cyan { "label =" }; normal { " { Text(" }; green { "\"Base URL\"" }; normal { ") }," } }
    line { normal { ")" } }
    line()
}

fun CodeEditor.appendRequestCard() {
    appendComposable()
    line { orange { "private fun" }; blue { " RequestCard" }; normal { "(" } }
    line(1) { normal { "url: String," } }
    line(1) { normal { "status: Int?," } }
    line(1) { normal { "method: HttpMethod," } }
    line(1) { normal { "call: () -> Unit," } }
    line(1) { normal { "tag: String = " }; green { "\"\"" }; normal { "," } }
    line(1) { normal { "showContent: Boolean = " }; _true; normal { "," } }
    line(1) { normal { "arguments: " }; yellow { "@Composable" }; normal { " ColumnScope.() -> Unit = {}," } }
    line(1) { normal { "content: " }; yellow { "@Composable" }; normal { " ColumnScope.() -> Unit," } }
    line(1) { normal { "" } }
    line { normal { ") = Card(" } }
    line(1) { cyan { "shape =" }; normal { " CutCornerShape(" }; cyan { "topEnd = 24" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(1) { cyan { "elevation =" }; normal { " CardDefaults.cardElevation(" }; cyan { "defaultElevation = 2" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(1) { cyan { "modifier =" }; normal { " Modifier." }; blue { "testTag" }; normal { "(" }; green { "\"RequestCard\"" }; blue { " merge" }; normal { " tag)" } }
    line { normal { ") {" } }
    line(1) { normal { "Column(" } }
    line(2) { cyan { "verticalArrangement =" }; normal { " Arrangement.spacedBy(" }; cyan { "8" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(2) { cyan { "modifier =" }; normal { " Modifier" } }
    line(3) { normal { "." }; blue { "background" }; normal { "(" }; purple { "colorScheme" }; normal { "." }; purple { "inversePrimary" }; normal { ")" } }
    line(3) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "12" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(1) { normal { ") {" } }
    line(2) { normal { "Request(url, status, method, call)" } }
    line(2) { normal { "arguments()" } }
    line(1) { normal { "}" } }
    line(1) { `if`; normal { " (showContent) Column(" } }
    line(2) { cyan { "verticalArrangement =" }; normal { " Arrangement.spacedBy(" }; cyan { "8" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(2) { cyan { "content =" }; normal { " content," } }
    line(2) { cyan { "modifier =" }; normal { " Modifier" } }
    line(3) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "12" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(3) { normal { "." }; blue { "testTag" }; normal { "(" }; green { "\"Content\"" }; normal { ")" } }
    line(1) { normal { ")" } }
    line { normal { ")}" } }
    line()
}

fun CodeEditor.appendRemoteData_String_GetHome() {
    appendComposable()
    line { orange { "private fun" }; normal { " RemoteData<String>." }; blue { "GetHome" }; normal { "(host: String, event: (Event) -> Unit) = RequestCard(" } }
    line(1) { cyan { "tag =" }; green { " \"GetHome\"" }; normal { "," } }
    line(1) { cyan { "url =" }; normal { " remember(host) { buildUrl { " }; `this`; normal { "." }; purple { "host" }; normal { " = host }." }; purple { "string" }; normal { " }," } }
    line(1) { cyan { "status =" }; normal { " status()," } }
    line(1) { cyan { "method =" }; normal { " HttpMethod." }; purple { "Get" }; normal { "," } }
    line(1) { cyan { "call =" }; normal { " { event(Event.CallHomeApi) }," } }
    line(1) { cyan { "showContent = " }; `this`; normal { " != RemoteData.Init," } }
    line { normal { ") {" } }
    line(1) { `when`; normal { " (" }; `this`; cyan { "@GetHome" }; normal { ") {" } }
    line(2) { normal { "RemoteData.Init -> {}" } }
    line(2) { normal { "RemoteData.Connecting -> ConnectingToServer()" } }
    line(2) { `is`; normal { " RemoteData.Data -> Text(" }; purple { "data" }; normal { ")" } }
    line(2) { `is`; normal { " RemoteData.Error -> Text(" }; purple { "message" }; normal { ")" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendRemoteData_Numbers_GetWrittenNumbers() {
    appendComposable()
    line { orange { "private fun" }; normal { " RemoteData<Numbers>." }; blue { "GetWrittenNumbers" }; normal { "(host: String, event: (Event) -> Unit) {" } }
    line(1) { `var`; normal { " range " }; `by`; normal { " remember { mutableStateOf(" }; cyan { "0f" }; normal { ".." }; cyan { "3f" }; normal { ") }" } }
    line(1) { `val`; normal { " (from, to) = remember(range) {" } }
    line(2) { normal { "range." }; purple { "start" }; normal { "." }; blue { "fastRoundToInt" }; normal { "() " }; blue { "to" }; normal { " range." }; purple { "endInclusive" }; normal { "." }; blue { "fastRoundToInt" }; normal { "()" } }
    line(1) { normal { "}" } }
    line(1) { normal { "RequestCard(" } }
    line(2) { cyan { "tag =" }; green { " \"GetWrittenNumbers\"" }; normal { "," } }
    line(2) { cyan { "url =" }; normal { " remember(host, range) {" } }
    line(3) { normal { "buildUrl {" } }
    line(4) { `this`; normal { "." }; purple { "host" }; normal { " = host" } }
    line(4) { cyan { "pathSegments =" }; normal { " listOf(" }; green { "\"written-numbers\"" }; normal { ")" } }
    line(4) { purple { "parameters" }; normal { "." }; blue { "apply" }; normal { " {" } }
    line(5) { normal { "append(" }; green { "\"from\"" }; normal { ", from.toString())" } }
    line(5) { normal { "append(" }; green { "\"to\"" }; normal { ", to.toString())" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}." }; purple { "string" } }
    line(2) { normal { "}," } }
    line(2) { cyan { "status =" }; normal { " status()," } }
    line(2) { cyan { "method =" }; normal { " HttpMethod." }; purple { "Get" }; normal { "," } }
    line(2) { cyan { "call =" }; normal { " { event(Event.CallWrittenNumbersApi(from, to)) }," } }
    line(2) { cyan { "arguments =" }; normal { " { RangeSlider(" }; green { "\"" }; orange { "\$" }; normal { "from" }; green { ".." }; orange { "\$" }; normal { "to" }; green { "\"" }; normal { ", range) { range = it } }," } }
    line(2) { cyan { "showContent = " }; `this`; normal { " != RemoteData.Init," } }
    line(1) { normal { ") {" } }
    line(2) { `when`; normal { " (" }; `this`; cyan { "@GetWrittenNumbers" }; normal { ") {" } }
    line(3) { normal { "RemoteData.Init -> {}" } }
    line(3) { normal { "RemoteData.Connecting -> ConnectingToServer()" } }
    line(3) { `is`; normal { " RemoteData.Error -> Text(" }; purple { "message" }; normal { ")" } }
    line(3) { `is`; normal { " RemoteData.Data -> " }; purple { "data" }; normal { "." }; blue { "forEach" }; normal { " { (value, string) ->" } }
    line(4) { normal { "Text(" }; green { "\"(" }; orange { "\$" }; normal { "value" }; normal { ", " }; orange { "\$" }; normal { "string" }; green { ")\"" }; normal { ")" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendRangeSlider() {
    appendComposable()
    line { orange { "private fun" }; blue { " RangeSlider" }; normal { "(state: String, range: FRange, onRangeChanged: (FRange) -> Unit) = Row(" } }
    line(1) { cyan { "horizontalArrangement =" }; normal { " Arrangement.spacedBy(" }; cyan { "8" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(1) { cyan { "verticalAlignment =" }; normal { " Alignment." }; purple { "CenterVertically" }; normal { "," } }
    line(1) { cyan { "modifier =" }; normal { " Modifier." }; purple { "padding" }; normal { "(" }; cyan { "horizontal = 12" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line { normal { ") {" } }
    line(1) { normal { "Text(" }; green { "\"Range\"" }; normal { ")" } }
    line(1) { normal { "RangeSlider(" } }
    line(2) { cyan { "value =" }; normal { " range," } }
    line(2) { cyan { "onValueChange =" }; normal { " onValueChange," } }
    line(2) { cyan { "valueRange =" }; normal { " -" }; cyan { "1f" }; normal { ".." }; cyan { "101f" }; normal { "," } }
    line(2) { cyan { "steps = 101" }; normal { "," } }
    line(2) { cyan { "colors =" }; normal { " SliderDefaults.colors(" } }
    line(3) { cyan { "thumbColor =" }; purple { " colorScheme" }; normal { "." }; purple { "tertiary" }; normal { "," } }
    line(3) { cyan { "activeTrackColor =" }; purple { " colorScheme" }; normal { "." }; purple { "secondary" }; normal { "," } }
    line(3) { cyan { "inactiveTrackColor =" }; purple { " colorScheme" }; normal { "." }; purple { "outlineVariant" }; normal { "," } }
    line(2) { normal { ")," } }
    line(2) { cyan { "modifier =" }; normal { " Modifier." }; blue { "semantics" }; normal { " { " }; purple { "stateDescription" }; normal { " = " }; green { "\"RangeSlider: [" }; orange { "\$" }; normal { "state" }; green { "]\"" }; normal { " }" } }
    line(1) { normal { ")" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendRequest() {
    appendComposable()
    line { orange { "private fun" }; blue { " Request" }; normal { "(url: String, status: Int?, method: HttpMethod, call: () -> Unit) = Row(" } }
    line(1) { cyan { "horizontalArrangement =" }; normal { " Arrangement.spacedBy(" }; cyan { "8" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(1) { cyan { "modifier =" }; normal { " Modifier." }; blue { "fillMaxWidth" }; normal { "()" } }
    line { normal { ") {" } }
    line(1) { normal { "Button(" } }
    line(2) { cyan { "onClick =" }; normal { " call," } }
    line(2) { cyan { "elevation =" }; normal { " ButtonDefaults.buttonElevation(" }; cyan { "defaultElevation = 4" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(1) { normal { ") { Text(method." }; purple { "value" }; normal { ") }" } }
    line(1) { normal { "Text(url, Modifier." }; blue { "weight" }; normal { "(" }; cyan { "1f" }; normal { ")." }; blue { "padding" }; normal { "(" }; cyan { "top = 12" }; normal { "." }; purple { "dp" }; normal { "))" } }
    line(1) { normal { "status?." }; blue { "let" }; normal { " { Status(it) }" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendStatus() {
    appendComposable()
    line { orange { "private fun" }; blue { " Status" }; normal { "(status: Int) = " }; purple { "colorScheme" }; normal { "." }; blue { "run" }; normal { " {" } }
    line(1) { `if`; normal { " (status." }; purple { "isOk" }; normal { ") " }; purple { "secondaryContainer" }; blue { " to" }; purple { " onSecondaryContainer" } }
    line(1) { `else`; purple { " tertiaryContainer" }; blue { " to" }; purple { " onTertiaryContainer" } }
    line { normal { "}." }; blue { "let" }; normal { " { (backgroundColor, color) ->" } }
    line(1) { normal { "Text(" } }
    line(2) { cyan { "text =" }; normal { " status.toString()," } }
    line(2) { cyan { "color =" }; normal { " color," } }
    line(2) { cyan { "modifier =" }; normal { " Modifier" } }
    line(3) { normal { "." }; blue { "background" }; normal { "(backgroundColor, " }; purple { "CircleShape" }; normal { ")" } }
    line(3) { normal { "." }; purple { "padding" }; normal { "(" }; cyan { "horizontal = 24" }; normal { "." }; purple { "dp" }; normal { ", " }; cyan { "vertical = 12" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(3) { normal { "." }; blue { "semantics" }; normal { " { " }; purple { "contentDescription" }; normal { " = " }; green { "\"Status\"" }; normal { " }" } }
    line(1) { normal { ")" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendInt_isOk() {
    line { orange { "private inline val" }; normal { " Int." }; purple { "isOk " }; `get`; normal { "() = " }; orange { "this in" }; cyan { " 200" }; normal { "..<" }; cyan { "300" } }
    line()
}

fun CodeEditor.appendConnectingToServer() {
    appendComposable()
    line { orange { "private fun" }; blue { " ConnectingToServer" }; normal { "() = Text(" }; green { "\"Connecting to the server...\"" }; normal { ")" } }
    line()
}

fun CodeEditor.appendUrl_string() {
    line { orange { "private val" }; normal { " Url." }; purple { "string " }; `get`; normal { "() = toString()." }; blue { "replaceFirst" }; normal { "(" }; green { "\"http://" }; normal { "\", " }; green { "\"\"" }; normal { ")" } }
    line()
}

fun CodeEditor.appendState() {
    line { orange { "data class" }; normal { " State(" } }
    line(1) { `val`; purple { " shouldUseRealApi" }; normal { ": Boolean," } }
    line(1) { `val`; purple { " host" }; normal { ": String," } }
    line(1) { `val`; purple { " home" }; normal { ": RemoteData<String>," } }
    line(1) { `val`; purple { " writtenNumbers" }; normal { ": RemoteData<Numbers>," } }
    line(1) { `val`; purple { " event" }; normal { ": (Event) -> Unit," } }
    line { normal { ") : CircuitUiState {" } }
    line(1) { orange { "sealed interface" }; normal { " Event : CircuitUiEvent {" } }
    line(2) { orange { "data class" }; normal { " OnHostChanged(" }; `val`; purple { " host" }; normal { ": String) : Event" } }
    line(2) { orange { "data class" }; normal { " OnServerModeChanged(" }; `val`; purple { " shouldUseRealApi" }; normal { ": Boolean) : Event" } }
    line(2) { orange { "data object" }; normal { " CallHomeApi : Event" } }
    line(2) { orange { "data class" }; normal { " CallWrittenNumbersApi(" }; `val`; purple { " from" }; normal { ": Int?, " }; `val`; purple { " to" }; normal { ": Int?) : Event" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendRemoteData() {
    line { orange { "sealed interface" }; normal { " RemoteData<" }; `out`; teal { " T" }; normal { "> {" } }
    line(1) { orange { "data object" }; normal { " Init : RemoteData<Nothing>" } }
    line()
    line(1) { orange { "data object" }; normal { " Connecting : RemoteData<Nothing>" } }
    line()
    line(1) { orange { "data class" }; normal { " Data<" }; teal { "T" }; normal { ">(" }; `val`; purple { " data" }; normal { ": " }; teal { "T" }; normal { ", " }; `val`; purple { " status" }; normal { ": Int? = " }; `null`; normal { ") : RemoteData<" }; teal { "T" }; normal { ">" } }
    line()
    line(1) { orange { "data class" }; normal { " Error(" } }
    line(2) { `val`; purple { " message" }; normal { ": String = " }; green { "\"Error!\"" }; normal { "," } }
    line(2) { `val`; purple { " status" }; normal { ": Int? = " }; `null`; normal { "," } }
    line(1) { normal { ") : RemoteData<Nothing> {" } }
    line(2) { `constructor`; normal { "(t: Throwable) : " }; `this`; normal { "(t." }; purple { "message" }; normal { " ?: " }; green { "\"?\"" }; normal { ")" } }
    line(2) { `constructor`; normal { "(code: HttpStatusCode) : " }; `this`; normal { "(code." }; purple { "description" }; normal { ", code." }; purple { "value" }; normal { ")" } }
    line(1) { normal { "}" } }
    line()
    line(1) { `fun`; blue { " status" }; normal { "() = " }; `when`; normal { " (" }; `this`; normal { ") {" } }
    line(2) { `is`; normal { " Data -> " }; purple { "status" } }
    line(2) { `is`; normal { " Error -> " }; purple { "status" } }
    line(2) { `else`; normal { " -> " }; `null` }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
