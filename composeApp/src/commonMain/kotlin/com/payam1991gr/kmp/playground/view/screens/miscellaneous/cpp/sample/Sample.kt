package com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendConverter() {
    line { orange { "private val " }; purple { "converter" }; normal { " = " }; `object`; normal { " : Converter<String> {" } }
    line(1) { orange { "override fun " }; blue { "from" }; normal { "(data: String): String = data" } }
    line(1) { orange { "override fun " }; normal { "String." }; blue { "toData" }; normal { "(): Data<String> = buildData {" } }
    line(2) { blue { "trim" }; normal { "()." }; blue { "toULongOrNull" }; normal { "()?." }; blue { "let" }; normal { " { " }; `this`; teal { "@toData" }; normal { " } ?: error() ?: " }; green { "\"\"" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendDecimalFormatTransformation() {
    line { orange { "private val" }; purple { " decimalFormatTransformation" }; normal { " = DecimalFormatTransformation()" } }
    line()
    line { `class`; normal { " DecimalFormatTransformation(" } }
    line(1) { orange { "private val" }; purple { " formatFactory" }; normal { ": (String) -> DecimalFormat = { DecimalFormatImpl(it) }," } }
    line { normal { ") : VisualTransformation {" } }
    line(1) { orange { "override fun" }; blue { " filter" }; normal { "(text: AnnotatedString) = " }; purple { "formatFactory" }; normal { "(text." }; purple { "text" }; normal { ")." }; blue { "let" }; normal { " { format ->" } }
    line(2) { normal { "TransformedText(format." }; purple { "annotatedString" }; normal { ", " }; `object`; normal { " : OffsetMapping {" } }
    line(3) { orange { "override fun" }; blue { " originalToTransformed" }; normal { "(offset: Int) = format.originalToTransformed(offset)" } }
    line(3) { orange { "override fun" }; blue { " transformedToOriginal" }; normal { "(offset: Int) = format.transformedToOriginal(offset)" } }
    line(2) { normal { "})" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendNumberInput() {
    appendComposable()
    line { orange { "private fun" }; normal { " State." }; blue { "NumberInput" }; normal { "(placeholder: String = " }; green { "\"18446744073709551615\"" }; normal { "." }; blue { "decimalFormat" }; normal { "()) {" } }
    line(1) { `var`; normal { " suffix " }; `by`; normal { " remember(" }; purple { "inputNumber" }; normal { ") {" } }
    line(2) { normal { "mutableStateOf(" }; purple { "inputNumber" }; normal { "." }; blue { "ifEmpty" }; normal { " { placeholder }." }; blue { "toScientific" }; normal { "())" } }
    line(1) { normal { "}" } }
    line(1) { normal { "TextFieldInput(" } }
    line(2) { cyan { "tag = " }; green { "\"Number\"" }; normal { "," } }
    line(2) { cyan { "title = " }; `when`; normal { " (" }; purple { "inputCalculation" }; normal { ") {" } }
    line(3) { `is`; normal { " State.Number.Calculating -> stringResource(Res.string." }; purple { "miscellaneous_cpp_calculating" }; normal { ")" } }
    line(3) { `is`; normal { " State.Number.Data -> MathFactors.annotatedString(" }; purple { "inputCalculation" }; normal { "." }; purple { "factors" }; normal { ")" } }
    line(2) { normal { "}," } }
    line(2) { cyan { "initialData =" }; purple { " inputNumber" }; normal { "," } }
    line(2) { cyan { "converter =" }; purple { " converter" }; normal { "," } }
    line(2) { cyan { "onTextChanged = " }; normal { "{ text ->" } }
    line(3) { normal { "text." }; blue { "filter" }; normal { " { it." }; blue { "isDigit" }; normal { "() }." }; blue { "apply" }; normal { " {" } }
    line(4) { normal { "suffix = " }; blue { "ifEmpty" }; normal { " { placeholder }." }; blue { "toScientific" }; normal { "()" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}," } }
    line(2) { cyan { "confirmButtonLabel =" }; normal { " stringResource(Res.string." }; purple { "miscellaneous_cpp_calculate" }; normal { ")," } }
    line(2) { cyan { "visualTransformation =" }; purple { " decimalFormatTransformation," } }
    line(2) { cyan { "canEdit =" }; purple { " canEdit," } }
    line(2) { cyan { "label =" }; normal { " { Text(stringResource(Res.string." }; purple { "miscellaneous_cpp_number" }; normal { ")) }," } }
    line(2) { cyan { "placeholder =" }; normal { " { Text(placeholder, " }; cyan { "maxLines = 1" }; normal { ", " }; cyan { "overflow =" }; normal { " TextOverflow." }; purple { "Clip" }; normal { ") }," } }
    line(2) { cyan { "suffix =" }; normal { " { Text(suffix) }," } }
    line(2) { cyan { "keyboardOptions =" }; normal { " KeyboardOptions(" }; cyan { "keyboardType =" }; normal { " KeyboardType." }; purple { "Number" }; normal { ")," } }
    line(1) { normal { ") { " }; purple { "event" }; normal { "(Event.OnNumberChanged(" }; `this`; normal { ")) }" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendRandomCalculations() {
    appendComposable()
    line { orange { "private fun" }; normal { " State." }; blue { "RandomCalculations" }; normal { "() = ContentList(Modifier." }; blue { "testTag" }; normal { "(" }; green { "\"Random Calculations\"" }; normal { ")) {" } }
    line(1) { normal { "Button(" } }
    line(2) { cyan { "onClick =" }; normal { " { " }; purple { "event" }; normal { "(Event.PerformRandomCalculations) }," } }
    line(2) { cyan { "enabled =" }; purple { " canCalculate" }; normal { "," } }
    line(1) { normal { ") { Text(stringResource(Res.string." }; purple { "miscellaneous_cpp_random_calculations" }; normal { ")) }" } }
    line(1) { normal { "CompositionLocalProvider(" }; purple { "LocalLayoutDirection" }; normal { " provides LayoutDirection." }; purple { "Ltr" }; normal { ") {" } }
    line(2) { purple { "calculations" }; normal { "." }; blue { "forEach" }; normal { " { it." }; blue { "CalculationCard" }; normal { "() }" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendCalculationCard() {
    appendComposable()
    line { `fun`; normal { " State.Number." }; blue { "CalculationCard" }; normal { "() = Card(" } }
    line(1) { cyan { "shape =" }; normal { " RoundedCornerShape(" }; cyan { "16" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(1) { cyan { "colors =" }; normal { " CardDefaults.cardColors(" }; cyan { "containerColor =" }; blue { " cardColor" }; normal { "())," } }
    line(1) { cyan { "modifier =" }; normal { " Modifier" } }
    line(2) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(2) { normal { "." }; blue { "stateOf" }; normal { "(" }; `this`; normal { ")" } }
    line { normal { ") {" } }
    line(1) { normal { "Crossfade(" }; `this`; teal { "@CalculationCard" }; normal { ") {" } }
    line(2) { normal { "SelectionContainer(Modifier." }; blue { "padding" }; normal { "(" }; cyan { "horizontal = 16" }; normal { "." }; purple { "dp" }; normal { ", " }; cyan { "vertical = 8" }; normal { "." }; purple { "dp" }; normal { ")) {" } }
    line(3) { `when`; normal { " (it) {" } }
    line(4) { `is`; normal { " State.Number.Calculating -> Text(" }; green { "\"" }; orange { "\$" }; purple { "value" }; green { " = …\"" }; normal { ")" } }
    line(4) { `is`; normal { " State.Number.Data -> MathFactors(it." }; purple { "factors" }; normal { ", " }; cyan { "prefix =" }; green { " \"" }; orange { "\$" }; purple { "value" }; green { " = \"" }; normal { ")" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendStateOf() {
    line { orange { "private fun" }; normal { " Modifier." }; blue { "stateOf" }; normal { "(number: State.Number) = " }; blue { "semantics" }; normal { "(" }; cyan { "mergeDescendants = " }; _true; normal { ") {" } }
    line(1) { purple { "stateDescription" }; normal { " = " }; `when`; normal { " (number) {" } }
    line(2) { `is`; normal { " State.Number.Calculating -> " }; green { "\"Calculating\"" } }
    line(2) { `is`; normal { " State.Number.Data -> " }; green { "\"Calculated\"" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendCardColor() {
    appendComposable()
    line { `fun`; normal { " State.Number." }; blue { "cardColor" }; normal { "() = " }; `when`; normal { " (" }; `this`; normal { ") {" } }
    line(1) { `is`; normal { " State.Number.Calculating -> MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "tertiaryContainer" } }
    line(1) { `is`; normal { " State.Number.Data -> MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "secondaryContainer" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendState() {
    line { orange { "data class" }; normal { " State(" } }
    line(1) { `val`; purple { " canEdit" }; normal { ": Boolean," } }
    line(1) { `val`; purple { " canCalculate" }; normal { ": Boolean," } }
    line(1) { `val`; purple { " inputNumber" }; normal { ": String," } }
    line(1) { `val`; purple { " inputCalculation" }; normal { ": Number," } }
    line(1) { `val`; purple { " calculations" }; normal { ": PersistentList<Number>," } }
    line(1) { `val`; purple { " event" }; normal { ": (Event) -> Unit," } }
    line { normal { ") : CircuitUiState {" } }
    line(1) { orange { "sealed interface" }; normal { " Event : CircuitUiEvent {" } }
    line(2) { orange { "data class" }; normal { " OnNumberChanged(" }; `val`; purple { " number" }; normal { ": String) : Event" } }
    line(2) { orange { "data object" }; normal { " PerformRandomCalculations : Event" } }
    line(1) { normal { "}" } }
    line()
    line(1) { orange { "sealed interface" }; normal { " Number {" } }
    line(2) { orange { "data class" }; normal { " Calculating(" }; `val`; purple { " number" }; normal { ": String) : Number" } }
    line(2) { orange { "data class" }; normal { " Data(" } }
    line(3) { `val`; purple { " number" }; normal { ": String = " }; green { "\"\"" }; normal { "," } }
    line(3) { `val`; purple { " factors" }; normal { ": PersistentList<Factor> = persistentListOf()," } }
    line(2) { normal { ") : Number" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
