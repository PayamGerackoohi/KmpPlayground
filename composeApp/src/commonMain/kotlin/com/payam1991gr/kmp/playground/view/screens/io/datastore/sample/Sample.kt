package com.payam1991gr.kmp.playground.view.screens.io.datastore.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendIntItem() {
    appendComposable()
    line { `fun`; normal { " State." }; blue { "IntItem" }; normal { "() = TextFieldInput(" } }
    line(1) { cyan { "tag = " }; green { "\"Int\"" }; normal { "," } }
    line(1) { cyan { "title = " }; normal { "stringResource(Res.string." }; purple { "io_datastore_int_template" }; normal { ", " }; purple { "int" }; normal { ")," } }
    line(1) { cyan { "initialData = " }; purple { "int" }; normal { "," } }
    line(1) { cyan { "converter = " }; normal { "remember { IntConverter() }," } }
    line(1) { cyan { "onTextChanged = " }; normal { "{ text -> text." }; blue { "filter" }; normal { " { it." }; blue { "isDecimal" }; normal { "() } }," } }
    line { normal { ") { " }; purple { "event" }; normal { "(Event.OnItemChanged(" }; `this`; normal { ")) }" } }
    line()
}

fun CodeEditor.appendFloatItem() {
    appendComposable()
    line { `fun`; normal { " State." }; blue { "FloatItem" }; normal { "() = TextFieldInput(" } }
    line(1) { cyan { "tag = " }; green { "\"Float\"" }; normal { "," } }
    line(1) { cyan { "title = " }; normal { "stringResource(Res.string." }; purple { "io_datastore_float_template" }; normal { ", " }; purple { "float" }; normal { ")," } }
    line(1) { cyan { "initialData = " }; purple { "float" }; normal { "," } }
    line(1) { purple { "converter = " }; normal { "remember { FloatConverter() }," } }
    line(1) { cyan { "onTextChanged = " }; normal { "{ text -> text." }; blue { "filter" }; normal { " { it." }; blue { "isFloat" }; normal { "() } }," } }
    line { normal { ") { " }; purple { "event" }; normal { "(Event.OnItemChanged(" }; `this`; normal { ")) }" } }
    line()
}

fun CodeEditor.appendByteArrayItem() {
    appendComposable()
    line { `fun`; normal { " State." }; blue { "ByteArrayItem" }; normal { "() = TextFieldInput(" } }
    line(1) { cyan { "tag = " }; green { "\"ByteArray\"" }; normal { "," } }
    line(1) { cyan { "title = " }; normal { "stringResource(" } }
    line(2) { normal { "Res.string." }; purple { "io_datastore_byte_array_template" }; normal { "," } }
    line(2) { purple { "byteArray." }; blue { "joinToString" }; normal { "(" }; green { "\",\"" }; normal { ", " }; cyan { "prefix = " }; green { "\"[\"" }; normal { ", " }; cyan { "postfix = " }; green { "\"]\"" }; normal { ")," } }
    line(1) { normal { ")," } }
    line(1) { cyan { "initialData = " }; purple { "byteArray" }; normal { "," } }
    line(1) { cyan { "converter = " }; normal { "remember { ByteArrayConverter() }," } }
    line(1) { cyan { "onTextChanged = " }; normal { "{ text -> text." }; blue { "filter" }; normal { " { it." }; blue { "isDecimalArray" }; normal { "() } }," } }
    line(1) { cyan { "canSave = " }; normal { "{ !(" }; purple { "hasError" }; normal { " || " }; purple { "value " }; blue { "contentEquals " }; purple { "byteArray" }; normal { ") }," } }
    line(1) { cyan { "visualTransformation = " }; normal { "{ text ->" } }
    line(2) { normal { "TransformedText(AnnotatedString(" }; green { "\"[" }; orange { "\$" }; normal { "text" }; green { "]\"" }; normal { "), " }; `object`; normal { " : OffsetMapping {" } }
    line(3) { orange { "override fun " }; blue { "originalToTransformed" }; normal { "(offset: Int): Int = offset + " }; cyan { "1" } }
    line(3) { orange { "override fun " }; blue { "transformedToOriginal" }; normal { "(offset: Int): Int = " }; `when`; normal { " (offset) {" } }
    line(4) { `in`; cyan { " 1" }; normal { "..<text." }; purple { "length" }; normal { " -> offset - " }; cyan { "1" } }
    line(4) { `else`; normal { " -> " }; cyan { "0" } }
    line(3) { normal { "}" } }
    line(2) { normal { "})" } }
    line(1) { normal { "}," } }
    line { normal { ") { " }; purple { "event" }; normal { "(Event.OnItemChanged(" }; `this`; normal { ")) }" } }
    line()
}

