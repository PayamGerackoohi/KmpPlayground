@file:Suppress("FunctionName")

package com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.sample

import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.sample.appendComposable
import com.payam1991gr.kmp.playground.view.sample.appendExperimentalMaterial3Api

fun CodeEditor.appendState_PtrState_PullToRefresh() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { orange { "private fun " }; normal { "State.PtrState." }; blue { "PullToRefresh" }; normal { "(" } }
    line(1) { normal { "title: String," } }
    line(1) { normal { "modifier: Modifier = Modifier," } }
    line(1) { normal { "content: " }; normal { "@Composable " }; normal { "(PaddingValues, " }; yellow { "@Composable " }; normal { "() -> Unit) -> Unit," } }
    line { normal { ") = Scaffold(" } }
    line(1) { cyan { "modifier = " }; normal { "modifier." }; blue { "module" }; normal { "(title)," } }
    line(1) { cyan { "topBar = " }; normal { "{" } }
    line(2) { normal { "TopAppBar(" } }
    line(3) { cyan { "title = " }; normal { "{ Text(title) }," } }
    line(3) { cyan { "actions = " }; normal { "{ " }; gray { "// Provide an accessible alternative to trigger refresh." } }
    line(4) { normal { "IconButton(" }; cyan { "onClick = " }; normal { "::onRefresh) {" } }
    line(5) { normal { "Icon(" } }
    line(6) { normal { "Icons.Filled." }; purple { "Refresh" }; normal { "," } }
    line(6) { normal { "stringResource(Res.string." }; purple { "trigger_refresh" }; normal { ")," } }
    line(5) { normal { ")" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { ")" } }
    line(1) { normal { "}," } }
    line { normal { ") {" } }
    line(1) { normal { "content(it) {" } }
    line(2) { normal { "LazyColumn(Modifier." }; blue { "moduleList" }; normal { "(title)) {" } }
    line(3) { normal { "items(" }; purple { "itemCount" }; normal { ") {" } }
    line(4) { normal { "ListItem({" } }
    line(5) { normal { "Text(" } }
    line(6) { normal { "stringResource(" } }
    line(7) { normal { "Res.string." }; purple { "components_pull_to_refresh_item_template" }; normal { "," } }
    line(7) { purple { "itemCount " }; normal { "- it" } }
    line(6) { normal { ")" } }
    line(5) { normal { ")" } }
    line(4) { normal { "})" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendState_PtrState_LinearProgressIndicatorSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { orange { "private fun " }; normal { "State.PtrState." }; blue { "LinearProgressIndicatorSample" }; normal { "() = " }; blue { "PullToRefresh" }; normal { "(" } }
    line(1) { cyan { "title = " }; normal { "stringResource(Res.string." }; purple { "components_pull_to_refresh_linear_progress_indicator" }; normal { ")," } }
    line(1) { cyan { "modifier = " }; normal { "Modifier." }; blue { "pullToRefresh" }; normal { "(" } }
    line(2) { cyan { "state = " }; purple { "state" }; normal { "," } }
    line(2) { cyan { "isRefreshing = " }; purple { "isRefreshing" }; normal { "," } }
    line(2) { cyan { "onRefresh = " }; normal { "::onRefresh" }; normal { "," } }
    line(1) { normal { ")" } }
    line { normal { ") { padding, content ->" } }
    line(1) { normal { "Box(Modifier." }; blue { "padding" }; normal { "(padding)) {" } }
    line(2) { normal { "content()" } }
    line(2) { orange { "val " }; normal { "modifier = Modifier." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(2) { orange { "if " }; normal { "(" }; purple { "isRefreshing" }; normal { ") LinearProgressIndicator(modifier)" } }
    line(2) { orange { "else " }; normal { "LinearProgressIndicator({ " }; purple { "state" }; normal { "." }; purple { "distanceFraction " }; normal { "}, modifier)" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendState_PtrState_PullToRefreshBoxSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { orange { "private fun " }; normal { "State.PtrState." }; blue { "PullToRefreshBoxSample" }; normal { "() = " }; blue { "PullToRefresh" }; normal { "(" } }
    line(1) { cyan { "title = " }; normal { "stringResource(Res.string." }; purple { "components_pull_to_refresh_box" }; normal { ")," } }
    line { normal { ") { padding, content ->" } }
    line(1) { normal { "PullToRefreshBox(" } }
    line(2) { cyan { "state = " }; purple { "state" }; normal { "," } }
    line(2) { cyan { "isRefreshing = " }; purple { "isRefreshing" }; normal { "," } }
    line(2) { cyan { "onRefresh = " }; normal { "::onRefresh" }; normal { "," } }
    line(2) { cyan { "modifier = " }; normal { "Modifier." }; blue { "padding" }; normal { "(padding)," } }
    line(1) { normal { ") { content() }" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendState_PtrState_ScalingSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { orange { "private fun " }; normal { "State.PtrState." }; blue { "ScalingSample" }; normal { "() {" } }
    line(1) { orange { "val " }; normal { "scaleFraction = {" } }
    line(2) { orange { "if " }; normal { "(" }; purple { "isRefreshing" }; normal { ") " }; cyan { "1f" } }
    line(2) { orange { "else " }; purple { "LinearOutSlowInEasing" }; normal { ".transform(" }; purple { "state" }; normal { "." }; purple { "distanceFraction" }; normal { ")." }; blue { "coerceIn" }; normal { "(" }; cyan { "0f" }; normal { ", " }; cyan { "1f" }; normal { ")" } }
    line(1) { normal { "}" } }
    line(1) { blue { "PullToRefresh" }; normal { "(" } }
    line(2) { cyan { "title = " }; normal { "stringResource(Res.string." }; purple { "components_pull_to_refresh_scaling" }; normal { ")," } }
    line(2) { cyan { "modifier = " }; normal { "Modifier." }; blue { "pullToRefresh" }; normal { "(" } }
    line(3) { cyan { "state = " }; purple { "state" }; normal { "," } }
    line(3) { cyan { "isRefreshing = " }; purple { "isRefreshing" }; normal { "," } }
    line(3) { cyan { "onRefresh = " }; normal { "::onRefresh" }; normal { "," } }
    line(2) { normal { ")," } }
    line(1) { normal { ") { padding, content ->" } }
    line(2) { normal { "Box(Modifier." }; blue { "padding" }; normal { "(padding)) {" } }
    line(3) { normal { "content()" } }
    line(3) { normal { "Box(" } }
    line(4) { normal { "Modifier" } }
    line(5) { normal { "." }; blue { "align" }; normal { "(Alignment." }; purple { "TopCenter" }; normal { ")" } }
    line(5) { normal { "." }; blue { "graphicsLayer " }; normal { "{" } }
    line(6) { purple { "scaleX " }; normal { "= scaleFraction()" } }
    line(6) { purple { "scaleY " }; normal { "= scaleFraction()" } }
    line(5) { normal { "}" } }
    line(3) { normal { ") {" } }
    line(4) { normal { "PullToRefreshDefaults.Indicator(" } }
    line(5) { cyan { "state = " }; purple { "state" }; normal { "," } }
    line(5) { cyan { "isRefreshing = " }; purple { "isRefreshing" }; normal { "," } }
    line(4) { normal { ")" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendState_PtrState_CustomBehaviorSample() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { orange { "private fun " }; normal { "State.PtrState." }; blue { "CustomBehaviorSample" }; normal { "() {" } }
    line(1) { orange { "val " }; normal { "state = remember {" } }
    line(2) { orange { "object " }; normal { ": PullToRefreshState {" } }
    line(3) { orange { "private val " }; purple { "anim " }; normal { "= Animatable(" }; cyan { "0f" }; normal { ", Float." }; purple { "VectorConverter" }; normal { ")" } }
    line(3) { orange { "override val " }; purple { "distanceFraction " }; orange { "get" }; normal { "() = " }; purple { "anim" }; normal { "." }; purple { "value" } }
    line(3) { orange { "override suspend fun " }; blue { "snapTo" }; normal { "(targetValue: Float) = " }; purple { "anim" }; normal { ".snapTo(targetValue)" } }
    line()
    line(3) { orange { "override suspend fun " }; blue { "animateToThreshold" }; normal { "() {" } }
    line(4) { purple { "anim" }; normal { ".animateTo(" }; cyan { "1f" }; normal { ", spring(" }; cyan { "dampingRatio = " }; normal { "Spring." }; purple { "DampingRatioHighBouncy" }; normal { "))" } }
    line(3) { normal { "}" } }
    line()
    line(3) { orange { "override suspend fun " }; blue { "animateToHidden" }; normal { "() {" } }
    line(4) { purple { "anim" }; normal { ".animateTo(" }; cyan { "0f" }; normal { ")" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line(1) { blue { "PullToRefresh" }; normal { "(" } }
    line(2) { cyan { "title = " }; normal { "stringResource(Res.string." }; purple { "components_pull_to_refresh_custom_behavior" }; normal { ")," } }
    line(1) { normal { ") { padding, content ->" } }
    line(2) { normal { "PullToRefreshBox(" } }
    line(3) { cyan { "modifier = " }; normal { "Modifier." }; blue { "padding" }; normal { "(padding)," } }
    line(3) { cyan { "isRefreshing = " }; purple { "isRefreshing" }; normal { "," } }
    line(3) { cyan { "onRefresh = " }; normal { "::onRefresh" }; normal { "," } }
    line(3) { cyan { "state = " }; normal { "state" }; normal { "," } }
    line(2) { normal { ") { content() }" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}

fun CodeEditor.appendModifier_module() {
    line { orange { "private fun " }; normal { "Modifier." }; blue { "module" }; normal { "(tag: String) = " }; orange { "this" } }
    line(1) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "horizontal = 16" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(1) { normal { "." }; blue { "height" }; normal { "(" }; cyan { "250" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(1) { normal { "." }; blue { "border" }; normal { "(" }; cyan { "2" }; normal { "." }; purple { "dp" }; normal { ", Color." }; purple { "Red" }; normal { ")" } }
    line(1) { normal { "." }; blue { "testTag" }; normal { "(" }; green { "\"Module." }; orange { "\$" }; normal { "tag" }; green { "\"" }; normal { ")" } }
    line()
}

fun CodeEditor.appendModifier_moduleList() {
    line { orange { "private fun " }; normal { "Modifier." }; blue { "moduleList" }; normal { "(tag: String) = " }; orange { "this" } }
    line(1) { normal { "." }; blue { "fillMaxSize" }; normal { "()" } }
    line(1) { normal { "." }; blue { "testTag" }; normal { "(" }; green { "\"Module.List." }; orange { "\$" }; normal { "tag" }; green { "\"" }; normal { ")" } }
    line()
}

fun CodeEditor.appendRememberPtrState() {
    appendExperimentalMaterial3Api()
    appendComposable()
    line { orange { "fun " }; blue { "rememberPtrState" }; normal { "(" } }
    line(1) { normal { "initialItemCount: Int = " }; cyan { "5" }; normal { "," } }
    line(1) { normal { "state: PullToRefreshState = rememberPullToRefreshState()," } }
    line(1) { normal { "coroutineScope: CoroutineScope = rememberCoroutineScope()," } }
    line { normal { "): PtrState {" } }
    line(1) { orange { "val " }; normal { "isRefreshing = remember { mutableStateOf(" }; orange { "false" }; normal { ") }" } }
    line(1) { orange { "val " }; normal { "itemCount = remember { mutableIntStateOf(initialItemCount) }" } }
    line(1) { orange { "return " }; normal { "remember { PtrStateImpl(state, isRefreshing, itemCount, coroutineScope) }" } }
    line { normal { "}" } }
    line()
}
