package com.payam1991gr.kmp.playground.view.module.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendContentList() {
    appendComposable()
    line { `fun`; blue { " ContentList" }; normal { "(" } }
    line(1) { normal { "modifier: Modifier = Modifier," } }
    line(1) { normal { "content: " }; yellow { "@Composable " }; normal { "ColumnScope.() -> Unit," } }
    line { normal { ") = Column(" } }
    line(1) { cyan { "horizontalAlignment = " }; normal { "Alignment." }; purple { "CenterHorizontally" }; normal { "," } }
    line(1) { cyan { "verticalArrangement = " }; normal { "Arrangement.spacedBy(" }; cyan { "8" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(1) { cyan { "modifier = " }; normal { "Modifier." }; blue { "fillMaxWidth" }; normal { "()," } }
    line(1) { cyan { "content = " }; normal { "content," } }
    line { normal { ")" } }
    line()
}

fun CodeEditor.appendModule() {
    appendComposable()
    line { `fun`; blue { " Module" }; normal { "(" } }
    line(1) { normal { "label: String," } }
    line(1) { normal { "onToggle: () -> Unit," } }
    line(1) { normal { "colors: Module.Colors = Module.Defaults.colors()," } }
    line(1) { normal { "animator: " }; composable; normal { " () -> Unit," } }
    line { normal { ") = Column(Modifier." }; blue { "fillMaxWidth" }; normal { "()) {" } }
    line(1) { normal { "TextButton(" } }
    line(2) { cyan { "onClick = " }; normal { "onToggle," } }
    line(2) { cyan { "colors = " }; normal { "ButtonDefaults.textButtonColors(" } }
    line(3) { cyan { "containerColor = " }; normal { "colors." }; purple { "header" }; normal { "," } }
    line(3) { cyan { "contentColor = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "onSecondary" }; normal { "," } }
    line(2) { normal { ")," } }
    line(2) { cyan { "shape = " }; normal { "RoundedCornerShape(" }; cyan { "topStart = 32" }; normal { "." }; purple { "dp" }; normal { ", " }; cyan { "topEnd = 32" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(2) { cyan { "modifier = " }; normal { "Modifier" } }
    line(3) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(3) { normal { "." }; blue { "height" }; normal { "(" }; cyan { "48" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(1) { normal { ") {" } }
    line(2) { normal { "Text(" } }
    line(3) { normal { "label," } }
    line(3) { cyan { "maxLines = 1" }; normal { "," } }
    line(3) { cyan { "overflow = " }; normal { "TextOverflow." }; purple { "Ellipsis" }; normal { "," } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line(1) { normal { "animator()" } }
    line { normal { "}" } }
    line()
    line { `object`; normal { " Module {" } }
    line(1) { immutable }
    line(1) { `class`; normal { " Colors(" }; `val`; purple { " header" }; normal { ": Color)" } }
    line()
    line(1) { `object`; normal { " Defaults {" } }
    appendComposable(2)
    line(2) { `fun`; blue { " colors" }; normal { "(header: Color = MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "secondary" }; normal { "): Colors = Colors(header)" } }
    line(1) { normal { "}" } }
    line()
    appendComposable(1)
    line(1) { `fun`; blue { " Content" }; normal { "(tag: String, cornerRadius: Dp = " }; cyan { "0" }; normal { "." }; purple { "dp" }; normal { ") {" } }
    line(2) { normal { "Box(" } }
    line(3) { normal { "Modifier" } }
    line(4) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(4) { normal { "." }; blue { "clip" }; normal { "(" } }
    line(5) { normal { "RoundedCornerShape(" } }
    line(6) { cyan { "bottomStart = " }; normal { "cornerRadius," } }
    line(6) { cyan { "bottomEnd = " }; normal { "cornerRadius," } }
    line(5) { normal { ")" } }
    line(4) { normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "RandomImage(" } }
    line(4) { cyan { "contentDescription = " }; green { "\"" }; orange { "\$" }; normal { "tag." }; green { "Content\"" }; normal { "," } }
    line(4) { cyan { "modifier = " }; normal { "Modifier." }; blue { "moduleSize" }; normal { "()" } }
    line(3) { normal { ")" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendModuleSize() {
    line { `fun`;normal { " Modifier." }; blue { "moduleSize" }; normal { "(ratio: Float = " }; cyan { "2f" }; normal { ") = " }; `this` }
    line(1) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(1) { normal { "." }; blue { "aspectRatio" }; normal { "(ratio)" } }
    line()
}

fun CodeEditor.appendTextFieldInput() {
    appendComposable()
    line { `fun`; normal { " <" }; teal { "T" }; normal { "> " }; blue { "TextFieldInput" }; normal { "(" } }
    line(1) { normal { "title: String," } }
    line(1) { normal { "initialData: " }; teal { "T" }; normal { "," } }
    line(1) { normal { "converter: Converter<" }; teal { "T" }; normal { ">," } }
    line(1) { normal { "modifier: Modifier = Modifier," } }
    line(1) { normal { "tag: String = " }; green { "\"\"" }; normal { "," } }
    line(1) { normal { "onTextChanged: (String) -> String = { it }," } }
    line(1) { normal { "canSave: Data<" }; teal { "T" }; normal { ">.() -> Boolean = { !(" }; purple { "hasError" }; normal { " || " }; purple { "value" }; normal { " == initialData) }," } }
    line(1) { normal { "visualTransformation: VisualTransformation = VisualTransformation." }; purple { "None" }; normal { "," } }
    line(1) { normal { "onSave: " }; teal { "T" }; normal { ".() -> Unit," } }
    line { normal { ") {" } }
    line(1) { `var`; normal { " value " }; `by`; normal { " remember(initialData) { mutableStateOf(converter.from(initialData)) }" } }
    line(1) { `val`; normal { " data = remember(value) { converter." }; blue { "run" }; normal { " { value." }; blue { "toData" }; normal { "() } }" } }
    line(1) { `val`; normal { " localTag = " }; green { "\"TextFieldInput\"" }; blue { " merge " }; normal { "tag" } }
    line(1) { normal { "Column(" } }
    line(2) { cyan { "verticalArrangement = " }; normal { "Arrangement.spacedBy(" }; cyan { "8" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(2) { cyan { "modifier = " }; normal { "modifier," } }
    line(1) { normal { ") {" } }
    line(2) { normal { "Header(title)" } }
    line(2) { normal { "Row(" } }
    line(3) { cyan { "horizontalArrangement = " }; normal { "Arrangement.spacedBy(" }; cyan { "8" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(3) { cyan { "verticalAlignment = " }; normal { "Alignment." }; purple { "CenterVertically" }; normal { "," } }
    line(2) { normal { ") {" } }
    line(3) { normal { "TextField(" } }
    line(4) { cyan { "value = " }; normal { "value," } }
    line(4) { cyan { "onValueChange = " }; normal { "{ value = onTextChanged(it) }," } }
    line(4) { cyan { "singleLine = " }; _true; normal { "," } }
    line(4) { cyan { "visualTransformation = " }; normal { "visualTransformation," } }
    line(4) { cyan { "isError = " }; normal { "data." }; purple { "hasError" }; normal { "," } }
    line(4) { cyan { "colors = " }; normal { "TextFieldDefaults.colors(" } }
    line(5) { cyan { "errorContainerColor = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "errorContainer" }; normal { "," } }
    line(4) { normal { ")," } }
    line(4) { cyan { "modifier = " }; normal { "Modifier" } }
    line(5) { normal { "." }; blue { "weight" }; normal { "(" }; cyan { "1f" }; normal { ")" } }
    line(5) { normal { "." }; blue { "testTag" }; normal { "(localTag)" } }
    line(3) { normal { ")" } }
    line(3) { normal { "Button(" } }
    line(4) { normal { "{ onSave(data." }; purple { "value" }; normal { ") }," } }
    line(4) { cyan { "enabled = " }; normal { "data.canSave()," } }
    line(4) { cyan { "modifier = " }; normal { "Modifier." }; blue { "testTag" }; normal { "(localTag " }; blue { "merge " }; green { "\"SaveButton\"" }; normal { ")" } }
    line(3) { normal { ") {" } }
    line(4) { normal { "Text(stringResource(Res.string." }; purple { "save" }; normal { "))" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendAnalogClock() {
    appendComposable()
    line { `fun`; blue { " AnalogClock" }; normal { "(" } }
    line(1) { normal { "date: LocalDate," } }
    line(1) { normal { "time: LocalTime," } }
    line(1) { normal { "zone: TimeZoneData," } }
    line(1) { normal { "modifier: Modifier = Modifier," } }
    line(1) { normal { "tag: String = " }; green { "\"\"" }; normal { "," } }
    line { normal { ") = KmpPlaygroundTheme(" }; cyan { "darkTheme =" }; normal { " time." }; blue { "isNight" }; normal { "()) {" } }
    line(1) { normal { "DateTimeData(date, time, zone)." }; blue { "BaseAnalogClock" }; normal { "(modifier, tag)" } }
    line { normal { "}" } }
    line()
    appendBaseAnalogClock()
    appendObject_AnalogClock()
    appendRememberAnalogClockState()
}

fun CodeEditor.appendBaseAnalogClock() {
    appendComposable()
    line { `fun`; normal { " DateTimeData." }; blue { "BaseAnalogClock" }; normal { "(" } }
    line(1) { normal { "modifier: Modifier = Modifier," } }
    line(1) { normal { "tag: String = " }; green { "\"\"" }; normal { "," } }
    line(1) { normal { "strokes: Strokes = Defaults.strokes()," } }
    line(1) { normal { "colors: Colors = Defaults.colors()," } }
    line { normal { ") {" } }
    line(1) { `val`; normal { " state = rememberAnalogClockState(" }; purple { "time" }; normal { ")" } }
    line(1) { normal { "Box(" } }
    line(2) { normal { "modifier" } }
    line(3) { normal { "." }; blue { "aspectRatio" }; normal { "(" }; cyan { "1f" }; normal { ")" } }
    line(3) { normal { "." }; blue { "testTag" }; normal { "(" }; green { "\"AnalogClock\"" }; blue { " merge" }; normal { " tag)" } }
    line(3) { normal { "." }; blue { "clearAndSetSemantics" }; normal { " { " }; purple { "stateDescription" }; normal { " = " }; purple { "timeFormat" }; normal { ".format(" }; purple { "time" }; normal { ") }" } }
    line(1) { normal { ") {" } }
    line(2) { normal { "Background(state, colors, strokes)" } }
    line(2) { normal { "Hands(state, colors, strokes)" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

@Suppress("FunctionName")
fun CodeEditor.appendObject_AnalogClock() {
    line { `object`; normal { " AnalogClock {" } }
    line(1) { orange { "private const val" }; purple { " HOUR_RADIUS" }; normal { " = " }; cyan { ".5f" } }
    line(1) { orange { "private const val" }; purple { " MINUTE_RADIUS" }; normal { " = " }; cyan { ".93f" } }
    line(1) { orange { "private const val" }; purple { " SECOND_LONG_RADIUS" }; normal { " = " }; cyan { ".96f" } }
    line(1) { orange { "private const val" }; purple { " SECOND_SHORT_RADIUS" }; normal { " = " }; cyan { ".2f" } }
    line(1) { orange { "private const val" }; purple { " TICK_START" }; normal { " = " }; cyan { ".9f" } }
    line(1) { orange { "private const val" }; purple { " TICK_END" }; normal { " = " }; cyan { ".96f" } }
    line(1) { orange { "private const val" }; purple { " NUMBER_POSITION" }; normal { " = " }; cyan { ".84f" } }
    line()
    line(1) { orange { "private fun" }; normal { " Vec." }; blue { "toOffset" }; normal { "() = Offset(get(" }; cyan { "0" }; normal { "), get(" }; cyan { "1" }; normal { "))" } }
    line()
    appendComposable(1)
    line(1) { `fun`; blue { " Background" }; normal { "(state: State, colors: Colors, strokes: Strokes) = Background." }; blue { "apply" }; normal { " {" } }
    line(2) { normal { "Box(Modifier" } }
    line(3) { normal { "." }; blue { "fillMaxSize" }; normal { "()" } }
    line(3) { normal { "." }; blue { "drawWithCache" }; normal { "()" } }
    line(4) { normal { "onDrawBehind {" } }
    line(5) { normal { "drawCircle(colors." }; purple { "background" }; normal { ")" } }
    line(5) { blue { "drawTicks" }; normal { "(state, colors, strokes)" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}) {" } }
    line(3) { blue { "Numbers" }; normal { "(colors)" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line()
    appendComposable(1)
    line(1) { `fun`; blue { " Hands" }; normal { "(state: State, colors: Colors, strokes: Strokes) = Hands." }; blue { "apply" }; normal { " {" } }
    line(2) { normal { "Box(Modifier" } }
    line(3) { normal { "." }; blue { "fillMaxSize" }; normal { "()" } }
    line(3) { normal { "." }; blue { "drawBehind" }; normal { "()" } }
    line(4) { normal { "state." }; blue { "run" }; normal { " { " }; blue { "hands" }; normal { "() }." }; blue { "apply" }; normal { " {" } }
    line(5) { blue { "drawHour" }; normal { "(" }; purple { "hour" }; normal { ", colors." }; purple { "foreground" }; normal { ", strokes." }; purple { "hour" }; normal { ")" } }
    line(5) { blue { "drawMinute" }; normal { "(" }; purple { "minute" }; normal { ", colors." }; purple { "foreground" }; normal { ", strokes." }; purple { "minute" }; normal { ")" } }
    line(5) { blue { "drawSecond" }; normal { "(" }; purple { "second" }; normal { ", colors, strokes." }; purple { "thin" }; normal { ")" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line()
    line(1) { orange { "private fun" }; normal { " DrawScope." }; purple { "radius" }; normal { "() = " }; purple { "size" }; normal { "." }; purple { "width" }; normal { " / " }; cyan { "2f" } }
    line()
    line(1) { `class`; normal { " State(" } }
    line(2) { orange { "private val" }; purple { " hour" }; normal { ": Float," } }
    line(2) { orange { "private val" }; purple { " minute" }; normal { ": Float," } }
    line(2) { orange { "private val" }; purple { " second" }; normal { ": Float," } }
    line(1) { normal { ") {" } }
    line(2) { `fun`; normal { " DrawScope." }; blue { "hands" }; normal { "(): Hands = " }; blue { "radius" }; normal { "()." }; blue { "let" }; normal { " { radius ->" } }
    line(3) { normal { "Hands(" } }
    line(4) { cyan { "hour =" }; purple { " center" }; blue { " to" }; purple { " center" }; normal { " + " }; purple { "hour" }; normal { "." }; blue { "toOffset" }; normal { "() * (" }; purple { "HOUR_RADIUS" }; normal { " * radius)," } }
    line(4) { cyan { "minute =" }; purple { " center" }; blue { " to" }; purple { " center" }; normal { " + " }; purple { "minute" }; normal { "." }; blue { "toOffset" }; normal { "() * (" }; purple { "MINUTE_RADIUS" }; normal { " * radius)," } }
    line(4) { cyan { "second =" }; purple { " second" }; normal { "." }; blue { "toOffset" }; normal { "()." }; blue { "let" }; normal { " { offset ->" } }
    line(5) { purple { "center" }; normal { " - offset * (" }; purple { "SECOND_SHORT_RADIUS" }; normal { " * radius) " }; blue { "to" } }
    line(7) { purple { "center" };normal { " + offset * (" }; purple { "SECOND_LONG_RADIUS" }; normal { " * radius)" } }
    line(4) { normal { "}," } }
    line(3) { normal { ")" } }
    line(2) { normal { "}" } }
    line()
    line(2) { `fun`; normal { " DrawScope." }; blue { "tick" }; normal { "(p1: Offset, p2: Offset): Offsets = " }; blue { "radius" }; normal { "()." }; blue { "let" }; normal { " { radius ->" } }
    line(3) { purple { "center" }; normal { " + p1 * radius " }; blue { "to" }; purple { " center" }; normal { " + p2 * radius" } }
    line(2) { normal { "}" } }
    line()
    line(2) { orange { "private fun" }; normal { " Float." }; blue { "toOffset" }; normal { "() = Offset(cos(" }; `this`; normal { "), sin(" }; `this`; normal { "))" } }
    line(1) { normal { "}" } }
    line()
    line(1) { orange { "data class" }; normal { " Hands(" }; `val`; purple { " hour" }; normal { ": Offsets, " }; `val`; purple { " minute" }; normal { ": Offsets, " }; `val`; purple { " second" }; normal { ": Offsets) {" } }
    line(2) { orange { "companion object" }; normal { " {" } }
    line(3) { `fun`; normal { " DrawScope." }; blue { "drawHour" }; normal { "(data: Offsets, color: Color, stroke: Float) = data." }; blue { "apply" }; normal { " {" } }
    line(4) { normal { "drawLine(color, " }; purple { "first" }; normal { ", " }; purple { "second" }; normal { ", stroke, StrokeCap." }; purple { "Round" }; normal { ")" } }
    line(3) { normal { "}" } }
    line()
    line(3) { `fun`; normal { " DrawScope." }; blue { "drawMinute" }; normal { "(data: Offsets, color: Color, stroke: Float) = data." }; blue { "apply" }; normal { " {" } }
    line(4) { normal { "drawLine(color, " }; purple { "first" }; normal { ", " }; purple { "second" }; normal { ", stroke, StrokeCap." }; purple { "Round" }; normal { ")" } }
    line(3) { normal { "}" } }
    line()
    line(3) { `fun`; normal { " DrawScope." }; blue { "drawSecond" }; normal { "(data: Offsets, colors: Colors, stroke: Float) = colors." }; blue { "apply" }; normal { " {" } }
    line(4) { `val`; normal { " radius = " }; blue { "radius" }; normal { "()" } }
    line(4) { normal { "drawCircle(" }; purple { "foreground" }; normal { ", " }; cyan { "radius =" }; normal { " radius / " }; cyan { "20f" }; normal { ")" } }
    line(4) { normal { "drawCircle(" }; purple { "secondsHand" }; normal { ", " }; cyan { "radius =" }; normal { " radius / " }; cyan { "27f" }; normal { ")" } }
    line(4) { normal { "data." }; blue { "apply" }; normal { " {" } }
    line(5) { normal { "drawLine(" }; purple { "secondsHand" }; normal { ", " }; purple { "first" }; normal { ", " }; purple { "second" }; normal { ", stroke, StrokeCap." }; purple { "Round" }; normal { ")" } }
    line(4) { normal { "}" } }
    line(4) { normal { "drawCircle(" }; purple { "background" }; normal { ", " }; cyan { "radius =" }; normal { " radius / " }; cyan { "50f" }; normal { ")" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line()
    line(1) { `object`; normal { " Background {" } }
    line(2) { orange { "private val" }; purple { " rotation30deg " }; `by`; normal { " lazy { Mat.rotationOf((" }; purple { "PI" }; normal { " / " }; cyan { "6.0" }; normal { ").toFloat()) }" } }
    line(2) { orange { "private val" }; purple { " rotation6deg " }; `by`; normal { " lazy { Mat.rotationOf((" }; purple { "PI" }; normal { " / " }; cyan { "30.0" }; normal { ").toFloat()) }" } }
    line()
    line(2) { orange { "private val" }; purple { " tickPositions " }; `by`; normal { " lazy {" } }
    line(3) { normal { "mutableListOf<Offsets>()." }; blue { "apply" }; normal { " {" } }
    line(4) { `var`; normal { " start = Vec.tor(" }; cyan { "0f" }; normal { ", -" }; purple { "TICK_START" }; normal { ")" } }
    line(4) { `var`; normal { " end = Vec.tor(" }; cyan { "0f" }; normal { ", -" }; purple { "TICK_END" }; normal { ")" } }
    line(4) { normal { "add(start." }; blue { "toOffset" }; normal { "() " }; blue { "to" }; normal { " end." }; blue { "toOffset" }; normal { "())" } }
    line(4) { normal { "repeat(" }; cyan { "59" }; normal { ") {" } }
    line(5) { normal { "start = (" }; purple { "rotation6deg" }; normal { " * start).flatten()" } }
    line(5) { normal { "end = (" }; purple { "rotation6deg" }; normal { " * end).flatten()" } }
    line(5) { normal { "add(start." }; blue { "toOffset" };normal { "() " };blue { "to" }; normal { " end." }; blue { "toOffset" }; normal { "())" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line()
    line(2) { orange { "private val" }; purple { " numberPositions " }; `by`; normal { " lazy {" } }
    line(3) { normal { "mutableListOf<Vec>()." }; blue { "apply" }; normal { " {" } }
    line(4) { `var`; normal { " position = Vec.tor(" }; cyan { "0f" }; normal { ", -" }; purple { "NUMBER_POSITION" }; normal { ")" } }
    line(4) { normal { "repeat(" }; cyan { "12" }; normal { ") {" } }
    line(5) { normal { "position = (" }; purple { "rotation30deg" }; normal { " * position).flatten()" } }
    line(5) { normal { "add(position)" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}." }; blue { "toPersistentList" }; normal { "()" } }
    line(2) { normal { "}" } }
    line()

    appendComposable(2)
    line(2) { `fun`; normal { " BoxScope." }; blue { "Numbers" }; normal { "(colors: Colors) = repeat(" }; cyan { "12" }; normal { ") {" } }
    line(3) { purple { "numberPositions" }; normal { "[it]." }; blue { "let" }; normal { " { position ->" } }
    line(4) { normal { "Text(" } }
    line(5) { normal { "(it + " }; cyan { "1" }; normal { ").toString()," } }
    line(5) { cyan { "color =" }; normal { " colors." }; purple { "foreground" }; normal { "," } }
    line(5) { cyan { "modifier =" }; normal { " Modifier." }; blue { "align" }; normal { "(BiasAlignment(position[" }; cyan { "0" }; normal { "], position[" }; cyan { "1" }; normal { "]))" } }
    line(4) { normal { ")" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line()
    line(2) { `fun`; normal { " DrawScope." }; blue { "drawTicks" }; normal { "(state: State, colors: Colors, strokes: Strokes) {" } }
    line(3) { purple { "tickPositions" }; normal { "." }; blue { "forEachIndexed" }; normal { " { index, (p1, p2) ->" } }
    line(4) { `val`; normal { " (start, end) = state." }; blue { "run" }; normal { " { " }; blue { "tick" }; normal { "(p1, p2) }" } }
    line(4) { `val`; normal { " isMajor = index % " }; cyan { "5" }; normal { " == " }; cyan { "0" } }
    line(4) { normal { "drawLine(" } }
    line(5) { cyan { "color =" }; normal { " colors." }; blue { "run" }; normal { " { " }; `if`; normal { " (isMajor) " }; purple { "foreground " }; `else`; purple { " lightForeground" }; normal { " }," } }
    line(5) { cyan { "start =" }; normal { " start," } }
    line(5) { cyan { "end =" }; normal { " end," } }
    line(5) { cyan { "strokeWidth =" }; normal { " strokes." }; blue { "run" }; normal { " { " }; `if`; normal { " (isMajor) " }; purple { "thin " }; `else`; purple { " default" }; normal { " }," } }
    line(5) { cyan { "cap = " }; `if`; normal { " (isMajor) StrokeCap." }; purple { "Round " }; `else`; normal { " Stroke." }; purple { "DefaultCap" }; normal { "," } }
    line(4) { normal { ")" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line()
    line(1) { immutable }
    line(1) { orange { "data class" }; normal { " Colors(" } }
    line(2) { `val`; purple { " secondsHand" }; normal { ": Color," } }
    line(2) { `val`; purple { " background" }; normal { ": Color," } }
    line(2) { `val`; purple { " foreground" }; normal { ": Color," } }
    line(2) { `val`; purple { " lightForeground" }; normal { ": Color," } }
    line(1) { normal { ")" } }
    line()
    line(1) { immutable }
    line(1) { orange { "data class" }; normal { " Strokes(" } }
    line(2) { `val`; purple { " hour" }; normal { ": Float," } }
    line(2) { `val`; purple { " minute" }; normal { ": Float," } }
    line(2) { `val`; purple { " thin" }; normal { ": Float," } }
    line(2) { `val`; purple { " default" }; normal { ": Float = Stroke." }; purple { "HairlineWidth" }; normal { "," } }
    line(1) { normal { ")" } }
    line()
    line(1) { `object`; normal { " Defaults {" } }
    appendComposable(2)
    line(2) { `fun`; blue { " colors" }; normal { "(" } }
    line(3) { normal { "secondsHand: Color = Color(" }; cyan { "0xFFEA9436" }; normal { ")," } }
    line(3) { normal { "background: Color = Color(MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "surfaceVariant" }; normal { "," } }
    line(3) { normal { "foreground: Color = Color(MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "onBackground" }; normal { "," } }
    line(3) { normal { "lightForeground: Color = Color(MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "onSurfaceVariant" }; normal { "," } }
    line(2) { normal { ") = Colors(" } }
    line(3) { cyan { "secondsHand =" }; normal { " secondsHand," } }
    line(3) { cyan { "background =" }; normal { " background," } }
    line(3) { cyan { "foreground =" }; normal { " foreground," } }
    line(3) { cyan { "lightForeground =" }; normal { " lightForeground," } }
    line(2) { normal { ")" } }
    line()
    appendComposable(2)
    line(2) { `fun`; blue { " strokes" }; normal { "(" } }
    line(3) { normal { "hour: Dp = " }; cyan { "8" }; normal { "." }; purple { "dp" }; normal { "," } }
    line(3) { normal { "minute: Dp = " }; cyan { "4" }; normal { "." }; purple { "dp" }; normal { "," } }
    line(3) { normal { "thin: Dp = " }; cyan { "2" }; normal { "." }; purple { "dp" }; normal { "," } }
    line(2) { normal { ") = " }; purple { "LocalDensity" }; normal { "." }; purple { "current" }; normal { "." }; blue { "run" }; normal { " {" } }
    line(3) { normal { "Strokes(" } }
    line(4) { cyan { "hour = " }; normal { "hour." }; blue { "toPx" }; normal { "()," } }
    line(4) { cyan { "minute = " }; normal { "minute." }; blue { "toPx" }; normal { "()," } }
    line(4) { cyan { "thin = " }; normal { "thin." }; blue { "toPx" }; normal { "()," } }
    line(3) { normal { ")" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendRememberAnalogClockState() {
    line { orange { "private const val" }; purple { " A" }; normal { " = (" }; cyan { "2" }; normal { " * " }; purple { "PI" }; normal { ").toFloat()" } }
    line()
    line { orange { "private const val" }; purple { " B" }; normal { " = (-" }; purple { "PI" }; normal { " / " }; cyan { "2" }; normal { ").toFloat()" } }
    line()
    appendComposable()
    line { `fun`; blue { " rememberAnalogClockState" }; normal { "(time: LocalTime): State {" } }
    line(1) { `val`; normal { " (initialSecond, minute, hour) = remember(time) {" } }
    line(2) { `val`; normal { " secondRatio = time." }; purple { "second" }; normal { " / " }; cyan { "60f" } }
    line(2) { `val`; normal { " minuteRatio = (time." }; purple { "minute" }; normal { " + secondRatio) / " }; cyan { "60f" } }
    line(2) { `val`; normal { " hourRatio = (time." }; purple { "hour" }; normal { " + minuteRatio) / " }; cyan { "12f" } }
    line(2) { normal { "listOf(secondRatio, minuteRatio, hourRatio)." }; blue { "map" }; normal { " { it * " }; purple { "A" }; normal { " + " }; purple { "B" }; normal { " }" } }
    line(1) { normal { "}" } }
    line(1) { `val`; normal { " second " }; `by`; normal { " rememberInfiniteTransition()." }; blue { "animateFloat" }; normal { "(" } }
    line(2) { cyan { "initialValue =" }; normal { " initialSecond," } }
    line(2) { cyan { "targetValue =" }; normal { " initialSecond + " }; purple { "A" }; normal { "," } }
    line(2) { cyan { "animationSpec =" }; normal { " infiniteRepeatable(tween(" }; cyan { "60_000" }; normal { ", " }; cyan { "easing =" }; purple { " LinearEasing" }; normal { "))," } }
    line(1) { normal { ")" } }
    line(1) { `return`; normal { " remember(time, second) { State(hour, minute, second) }" } }
    line { normal { "}" } }
    line()
}
