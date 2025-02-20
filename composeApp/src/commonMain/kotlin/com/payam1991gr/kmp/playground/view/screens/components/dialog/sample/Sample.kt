package com.payam1991gr.kmp.playground.view.screens.components.dialog.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendResources() {
    line { orange { "private val " }; purple { "timestampStringRes " }; normal { "= Res.string." }; purple { "components_date_picker_timestamp_template" } }
    line { orange { "private val " }; purple { "dateStringRes " }; normal { "= Res.string." }; purple { "components_date_picker_date_template" } }
    line()
}

fun CodeEditor.appendBasicAlertDialogSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { `fun`; blue { " BasicAlertDialogSample" }; normal { "() = ContentList {" } }
    line(1) { `val`; normal { " animationDuration = " }; `if`; normal { " (" }; purple { "LocalTestMode.current" }; normal { ") " }; cyan { "0 " }; `else`; cyan { " 300" } }
    line(1) { `var`; normal { " showDialog " }; `by`; normal { " remember { mutableStateOf(" }; `false`; normal { ") }" } }
    line(1) { `var`; normal { " animateDialog " }; `by`; normal { " remember { mutableStateOf(" }; `false`; normal { ") }" } }
    line(1) { `val`; normal { " animationSpec = tween<Float>(animationDuration)" } }
    line(1) { `val`; normal { " alpha " }; `by`; normal { " animateFloatAsState(" } }
    line(2) { `if`; normal { " (animateDialog) " }; cyan { "1f " }; `else`; cyan { " 0f" }; normal { "," } }
    line(2) { cyan { "animationSpec = " }; normal { "animationSpec," } }
    line(1) { normal { ")" } }
    line(1) { `val`; normal { " scope = rememberCoroutineScope()" } }
    line(1) { `val`; normal { " shouldAnimate = rememberSetting {" } }
    line(2) { `if`; normal { " (it) Res.string." }; purple { "animate " }; `else`; normal { " Res.string." }; purple { "instant" } }
    line(1) { normal { "}" } }
    line(1) { `val`; normal { " isDismissible = rememberSetting {" } }
    line(2) { `if`; normal { "(it) Res.string." }; purple { "dismissible " }; `else`; normal { " Res.string." }; purple { "mandatory" } }
    line(1) { normal { "}" } }
    line(1) { normal { "Settings(isDismissible, shouldAnimate)" } }
    line()
    line(1) { `fun`; blue { " onClose" }; normal { "(): Any = " }; `if`; normal { " (shouldAnimate." }; purple { "value" }; normal { ") scope." }; cyan { "launch " }; normal { "{" } }
    line(2) { normal { "animateDialog = " }; `false` }
    line(2) { normal { "delay(animationDuration.toLong())" } }
    line(2) { normal { "showDialog = " }; `false` }
    line(1) { normal { "} " }; `else`; normal { " showDialog = " }; `false` }
    line()
    line(1) { normal { "Button(" } }
    line(2) { cyan { "onClick = " }; normal { "{" } }
    line(3) { normal { "showDialog = " }; `true` }
    line(3) { `if`; normal { " (shouldAnimate." }; purple { "value" }; normal { ") scope." }; cyan { "launch " }; normal { "{" } }
    line(4) { normal { "delay(animationDuration.toLong())" } }
    line(4) { normal { "animateDialog = " }; `true` }
    line(3) { normal { "}" } }
    line(2) { normal { "}," } }
    line(2) { cyan { "modifier = " }; normal { "Modifier." }; blue { "testTag" }; normal { "(" }; green { "\"BasicAlertDialog.Show\"" }; normal { ")" } }
    line(1) { normal { ") { Text(stringResource(Res.string." }; purple { "show" }; normal { ")) }" } }
    line(1) { `if`; normal { " (showDialog) BasicAlertDialog(" } }
    line(2) { cyan { "onDismissRequest = " }; normal { "{ " }; `if`; normal { " (isDismissible." }; purple { "value" }; normal { ") onClose() }," } }
    line(1) { normal { ") {" } }
    line(2) { normal { "Surface(" } }
    line(3) { cyan { "shape = " }; normal { "MaterialTheme." }; purple { "shapes" }; normal { "." }; purple { "large" }; normal { "," } }
    line(3) { cyan { "tonalElevation = " }; normal { "AlertDialogDefaults." }; purple { "TonalElevation" }; normal { "," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier" } }
    line(4) { normal { "." }; blue { "wrapContentWidth" }; normal { "()" } }
    line(4) { normal { "." }; blue { "wrapContentHeight" }; normal { "()." }; blue { "run " }; normal { "{ " }; `if`; normal { " (shouldAnimate." }; purple { "value" }; normal { ") " }; blue { "alpha" }; normal { "(alpha) " }; orange { "else this " }; normal { "}" } }
    line(4) { normal { "." }; cyan { "testTag" }; normal { "(" }; green { "\"BasicAlertDialogSample\"" }; normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Column(" }; cyan { "modifier = " }; normal { "Modifier." }; blue { "padding" }; normal { "(" }; cyan { "16" }; normal { "." }; purple { "dp" }; normal { ")) {" } }
    line(4) { normal { "Text(" } }
    line(5) { cyan { "text = " }; green { "\"This area typically contains the supportive text \" " }; normal { "+" } }
    line(6) { green { "\"which presents the details regarding the Dialog's purpose.\"" } }
    line(4) { normal { ")" } }
    line(4) { normal { "Spacer(" }; cyan { "modifier = " }; normal { "Modifier." }; blue { "height" }; normal { "(" }; cyan { "24" }; normal { "." }; purple { "dp" }; normal { "))" } }
    line(4) { normal { "TextButton(" } }
    line(5) { cyan { "onClick = " }; normal { "{ onClose() }," } }
    line(5) { cyan { "modifier = " }; normal { "Modifier." }; blue { "align" }; normal { "(Alignment." }; purple { "End" }; normal { ")," } }
    line(4) { normal { ") { Text(stringResource(Res.string." }; purple { "confirm" }; normal { ")) }" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendDatePickerDialogSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { `fun`; blue { " DatePickerDialogSample" }; normal { "(state: DatePicker) = ContentList {" } }
    line(1) { `var`; normal { " showDialog " }; `by`; normal { " remember { mutableStateOf(" }; `false`; normal { ") }" } }
    line(1) { normal { "state." }; blue { "run " }; normal { "{" } }
    line(2) { normal { "rememberDatePickerState(" } }
    line(3) { cyan { "initialDisplayedMonthMillis = " }; purple { "initialDisplayDate" }; normal { "," } }
    line(3) { cyan { "initialSelectedDateMillis = " }; purple { "date" }; normal { "." }; purple { "ms" }; normal { "," } }
    line(2) { normal { ")" } }
    line(1) { normal { "}." }; blue { "apply " }; normal { "{" } }
    line(2) { normal { "state." }; purple { "date" }; normal { "." }; blue { "apply " }; normal { "{" } }
    line(3) { normal { "Text(stringResource(" }; purple { "timestampStringRes" }; normal { ", " }; purple { "ms" }; normal { "." }; blue { "box" }; normal { "()))" } }
    line(3) { normal { "Text(stringResource(" }; purple { "dateStringRes" }; normal { ", " }; purple { "text" }; normal { "." }; blue { "box" }; normal { "()))" } }
    line(2) { normal { "}" } }
    line(2) { normal { "Button(" } }
    line(3) { cyan { "onClick = " }; normal { "{ showDialog = " }; `true`; normal { " }," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier." }; blue { "testTag" }; normal { "(" }; green { "\"DatePickerDialog.Show\"" }; normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Text(stringResource(Res.string." }; purple { "show" }; normal { "))" } }
    line(2) { normal { "}" } }
    line(2) { `if`; normal { " (showDialog) {" } }
    line(3) { `val`; normal { " confirmEnabled = remember { derivedStateOf { " }; purple { "hasDate " }; normal { "} }" } }
    line(3) { normal { "DatePickerDialog(" } }
    line(4) { cyan { "onDismissRequest = " }; normal { "{ showDialog = " }; `false`; normal { " }," } }
    line(4) { cyan { "confirmButton = " }; normal { "{" } }
    line(5) { normal { "TextButton(" } }
    line(6) { cyan { "onClick = " }; normal { "{" } }
    line(7) { normal { "showDialog = " }; `false` }
    line(7) { purple { "selectedDateMillis" }; normal { "?." }; blue { "let " }; normal { "{ ms -> state." }; purple { "date" };normal { "." }; purple { "ms " }; normal { "= ms }" } }
    line(6) { normal { "}," } }
    line(6) { cyan { "enabled = " }; normal { "confirmEnabled." }; purple { "value" }; normal { "," } }
    line(5) { normal { ") { Text(stringResource(Res.string." }; purple { "ok" }; normal { ")) }" } }
    line(4) { normal { "}," } }
    line(4) { cyan { "dismissButton = " }; normal { "{" } }
    line(5) { normal { "TextButton({ showDialog = " }; `false`; normal { " }) {" } }
    line(6) { normal { "Text(stringResource(Res.string." }; purple { "cancel" }; normal { "))" } }
    line(5) { normal { "}" } }
    line(4) { normal { "}," } }
    line(3) { normal { ") {" } }
    line(4) { normal { "DatePicker(" }; `this`; teal { "@apply" }; normal { ")" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendHasDate() {
    appendExperimentalMaterial3Api()
    line { orange { "private val " }; normal { "DatePickerState." }; purple { "hasDate " }; `get`; normal { "() = " }; purple { "selectedDateMillis " }; normal { "!= " }; `null` }
    line()
}

fun CodeEditor.appendTimePickerDialogSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { `fun`; blue { " TimePickerDialogSample" }; normal { "() = ContentList {" } }
    line(1) { `var`; normal { " time " }; `by`; normal { " remember { mutableStateOf(Time()) }" } }
    line(1) { `var`; normal { " showTimePicker " }; `by`; normal { " remember { mutableStateOf(" }; `false`; normal { ") }" } }
    line()
    line(1) { normal { "rememberTimePickerState(" } }
    line(2) { cyan { "initialHour = " }; normal { "time." }; purple { "hour" }; normal { "," } }
    line(2) { cyan { "initialMinute = " }; normal { "time." }; purple { "minute" }; normal { "," } }
    line(1) { normal { ")." }; blue { "apply " }; normal { "{" } }
    line(1) { normal { "Text(" } }
    line(2) { cyan { "text = " }; normal { "time." }; blue { "format" }; normal { "()," } }
    line(2) { cyan { "modifier = " }; normal { "Modifier." }; blue { "testTag" }; normal { "(" }; green { "\"TimePicker.Text\"" }; normal { ")" } }
    line(1) { normal { ")" } }
    line(2) { normal { "Button(" } }
    line(3) { cyan { "onClick = " }; normal { "{ showTimePicker = " }; `true`; normal { " }," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier." }; blue { "testTag" }; normal { "(" }; green { "\"TimePicker.SetTime\"" }; normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Text(stringResource(Res.string." }; purple { "time_picker_dialog_set_time" }; normal { "))" } }
    line(2) { normal { "}" } }
    line()
    line(2) { `if`; normal { " (showTimePicker) TimePickerDialog(" } }
    line(3) { cyan { "title = " }; normal { "stringResource(Res.string." }; purple { "time_picker_dialog_select_time" }; normal { ")," } }
    line(3) { cyan { "onCancel = " }; normal { "{ showTimePicker = " }; `false`; normal { " }," } }
    line(3) { cyan { "onConfirm = " }; normal { "{" } }
    line(4) { normal { "time = " }; blue { "time" }; normal { "()" } }
    line(4) { normal { "showTimePicker = " }; `false` }
    line(3) { normal { "}," } }
    line(2) { normal { ") { TimePicker(" }; `this`; normal { ") }" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendTimePickerDialog() {
    appendComposable()
    line { `fun`; blue { " TimePickerDialog" }; normal { "(" } }
    line(1) { normal { "title: String," } }
    line(1) { normal { "onCancel: () -> Unit," } }
    line(1) { normal { "onConfirm: () -> Unit," } }
    line(1) { normal { "content: " }; yellow { "@Composable " }; normal { "() -> Unit," } }
    line { normal { ") {" } }
    line(1) { normal { "Dialog(" } }
    line(2) { cyan { "onDismissRequest = " }; normal { "onCancel," } }
    line(2) { cyan { "properties = " }; normal { "DialogProperties(" }; cyan { "usePlatformDefaultWidth = " }; `false`; normal { ")," } }
    line(1) { normal { ") {" } }
    line(2) { normal { "Surface(" } }
    line(3) { cyan { "shape = " }; normal { "MaterialTheme." }; purple { "shapes" }; normal { "." }; purple { "extraLarge" }; normal { "," } }
    line(3) { cyan { "tonalElevation = 8" }; normal { "." }; purple { "dp" }; normal { "," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier" } }
    line(4) { normal { "." }; blue { "width" }; normal { "(IntrinsicSize." }; purple { "Min" }; normal { ")" } }
    line(4) { normal { "." }; blue { "height" }; normal { "(IntrinsicSize." }; purple { "Min" }; normal { ")" } }
    line(4) { normal { "." }; blue { "background" }; normal { "(" } }
    line(5) { cyan { "shape = " }; normal { "MaterialTheme." }; purple { "shapes" }; normal { "." }; purple { "extraLarge" }; normal { "," } }
    line(5) { cyan { "color = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "surface" }; normal { "," } }
    line(4) { normal { ")," } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Column(" } }
    line(4) { normal { "modifier = " }; normal { "Modifier." }; blue { "padding" }; normal { "(" }; cyan { "24" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(4) { cyan { "horizontalAlignment = " }; normal { "Alignment." }; purple { "CenterHorizontally" }; normal { "," } }
    line(3) { normal { ") {" } }
    line(4) { normal { "Text(" } }
    line(5) { cyan { "text = " }; normal { "title," } }
    line(5) { cyan { "style = " }; normal { "MaterialTheme." }; purple { "typography" }; normal { "." }; purple { "labelMedium" }; normal { "," } }
    line(5) { cyan { "modifier = " }; normal { "Modifier" } }
    line(6) { normal { "." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(6) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "bottom = 16" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { ")" } }
    line(4) { normal { "content()" } }
    line(4) { normal { "Row(Modifier." }; blue { "fillMaxWidth" }; normal { "()) {" } }
    line(5) { normal { "Spacer(Modifier." }; blue { "weight" }; normal { "(" }; cyan { "1f" }; normal { "))" } }
    line(5) { normal { "TextButton(onCancel) { Text(stringResource(Res.string." }; purple { "cancel" }; normal { ")) }" } }
    line(5) { normal { "TextButton(onConfirm) { Text(stringResource(Res.string." }; purple { "ok" }; normal { ")) }" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
