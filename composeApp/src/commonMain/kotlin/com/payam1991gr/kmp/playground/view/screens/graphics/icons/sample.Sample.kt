package com.payam1991gr.kmp.playground.view.screens.graphics.icons

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.*

fun CodeEditor.appendPreview() {
    appendComposable()
    line { `fun`; blue { " Preview" }; normal { "(state: State) = state." }; blue { "apply" }; normal { " {" } }
    line(1) { `val`; normal { " spacing = " }; cyan { "2" }; normal { "." }; purple { "dp" } }
    line(1) { `val`; normal { " snackbarState = remember { SnackbarHostState() }" } }
    line(1) { `val`; normal { " scope = rememberCoroutineScope()" } }
    line(1) { `fun`; blue { " showMessage" }; normal { "(text: String) = scope." }; blue { "launch" }; normal { " {" } }
    line(2) { normal { "withTimeout(" }; cyan { "1000" }; normal { ") {" } }
    line(3) { normal { "snackbarState.showSnackbar(text)" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line(2) { normal { "state." }; blue { "ObserveEffects" }; normal { "(::showMessage)" } }
    line(2) { normal { "Scaffold(" } }
    line(3) { cyan { "snackbarHost =" }; normal { " { SnackbarHost(snackbarState, Modifier." }; blue { "testTag" }; normal { "(" }; green { "\"Snackbar\"" }; normal { ")) }," } }
    line(2) { normal { ") {" } }
    line(3) { normal { "LazyVerticalGrid(" } }
    line(4) { cyan { "columns = " }; normal { "GridCells.Adaptive(" }; cyan { "40" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(4) { cyan { "horizontalArrangement = " }; normal { "Arrangement.spacedBy(spacing)," } }
    line(4) { cyan { "verticalArrangement = " }; normal { "Arrangement.spacedBy(spacing)," } }
    line(4) { cyan { "contentPadding = " }; normal { "PaddingValues(" }; cyan { "16" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(4) { cyan { "modifier = " }; normal { "Modifier." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(3) { normal { ") {" } }
    line(4) { blue { "header" }; normal { "(Res.string." }; purple { "graphic_icons_core" }; normal { ")" } }
    line(4) { blue { "icons" }; normal { "(" }; purple { "coreIcons" }; normal { ", " }; purple { "graphic_icons_core" }; normal { ")" } }
    line(4) { blue { "header" }; normal { "(" } }
    line(5) { normal { "Res.string." }; purple { "graphic_icons_extended" }; normal { "," } }
    line(5) { normal { "Modifier." }; blue { "padding" }; normal { "(" }; cyan { "top = 16" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(4) { normal { ")" } }
    line(4) { `when`; normal { " (" }; purple { "extendedIcons" }; normal { ") {" } }
    line(5) { normal { "LazyData.Loading -> " }; blue { "loading" }; normal { "()" } }
    line(5) { `is`; normal { " LazyData.Date -> " }; blue { "icons" }; normal { "(" }; purple { "extendedIcons" }; normal { "." }; purple { "data" }; normal { ", " }; purple { "event" }; normal { ")" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendState() {
    appendComposable()
    line { orange { "data class" }; normal { " State(" } }
    line(1) { `val`; purple { " coreIcons" }; normal { ": PersistentList<IconData>," } }
    line(1) { `val`; purple { " extendedIcons" }; normal { ": LazyData<PersistentList<IconData>>," } }
    line(1) { `val`; purple { " effect" }; normal { ": MutableState<Effect?>," } }
    line(1) { `val`; purple { " event" }; normal { ": (Event) -> Unit," } }
    line { normal { ") : CircuitUiState {" } }
    line(1) { orange { "sealed interface" }; normal { " Event : CircuitUiEvent {" } }
    line(2) { orange { "data class" }; normal { " OnIconClicked(" }; `val`; purple { " icon" }; normal { ": IconData) : Event" } }
    line(1) { normal { "}" } }
    line()
    line(1) { orange { "sealed interface" }; normal { " Effect {" } }
    line(2) { orange { "data class" }; normal { " ShowMessage(" }; `val`; purple { " text" }; normal { ": String) : Effect" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendLazyData() {
    line { orange { "sealed interface" }; normal { " LazyData<" }; `out`; normal { " T> {" } }
    line(1) { orange { "data class" }; normal { " Data<" }; teal { "T" }; normal { ">(" }; `val`; purple { " data" }; normal { ": " }; teal { "T" }; normal { ") : LazyData<" }; teal { "T" }; normal { ">" } }
    line(1) { orange { "data object" }; normal { " Loading : LazyData<Nothing>" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendIconData() {
    line { orange { "data class" }; normal { " IconData(" }; `val`; purple { " title" }; normal { ": String, " }; `val`; purple { " icon" }; normal { ": ImageVector)" } }
    line()
}

@Suppress("FunctionName")
fun CodeEditor.appendLazyGridScope_header() {
    line { orange { "private fun" }; normal { " LazyGridScope." }; blue { "header" }; normal { "(" } }
    line(1) { normal { "titleRes: StringResource," } }
    line(1) { normal { "modifier: Modifier = Modifier," } }
    line { normal { ") = item(" }; cyan { "span =" }; normal { " { GridItemSpan(" }; purple { "maxLineSpan" }; normal { ") }) {" } }
    line(1) { normal { "Header(stringResource(titleRes), modifier)" } }
    line { normal { "}" } }
    line()
}

@Suppress("FunctionName")
fun CodeEditor.appendLazyGridScope_icons() {
    line { orange { "private fun" }; normal { " LazyGridScope." }; blue { "icons" }; normal { "(" } }
    line(1) { normal { "icons: PersistentList<IconData>," } }
    line(1) { normal { "event: (Event) -> Unit," } }
    line { normal { ") = " }; blue { "items" }; normal { "(icons) {" } }
    line(1) { normal { "Icon(it) { event(Event.OnIconClicked(it)) }" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendIcon() {
    appendComposable()
    line { `fun`; blue { " Icon" }; normal { "(data: IconData, onClick: () -> Unit) = Icon(" } }
    line(1) { cyan { "imageVector =" }; normal { " data." }; purple { "icon" }; normal { "," } }
    line(1) { cyan { "contentDescription =" }; normal { " data." }; purple { "title" }; normal { "," } }
    line(1) { cyan { "tint =" }; normal { " MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "onSecondaryContainer" }; normal { "," } }
    line(1) { cyan { "modifier =" }; normal { " Modifier" } }
    line(2) { normal { "." }; blue { "fillMaxSize" }; normal { "()" } }
    line(2) { normal { "." }; blue { "aspectRatio" }; normal { "(" }; cyan { "1f" }; normal { ")" } }
    line(2) { normal { "." }; blue { "background" }; normal { "(MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "secondaryContainer" }; normal { ")" } }
    line(2) { normal { "." }; blue { "clickable" }; normal { " { onClick() }" } }
    line(2) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "4" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line { normal { ")" } }
    line()
}

fun CodeEditor.appendLoading() {
    line { orange { "private fun" }; normal { " LazyGridScope." }; blue { "loading" }; normal { "() = item(" }; cyan { "span =" }; normal { " { GridItemSpan(" }; purple { "maxLineSpan" }; normal { ") }) {" } }
    line(1) { normal { "LinearProgressIndicator(" } }
    line(2) { cyan { "color =" }; normal { " MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "secondary" }; normal { "," } }
    line(2) { cyan { "trackColor =" }; normal { " MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "tertiary" }; normal { "," } }
    line(1) { normal { ")" } }
    line { normal { "}" } }
    line()
}

@Suppress("FunctionName")
fun CodeEditor.appendState_ObserveEffects() {
    appendComposable()
    line { orange { "private fun" }; normal { " State." }; blue { "ObserveEffects" }; normal { "(showMessage: (String) -> Unit) {" } }
    line(1) { `when`; normal { " (" }; `val`; normal { " effect = " }; purple { "effect" }; normal { "." }; purple { "value" }; normal { ") {" } }
    line(2) { `null`; normal { " -> nothing()" } }
    line(2) { `is`; normal { " Effect.ShowMessage -> showMessage(effect." }; purple { "text" }; normal { ")" } }
    line(1) { normal { "}" } }
    line(1) { purple { "effect" }; normal { "." }; purple { "value" }; normal { " = " }; `null` }
    line { normal { "}" } }
    line()
}
