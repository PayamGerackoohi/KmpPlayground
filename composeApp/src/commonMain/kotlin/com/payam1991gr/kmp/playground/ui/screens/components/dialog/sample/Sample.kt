package com.payam1991gr.kmp.playground.ui.screens.components.dialog.sample

import com.payam1991gr.kmp.playground.ui.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.ui.sample.appendComposable
import com.payam1991gr.kmp.playground.ui.sample.appendExperimentalMaterial3Api

fun CodeEditor.appendResources() {
    line { orange { "private val " }; purple { "timestampStringRes " }; normal { "= Res.string." }; purple { "components_date_picker_timestamp_template" } }
    line { orange { "private val " }; purple { "dateStringRes " }; normal { "= Res.string." }; purple { "components_date_picker_date_template" } }
    line()
}

fun CodeEditor.appendBasicAlertDialogSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { orange { "fun " }; blue { "BasicAlertDialogSample" }; normal { "() = ContentList {" } }
    line(1) { orange { "val " }; normal { "animationDuration = " }; orange { "if " }; normal { "(" }; purple { "LocalTestMode.current" }; normal { ") " }; cyan { "0 " }; orange { "else " }; cyan { "300" } }
    line(1) { orange { "var " }; normal { "showDialog " }; orange { "by " }; normal { "remember { mutableStateOf(" }; orange { "false" }; normal { ") }" } }
    line(1) { orange { "var " }; normal { "animateDialog " }; orange { "by " }; normal { "remember { mutableStateOf(" }; orange { "false" }; normal { ") }" } }
    line(1) { orange { "val " }; normal { "animationSpec = tween<Float>(animationDuration)" } }
    line(1) { orange { "val " }; normal { "alpha " }; orange { "by " }; normal { "animateFloatAsState(" } }
    line(2) { orange { "if " }; normal { "(animateDialog) " }; cyan { "1f " }; orange { "else " }; cyan { "0f" }; normal { "," } }
    line(2) { cyan { "animationSpec = " }; normal { "animationSpec," } }
    line(1) { normal { ")" } }
    line(1) { orange { "val " }; normal { "scope = rememberCoroutineScope()" } }
    line(1) { orange { "val " }; normal { "shouldAnimate = rememberSetting {" } }
    line(2) { orange { "if " }; normal { "(it) Res.string." }; purple { "animate " }; orange { "else " }; normal { "Res.string." }; purple { "instant" } }
    line(1) { normal { "}" } }
    line(1) { orange { "val " }; normal { "isDismissible = rememberSetting {" } }
    line(2) { orange { "if" }; normal { "(it) Res.string." }; purple { "dismissible " }; orange { "else " }; normal { "Res.string." }; purple { "mandatory" } }
    line(1) { normal { "}" } }
    line(1) { normal { "Settings(isDismissible, shouldAnimate)" } }
    line()
    line(1) { orange { "fun " }; blue { "onClose" }; normal { "(): Any = " }; orange { "if " }; normal { "(shouldAnimate." }; purple { "value" }; normal { ") scope." }; cyan { "launch " }; normal { "{" } }
    line(2) { normal { "animateDialog = " }; orange { "false" } }
    line(2) { normal { "delay(animationDuration.toLong())" } }
    line(2) { normal { "showDialog = " }; orange { "false" } }
    line(1) { normal { "} " }; orange { "else " }; normal { "showDialog = " }; orange { "false" } }
    line()
    line(1) { normal { "Button(" } }
    line(2) { cyan { "onClick = " }; normal { "{" } }
    line(3) { normal { "showDialog = " }; orange { "true" } }
    line(3) { orange { "if " }; normal { "(shouldAnimate." }; purple { "value" }; normal { ") scope." }; cyan { "launch " }; normal { "{" } }
    line(4) { normal { "delay(animationDuration.toLong())" } }
    line(4) { normal { "animateDialog = " }; orange { "true" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}," } }
    line(2) { cyan { "modifier = " }; normal { "Modifier." }; blue { "testTag" }; normal { "(" }; green { "\"BasicAlertDialog.Show\"" }; normal { ")" } }
    line(1) { normal { ") { Text(stringResource(Res.string." }; purple { "show" }; normal { ")) }" } }
    line(1) { orange { "if " }; normal { "(showDialog) BasicAlertDialog(" } }
    line(2) { cyan { "onDismissRequest = " }; normal { "{ " }; orange { "if " }; normal { "(isDismissible." }; purple { "value" }; normal { ") onClose() }," } }
    line(1) { normal { ") {" } }
    line(2) { normal { "Surface(" } }
    line(3) { cyan { "shape = " }; normal { "MaterialTheme." }; purple { "shapes" }; normal { "." }; purple { "large" }; normal { "," } }
    line(3) { cyan { "tonalElevation = " }; normal { "AlertDialogDefaults." }; purple { "TonalElevation" }; normal { "," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier" } }
    line(4) { normal { "." }; blue { "wrapContentWidth" }; normal { "()" } }
    line(4) { normal { "." }; blue { "wrapContentHeight" }; normal { "()." }; blue { "run " }; normal { "{ " }; orange { "if " }; normal { "(shouldAnimate." }; purple { "value" }; normal { ") " }; blue { "alpha" }; normal { "(alpha) " }; orange { "else this " }; normal { "}" } }
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
    line { orange { "fun " }; blue { "DatePickerDialogSample" }; normal { "(state: DatePicker) = ContentList {" } }
    line(1) { orange { "var " }; normal { "showDialog " }; orange { "by " }; normal { "remember { mutableStateOf(" }; orange { "false" }; normal { ") }" } }
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
    line(3) { cyan { "onClick = " }; normal { "{ showDialog = " }; orange { "true " }; normal { "}," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier." }; blue { "testTag" }; normal { "(" }; green { "\"DatePickerDialog.Show\"" }; normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Text(stringResource(Res.string." }; purple { "show" }; normal { "))" } }
    line(2) { normal { "}" } }
    line(2) { orange { "if " }; normal { "(showDialog) {" } }
    line(3) { orange { "val " }; normal { "confirmEnabled = remember { derivedStateOf { " }; purple { "hasDate " }; normal { "} }" } }
    line(3) { normal { "DatePickerDialog(" } }
    line(4) { cyan { "onDismissRequest = " }; normal { "{ showDialog = " }; orange { "false " }; normal { "}," } }
    line(4) { cyan { "confirmButton = " }; normal { "{" } }
    line(5) { normal { "TextButton(" } }
    line(6) { cyan { "onClick = " }; normal { "{" } }
    line(7) { normal { "showDialog = " }; orange { "false" } }
    line(7) { purple { "selectedDateMillis" }; normal { "?." }; blue { "let " }; normal { "{ ms -> state." }; purple { "date" };normal { "." }; purple { "ms " }; normal { "= ms }" } }
    line(6) { normal { "}," } }
    line(6) { cyan { "enabled = " }; normal { "confirmEnabled." }; purple { "value" }; normal { "," } }
    line(5) { normal { ") { Text(stringResource(Res.string." }; purple { "ok" }; normal { ")) }" } }
    line(4) { normal { "}," } }
    line(4) { cyan { "dismissButton = " }; normal { "{" } }
    line(5) { normal { "TextButton({ showDialog = " }; orange { "false " }; normal { "}) {" } }
    line(6) { normal { "Text(stringResource(Res.string." }; purple { "cancel" }; normal { "))" } }
    line(5) { normal { "}" } }
    line(4) { normal { "}," } }
    line(3) { normal { ") {" } }
    line(4) { normal { "DatePicker(" }; orange { "this" }; teal { "@apply" }; normal { ")" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendHasDate() {
    appendExperimentalMaterial3Api()
    line { orange { "private val " }; normal { "DatePickerState." }; purple { "hasDate " }; orange { "get" }; normal { "() = " }; purple { "selectedDateMillis " }; normal { "!= " }; orange { "null" } }
    line()
}

fun CodeEditor.appendTimePickerDialogSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { orange { "fun " }; blue { "TimePickerDialogSample" }; normal { "() = ContentList {" } }
    line(1) { orange { "var " }; normal { "time " }; orange { "by " }; normal { "remember { mutableStateOf(Time()) }" } }
    line(1) { orange { "var " }; normal { "showTimePicker " }; orange { "by " }; normal { "remember { mutableStateOf(" }; orange { "false" }; normal { ") }" } }
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
    line(3) { cyan { "onClick = " }; normal { "{ showTimePicker = " }; orange { "true " }; normal { "}," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier." }; blue { "testTag" }; normal { "(" }; green { "\"TimePicker.SetTime\"" }; normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Text(stringResource(Res.string." }; purple { "time_picker_dialog_set_time" }; normal { "))" } }
    line(2) { normal { "}" } }
    line()
    line(2) { orange { "if " }; normal { "(showTimePicker) TimePickerDialog(" } }
    line(3) { cyan { "title = " }; normal { "stringResource(Res.string." }; purple { "time_picker_dialog_select_time" }; normal { ")," } }
    line(3) { cyan { "onCancel = " }; normal { "{ showTimePicker = " }; orange { "false " }; normal { "}," } }
    line(3) { cyan { "onConfirm = " }; normal { "{" } }
    line(4) { normal { "time = " }; blue { "time" }; normal { "()" } }
    line(4) { normal { "showTimePicker = " }; orange { "false" } }
    line(3) { normal { "}," } }
    line(2) { normal { ") { TimePicker(" }; orange { "this" }; normal { ") }" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendTimePickerDialog() {
    appendComposable()
    line { orange { "fun " }; blue { "TimePickerDialog" }; normal { "(" } }
    line(1) { normal { "title: String," } }
    line(1) { normal { "onCancel: () -> Unit," } }
    line(1) { normal { "onConfirm: () -> Unit," } }
    line(1) { normal { "content: " }; yellow { "@Composable " }; normal { "() -> Unit," } }
    line { normal { ") {" } }
    line(1) { normal { "Dialog(" } }
    line(2) { cyan { "onDismissRequest = " }; normal { "onCancel," } }
    line(2) { cyan { "properties = " }; normal { "DialogProperties(" }; cyan { "usePlatformDefaultWidth = " }; orange { "false" }; normal { ")," } }
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
