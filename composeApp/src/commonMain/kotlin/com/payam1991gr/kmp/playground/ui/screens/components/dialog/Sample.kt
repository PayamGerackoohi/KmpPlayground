package com.payam1991gr.kmp.playground.ui.screens.components.dialog

import com.payam1991gr.kmp.playground.ui.module.editor.CodeEditor

fun CodeEditor.appendBasicAlertDialogSample() {
    line { yellow { "@OptIn" }; normal { "(" }; yellow { "ExperimentalMaterial3Api" }; normal { "::" }; orange { "class" }; normal { ")" } }
    line { yellow { "@Composable" } }
    line { orange { "fun " }; blue { "BasicAlertDialogSample" }; normal { "() = Column(" } }
    line(1) { teal { "horizontalAlignment = " }; normal { "Alignment." }; purple { "CenterHorizontally" }; normal { "," } }
    line(1) { teal { "modifier = " }; normal { "Modifier." }; blue { "fillMaxWidth" }; normal { "()," } }
    line { normal { ") {" } }
    line(1) { orange { "val " }; normal { "animationDuration = " }; orange { "if " }; normal { "(" }; purple { "LocalTestMode.current" }; normal { ") " }; teal { "0 " }; orange { "else " }; teal { "300" } }
    line(1) { orange { "var " }; normal { "showDialog " }; orange { "by " }; normal { "remember { mutableStateOf(" }; orange { "false" }; normal { ") }" } }
    line(1) { orange { "var " }; normal { "animateDialog " }; orange { "by " }; normal { "remember { mutableStateOf(" }; orange { "false" }; normal { ") }" } }
    line(1) { orange { "val " }; normal { "animationSpec = tween<Float>(animationDuration)" } }
    line(1) { orange { "val " }; normal { "alpha " }; orange { "by " }; normal { "animateFloatAsState(" } }
    line(2) { orange { "if " }; normal { "(animateDialog) " }; teal { "1f " }; orange { "else " }; teal { "0f" }; normal { "," } }
    line(2) { teal { "animationSpec = " }; normal { "animationSpec," } }
    line(1) { normal { ")" } }
    line(1) { orange { "val " }; normal { "scope = rememberCoroutineScope()" } }
    line(1) { orange { "val " }; normal { "isDismissible " }; orange { "by " }; normal { "rememberSettingItem(" }; orange { "true" }; normal { ") {" } }
    line(2) { orange { "if" }; normal { "(it) Res.string." }; purple { "dismissible " }; orange { "else " }; normal { "Res.string." }; purple { "mandatory" } }
    line(1) { normal { "}" } }
    line(1) { orange { "val " }; normal { "shouldAnimate " }; orange { "by " }; normal { "rememberSettingItem(" }; orange { "true" }; normal { ") {" } }
    line(2) { orange { "if " }; normal { "(it) Res.string." }; purple { "animate " }; orange { "else " }; normal { "Res.string." }; purple { "instant" } }
    line(1) { normal { "}" } }
    line(1) { orange { "fun " }; blue { "onClose" }; normal { "(): Any = " }; orange { "if " }; normal { "(shouldAnimate." }; purple { "value" }; normal { ") scope." }; teal { "launch " }; normal { "{" } }
    line(2) { normal { "animateDialog = " }; orange { "false" } }
    line(2) { normal { "delay(animationDuration.toLong())" } }
    line(2) { normal { "showDialog = " }; orange { "false" } }
    line(1) { normal { "} " }; orange { "else " }; normal { "showDialog = " }; orange { "false" } }
    line()
    line(1) { normal { "Settings(isDismissible, shouldAnimate)" } }
    line(1) { normal { "Button(" } }
    line(2) { teal { "onClick =" }; normal { "{" } }
    line(3) { normal { "showDialog = " }; orange { "true" } }
    line(3) { orange { "if " }; normal { "(shouldAnimate." }; purple { "value" }; normal { ") scope." }; teal { "launch " }; normal { "{" } }
    line(4) { normal { "delay(animationDuration.toLong())" } }
    line(4) { normal { "animateDialog = " }; orange { "true" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}," } }
    line(2) { teal { "modifier = " }; normal { "Modifier." }; blue { "testTag" }; normal { "(" }; green { "\"BasicAlertDialog.Show\"" }; normal { ")" } }
    line(1) { normal { ") { Text(" }; green { "\"Show\"" }; normal { ") }" } }
    line(1) { orange { "if " }; normal { "(showDialog) BasicAlertDialog(" } }
    line(2) { teal { "onDismissRequest = " }; normal { "{ " }; orange { "if " }; normal { "(isDismissible." }; purple { "value" }; normal { ") onClose() }," } }
    line(1) { normal { ") {" } }
    line(2) { normal { "Surface(" } }
    line(3) { teal { "shape = " }; normal { "MaterialTheme." }; purple { "shapes" }; normal { "." }; purple { "large" }; normal { "," } }
    line(3) { teal { "tonalElevation = " }; normal { "AlertDialogDefaults." }; purple { "TonalElevation" }; normal { "," } }
    line(3) { teal { "modifier = " }; normal { "Modifier" } }
    line(4) { normal { "." }; blue { "wrapContentWidth" }; normal { "()" } }
    line(4) { normal { "." }; blue { "wrapContentHeight" }; normal { "()." }; blue { "run " }; normal { "{" } }
    line(5) { orange { "if " }; normal { "(shouldAnimate." }; purple { "value" }; normal { ") " }; blue { "alpha" }; normal { "(alpha)" } }
    line(5) { orange { "else this" } }
    line(4) { normal { "}" } }
    line(4) { normal { "." }; teal { "testTag" }; normal { "(" }; green { "\"BasicAlertDialogSample\"" }; normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Column(" }; teal { "modifier = " }; normal { "Modifier." }; blue { "padding" }; normal { "(" }; teal { "16" }; normal { "." }; purple { "dp" }; normal { ")) {" } }
    line(4) { normal { "Text(" } }
    line(5) { teal { "text = " }; green { "\"This area typically contains the supportive text \" " }; normal { "+" } }
    line(6) { green { "\"which presents the details regarding the Dialog's purpose.\"" } }
    line(4) { normal { ")" } }
    line(4) { normal { "Spacer(" }; teal { "modifier = " }; normal { "Modifier." }; blue { "height" }; normal { "(" }; teal { "24" }; normal { "." }; purple { "dp" }; normal { "))" } }
    line(4) { normal { "TextButton(" } }
    line(5) { teal { "onClick = " }; normal { "{ onClose() }," } }
    line(5) { teal { "modifier = " }; normal { "Modifier." }; blue { "align" }; normal { "(Alignment." }; purple { "End" }; normal { ")," } }
    line(4) { normal { ") { Text(" }; green { "\"Confirm\"" }; normal { ") }" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
}