fun CodeEditor.appendState() {
    appendComposable()
    line { orange { "data class " }; normal { "State(" } }
    line(1) { `val`; purple { " showCode" }; normal { ": Boolean," } }
    line(1) { `val`; purple { " toolbarActions" }; normal { ": PersistentList<Action>," } }
    line(1) { `val`; purple { " int" }; normal { ": Int," } }
    line(1) { `val`; purple { " float" }; normal { ": Float," } }
    line(1) { `val`; purple { " byteArray" }; normal { ": ByteArray," } }
    line(1) { `val`; purple { " event" }; normal { ": (Event) -> Unit," } }
    line { normal { ") : CircuitUiState {" } }
    line(1) { orange { "sealed interface" }; normal { " Event : CircuitUiEvent {" } }
    line(2) { orange { "data class" }; normal { " OnItemChanged<" }; `out`; teal { " T" }; normal { ">(" }; `val`; purple { " value" }; normal { ": " }; teal { "T" }; normal { ") : Event" } }
    line(1) { normal { "}" } }
    line()
    line(1) { `class`; normal { " Data(" } }
    line(2) { orange { "private val" }; purple { " dataStore" }; normal { ": DataStore<Preferences>," } }
    line(2) { normal { "int: ComposeState<Int>," } }
    line(2) { normal { "float: ComposeState<Float>," } }
    line(2) { normal { "byteArray: ComposeState<ByteArray>," } }
    line(2) { orange { "private val" }; purple { " scope" }; normal { ": CoroutineScope," } }
    line(1) { normal { ") {" } }
    line(2) { orange { "companion object" }; normal { " {" } }
    line(3) { `val`; purple { " intKey" }; normal { " = intPreferencesKey(" }; green { "\"int item\"" }; normal { ")" } }
    line(3) { `val`; purple { " floatKey" }; normal { " = floatPreferencesKey(" }; green { "\"float item\"" }; normal { ")" } }
    line(3) { `val`; purple { " byteArrayKey" }; normal { " = byteArrayPreferencesKey(" }; green { "\"byte array item\"" }; normal { ")" } }
    line(2) { normal { "}" } }
    line()
    line(2) { `val`; purple { " int " }; `by`; normal { " int" } }
    line(2) { `val`; purple { " float " }; `by`; normal { " float" } }
    line(2) { `val`; purple { " byteArray " }; `by`; normal { " byteArray" } }
    line()
    line(2) { orange { "private fun" }; blue { " update" }; normal { "(block: (MutablePreferences) -> Unit) = " }; purple { "scope" }; normal { "." }; blue { "launch" }; normal { " {" } }
    line(3) { purple { "dataStore" }; normal { ".edit(block)" } }
    line(2) { normal { "}" } }
    line()
    line(2) { `fun`; blue { " update" }; normal { "(value: Int) = update { it[" }; purple { "intKey" }; normal { "] = value }" } }
    line(2) { `fun`; blue { " update" }; normal { "(value: Float) = update { it[" }; purple { "floatKey" }; normal { "] = value }" } }
    line(2) { `fun`; blue { " update" }; normal { "(value: ByteArray) = update { it[" }; purple { "byteArrayKey" }; normal { "] = value }" } }
    line(1) { normal { "}" } }
    line()
    line(1) { orange { "override fun " }; blue { "equals" }; normal { "(other: Any?): Boolean {" } }
    line(2) { `if`; normal { " (" }; `this`; normal { " === other) " }; orange { "return true" } }
    line(2) { `if`; normal { " (other == " }; `null`; normal { " || " }; `this`; normal { "::" }; `class`; normal { " != other::" }; `class`; normal { ") " }; orange { "return false" } }
    line()
    line(2) { normal { "other " }; `as`; normal { " State" } }
    line(2) { `if`; normal { " (" }; purple { "showCode" }; normal { " != other." }; purple { "showCode" }; normal { ") " }; orange { "return false" } }
    line(2) { `if`; normal { " (" }; purple { "int" }; normal { " != other." }; purple { "int" }; normal { ") " }; orange { "return false" } }
    line(2) { `if`; normal { " (" }; purple { "float" }; normal { " != other." }; purple { "float" }; normal { ") " }; orange { "return false" } }
    line(2) { `if`; normal { " (" }; purple { "float" }; normal { " != other." }; purple { "float" }; normal { ") " }; orange { "return false" } }
    line(2) { `if`; normal { " (!" }; purple { "byteArray" }; normal { "." }; blue { "contentEquals" }; normal { "(other." }; purple { "byteArray" }; normal { ")) " }; orange { "return false" } }
    line()
    line(2) { orange { "return true" } }
    line(1) { normal { "}" } }
    line()
    line(1) { orange { "override fun" }; blue { " hashCode" }; normal { "(): Int {" } }
    line(2) { `var`; normal { " result = " }; purple { "showCode" }; normal { ".hashCode()" } }
    line(2) { normal { "result = " }; cyan { "31" }; normal { " * result + " }; purple { "int" } }
    line(2) { normal { "result = " }; cyan { "31" }; normal { " * result + " }; purple { "float" }; normal { ".hashCode()" } }
    line(2) { normal { "result = " }; cyan { "31" }; normal { " * result + " }; purple { "byteArray" }; normal { "." }; blue { "contentHashCode" }; normal { "()" } }
    line(2) { `return`; normal { " result" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
    appendRememberData()
}

fun CodeEditor.appendRememberData() {
    appendComposable()
    line { `fun`; blue { " rememberData" }; normal { "(" } }
    line(1) { normal { "dataStore: DataStore<Preferences>," } }
    line(1) { normal { "initialIntItem: Int = " }; cyan { "0" }; normal { "," } }
    line(1) { normal { "initialFloatItem: Float = " }; cyan { "0f" }; normal { "," } }
    line(1) { normal { "initialByteArrayItem: ByteArray = byteArrayOf()," } }
    line { normal { "): State.Data {" } }
    line(1) { `val`; normal { " intItem = remember { mutableStateOf(initialIntItem) }" } }
    line(1) { `val`; normal { " floatItem = remember { mutableStateOf(initialFloatItem) }" } }
    line(1) { `val`; normal { " byteArrayItem = remember { mutableStateOf(initialByteArrayItem) }" } }
    line(1) { normal { "dataStore." }; purple { "data" }; normal { "." }; blue { "collectAsState" }; normal { "(" }; `null`; normal { ")." }; purple { "value" }; normal { "?." }; blue { "let" }; normal { " { pref ->" } }
    line(2) { normal { "pref[State.Data." }; purple { "intKey" }; normal { "]?." }; blue { "let" }; normal { " { intItem." }; purple { "value" }; normal { " = it }" } }
    line(2) { normal { "pref[State.Data." }; purple { "floatKey" }; normal { "]?." }; blue { "let" }; normal { " { floatItem." }; purple { "value" }; normal { " = it }" } }
    line(2) { normal { "pref[State.Data." }; purple { "byteArrayKey" }; normal { "]?." }; blue { "let" }; normal { " { byteArrayItem." }; purple { "value" }; normal { " = it }" } }
    line(1) { normal { "}" } }
    line(1) { `val`; normal { " scope = rememberCoroutineScope()" } }
    line(1) { `return`; normal { " remember { State.Data(dataStore, intItem, floatItem, byteArrayItem, scope) }" } }
    line { normal { "}" } }
    line()
}
