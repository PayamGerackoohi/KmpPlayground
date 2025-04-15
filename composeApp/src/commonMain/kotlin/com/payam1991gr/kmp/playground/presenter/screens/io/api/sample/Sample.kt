package com.payam1991gr.kmp.playground.presenter.screens.io.api.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendNumbers() {
    line { `typealias`; normal { " Numbers = List<WrittenNumber>" } }
    line()
}

fun CodeEditor.appendPresenter() {
    appendComposable()
    line { `class`; normal { " ApiPresenter(" } }
    line(1) { orange { "private val" }; purple { " realApi" }; normal { ": Api," } }
    line(1) { orange { "private val" }; purple { " fakeApi" }; normal { ": Api," } }
    line { normal { ") : Presenter<State> {" } }
    line(1) { orange { "private var" }; normal { " shouldUseRealApi " }; `by`; normal { " mutableStateOf(" }; _true; normal { ")" } }
    line(1) { orange { "private val" }; purple { " api" }; normal { ": Api " }; `get`; normal { "() = " }; `if`; normal { " (" }; purple { "shouldUseRealApi" }; normal { ") " }; purple { "realApi " }; `else`; purple { " fakeApi" } }
    line(1) { orange { "private var" }; normal { " home " }; `by`; normal { " mutableStateOf<RemoteData<String>>(Init)" } }
    line(1) { orange { "private var" }; purple { " writtenNumbers " }; `by`; normal { " mutableStateOf<RemoteData<Numbers>>(Init)" } }
    line()
    appendComposable(1)
    line(1) { orange { "override fun" }; blue { " present" }; normal { "(): State {" } }
    line(2) { `val`; normal { " scope = rememberCoroutineScope()" } }
    line(2) { `return`; normal { " State(" }; purple { "shouldUseRealApi" }; normal { ", " }; purple { "api" }; normal { "." }; purple { "host" }; normal { ", " }; purple { "home" }; normal { ", " }; purple { "writtenNumbers" }; normal { ") { event ->" } }
    line(3) { `when`; normal { " (event) {" } }
    line(4) { `is`; normal { " Event.OnHostChanged -> " }; purple { "api" }; normal { "." }; purple { "host" }; normal { " = event." }; purple { "host" } }
    line(4) { `is`; normal { " Event.OnServerModeChanged -> " }; purple { "shouldUseRealApi" }; normal { " = event." }; purple { "shouldUseRealApi" } }
    line()
    line(4) { normal { "Event.CallHomeApi -> scope." }; blue { "launch" }; normal { " {" } }
    line(5) { purple { "api" }; normal { ".home().collectLatest { " }; purple { "home" }; normal { " = it }" } }
    line(4) { normal { "}" } }
    line()
    line(4) { `is`; normal { " Event.CallWrittenNumbersApi -> scope." }; blue { "launch" }; normal { " {" } }
    line(5) { purple { "api" }; normal { ".writtenNumbers(event." }; purple { "from" }; normal { ", event." }; purple { "to" }; normal { ").collectLatest { " }; purple { "writtenNumbers" }; normal { " = it }" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendWrittenNumber() {
    line { yellow { "@Serializable" } }
    line { orange { "data class" }; normal { " WrittenNumber(" }; `val`; purple { " value" }; normal { ": Int = " }; cyan { "0" }; normal { ", " }; `val`; purple { " string" }; normal { ": String = " }; green { "\"\"" }; normal { ") {" } }
    line(1) { orange { "companion object " }; normal { "{" } }
    line(2) { orange { "private val" }; purple { " digits" }; normal { " =" } }
    line(3) { normal { "arrayOf(" }; green { "\"Zero\"" }; normal { ", " }; green { "\"One\"" }; normal { ", " }; green { "\"Two\"" };normal { ", " }; green { "\"Three\"" }; normal { ", " }; green { "\"Four\"" }; normal { ", " }; green { "\"Five\"" }; normal { ", " }; green { "\"Six\"" }; normal { ", " }; green { "\"Seven\"" }; normal { ", " }; green { "\"Eight\"" }; normal { ", " }; green { "\"Nine\"" }; normal { ")" } }
    line(2) { orange { "private val" }; purple { " tens" }; normal { " =" } }
    line(3) { normal { "arrayOf(" }; green { "\"Twenty\"" }; normal { ", " }; green { "\"Thirty\"" }; normal { ", " }; green { "\"Forty\"" };normal { ", " }; green { "\"Fifty\"" }; normal { ", " }; green { "\"Sixty\"" }; normal { ", " }; green { "\"Seventy\"" }; normal { ", " }; green { "\"Eighty\"" }; normal { ", " }; green { "\"Ninety\"" } }
    line()
    line(2) { `fun`; blue { " of" }; normal { "(value: Int) = WrittenNumber(" } }
    line(3) { normal { "value, " }; `if`; normal { " (value " }; orange { "!in" }; cyan { " 0" }; normal { ".." }; cyan { "100" }; normal { ") " }; green { "\"Unsupported\"" } }
    line(3) { orange { "else when" }; normal { " (value) {" } }
    line(4) { `in`; cyan { " 0" }; normal { "..<" }; cyan { "10" }; normal { " -> " }; purple { "digits" }; normal { "[value]" } }
    line(4) { cyan { "10" }; normal { " -> " }; green { "\"Ten\"" } }
    line(4) { cyan { "11" }; normal { " -> " }; green { "\"Eleven\"" } }
    line(4) { cyan { "12" }; normal { " -> " }; green { "\"Twelve\"" } }
    line(4) { cyan { "13" }; normal { " -> " }; green { "\"Thirteen\"" } }
    line(4) { cyan { "15" }; normal { " -> " }; green { "\"Fifteen\"" } }
    line(4) { cyan { "18" }; normal { " -> " }; green { "\"Eighteen\"" } }
    line(4) { `in`; cyan { " 10" }; normal { "..<" }; cyan { "20" }; normal { " -> " }; green { "\"" }; orange { "\${" }; purple { "digits" }; normal { "[value - " }; cyan { "10" }; normal { "]" }; orange { "}" }; green { "teen\"" } }
    line(4) { cyan { "100" }; normal { " -> " }; normal { "\"One Hundred\"" } }
    line(4) { `else`; normal { " -> (value % " }; cyan { "10" }; normal { ")." }; blue { "let" }; normal { " { r ->" } }
    line(5) { green { "\"" }; orange { "\${" }; purple { "tens" }; normal { "[value / " }; cyan { "10" }; normal { " - " }; cyan { "2" }; normal { "]" }; orange { "}\${if" }; normal { " (r == " }; cyan { "0" }; normal { ") " }; green { "\"\" " }; `else`; green { " \" " }; orange { "\${" }; purple { "digits" }; normal { "[r]" }; orange { "}" }; green { "\"" }; orange { "}" }; green { "\"" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendApi() {
    appendComposable()
    line { `interface`; normal { " Api {" } }
    line(1) { green { "/**" } }
    line(1) { green { " * Server Address" } }
    line(1) { green { " * http://192.168.1.101:8080" } }
    line(1) { green { " */" } }
    line(1) { `var`; purple { " host" }; normal { ": String" } }
    line()
    line(1) { `fun`; blue { " home" }; normal { "(): Flow<RemoteData<String>>" } }
    line()
    line(1) { green { "/**" } }
    line(1) { green { " * It converts integers into their written form" } }
    line(1) { green { " * ### Errors" } }
    line(1) { green { " * - 400    Bad Request" } }
    line(1) { green { " * @param from start of the range (0 ≤ `from` ≤ `to` ≤ 100)" } }
    line(1) { green { " * @param to end of the range (0 ≤ `from` ≤ `to` ≤ 100)" } }
    line(1) { green { " */" } }
    line(1) { `fun`; blue { " writtenNumbers" }; normal { "(from: Int?, to: Int?): Flow<RemoteData<List<WrittenNumber>>>" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendApiImpl() {
    appendComposable()
    line { `class`; normal { " ApiImpl(" } }
    line(1) { normal { "httpClientEngine: HttpClientEngine," } }
    line(1) { orange { "private val" }; purple { " io" }; normal { ": CoroutineContext = Dispatchers." }; purple { "IO" }; normal { "," } }
    line { normal { ") : Api {" } }
    line(1) { orange { "override var" }; purple { " host " }; `by`; normal { " mutableStateOf(" }; green { "\"http://192.168.1.101:8080\"" }; normal { ")" } }
    line(1) { orange { "private val" }; purple { " client" }; normal { " = " }; orange { "HttpClient" }; normal { "(httpClientEngine) {" } }
    line(2) { normal { "install(" }; purple { "ContentNegotiation" }; normal { ") { " }; blue { "json" }; normal { "() }" } }
    line(1) { normal { "}" } }
    line()
    line(1) { orange { "override fun" }; blue { " home" }; normal { "() = flow {" } }
    line(2) { normal { "emit(Connecting)" } }
    line(2) { `try`; normal { "{" } }
    line(3) { purple { "client" }; normal { ".get(" }; green { "\"" }; orange { "\$" }; purple { "host" }; green { "/\"" }; normal { ")." }; blue { "let" }; normal { " {" } }
    line(4) { `val`; normal { " text = it.bodyAsText()" } }
    line(4) { `val`; normal { " (status, isSuccess) = it." }; blue { "status" }; normal { "()" } }
    line(4) { `if`; normal { " (isSuccess) Data(text, status)" } }
    line(4) { `else`; normal { " Error(text, status)" } }
    line(3) { normal { "}" } }
    line(2) { normal { "} " }; `catch`; normal { " (t: Throwable) {" } }
    line(3) { normal { "Error(t)" } }
    line(2) { normal { "}." }; blue { "let" }; normal { " { emit(it) }" } }
    line(1) { normal { "}." }; blue { "flowOn" }; normal { "(" }; purple { "io" }; normal { ")" } }
    line()
    line(1) { orange { "override fun" }; blue { " writtenNumbers" }; normal { "(from: Int?, to: Int?) = flow {" } }
    line(2) { normal { "emit(Connecting)" } }
    line(2) { `try`; normal { " {" } }
    line(3) { purple { "client" }; normal { ".get(" }; green { "\"" }; orange { "\$" }; purple { "host" }; green { "/written-numbers\"" }; normal { ") {" } }
    line(4) { blue { "parameter" }; normal { "(" }; green { "\"from\"" }; normal { ", from)" } }
    line(4) { blue { "parameter" }; normal { "(" }; green { "\"to\"" }; normal { ", to)" } }
    line(3) { normal { "}." }; blue { "let" }; normal { " {" } }
    line(4) { `val`; normal { " (status, isSuccess) = it." }; blue { "status" }; normal { "()" } }
    line(4) { `if`; normal { " (isSuccess) Data(it.body<List<WrittenNumber>>(), status)" } }
    line(4) { `else`; normal { " Error(it.bodyAsText(), status)" } }
    line(3) { normal { "}" } }
    line(2) { normal { "} " }; `catch`; normal { " (t: Throwable) {" } }
    line(3) { normal { "Error(t)" } }
    line(2) { normal { "}." }; blue { "let" }; normal { " { emit(it) }" } }
    line(1) { normal { "}." }; blue { "flowOn" }; normal { "(" }; purple { "io" }; normal { ")" } }
    line()
    line(1) { orange { "private fun" }; normal { " HttpResponse." }; blue { "status" }; normal { "() = " }; purple { "status" }; normal { "." }; blue { "run" }; normal { " { " }; purple { "value" }; blue { " to isSuccess" }; normal { "() }" } }
    line { normal { "}" } }
    line()
}
