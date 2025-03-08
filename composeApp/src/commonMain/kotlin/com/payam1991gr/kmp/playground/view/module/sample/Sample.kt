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
