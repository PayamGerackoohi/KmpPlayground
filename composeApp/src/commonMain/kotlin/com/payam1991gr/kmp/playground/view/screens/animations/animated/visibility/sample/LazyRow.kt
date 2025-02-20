package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module.content
import com.payam1991gr.kmp.playground.view.module.editor.CodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.sample.*
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

@Composable
fun LazyRowSample() {
    var visible by rememberBoolean()
    val model = remember { MyModel() }
    val state by derivedStateOf {
        model.items.map { it.itemId }.joinToString(", ", prefix = "[", postfix = "]")
    }
    val hasContent by derivedStateOf { model.items.isNotEmpty() }
    val cornerRadius by animateDpAsState(if (hasContent) 0.dp else 32.dp)
    val tag = stringResource(Res.string.animations_animated_visibility_lazy_row)
    Module(tag, { visible = !visible }) {
        AnimatedVisibility(
            visible = visible,
            enter = slideInVertically() + fadeIn(),
            exit = fadeOut() + slideOutVertically(),
            modifier = Modifier
                .clipToBounds()
                .content(tag)
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { model.addNewItem() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                            contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                        ),
                        shape = RoundedCornerShape(bottomStart = cornerRadius),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(Res.string.add))
                    }
                    Button(
                        onClick = { model.removeAll() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                            contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                        ),
                        shape = RoundedCornerShape(bottomEnd = cornerRadius),
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(stringResource(Res.string.clear_all))
                    }
                }
                LaunchedEffect(model) {
                    snapshotFlow {
                        model.items.firstOrNull { it.visible.run { isIdle && !targetState } }
                    }.collect {
                        if (it != null) model.pruneItems()
                    }
                }
                LazyRow(
                    Modifier
                        .padding(top = 1.dp)
                        .testTag("$tag.List")
                        .semantics { stateDescription = "State: $state" }
                ) {
                    items(model.items, key = { it.itemId }) { item ->
                        AnimatedVisibility(
                            visibleState = item.visible,
                            enter = expandHorizontally(),
                            exit = shrinkHorizontally(),
                        ) {
                            IconButton(
                                { model.removeItem(item) },
                                colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = MaterialTheme.colorScheme.error,
                                    contentColor = MaterialTheme.colorScheme.onError,
                                ),
                                modifier = Modifier.background(item.color)
                            ) {
                                Icon(
                                    Icons.Default.Delete,
                                    stringResource(
                                        Res.string.animations_animated_visibility_lazy_row_remove,
                                        item.itemId,
                                    ),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

private val turquoiseColors = listOf(
    Color(0xff07688C),
    Color(0xff1986AF),
    Color(0xff50B6CD),
    Color(0xffBCF8FF),
    Color(0xff8AEAE9),
    Color(0xff46CECA)
)

private class MyModel {
    private val _items: MutableList<ColoredItem> = mutableStateListOf()
    private var lastItemId = 0
    val items: List<ColoredItem> = _items

    inner class ColoredItem(
        val visible: MutableTransitionState<Boolean>,
        val itemId: Int,
    ) {
        val color: Color get() = turquoiseColors.run { this[itemId % size] }
    }

    fun addNewItem() {
        lastItemId++
        _items.add(
            ColoredItem(
                MutableTransitionState(false).apply { targetState = true },
                lastItemId,
            )
        )
    }

    fun removeItem(item: ColoredItem) {
        item.visible.targetState = false
    }

    fun pruneItems() {
        _items.removeAll(
            items.filter {
                it.visible.run { isIdle && !targetState }
            }
        )
    }

    fun removeAll() {
        _items.forEach {
            it.visible.targetState = false
        }
    }
}

fun CodeEditor.appendLazyRowSample() {
    appendComposable()
    line { `fun`; blue { "LazyRowSample" }; normal { "() {" } }
    line(1) { `var`; normal { " visible " }; `by`; normal { " rememberBoolean()" } }
    line(1) { `val`; normal { " model = remember { MyModel() }" } }
    line(1) { `val`; normal { " state " }; `by`; normal { " derivedStateOf {" } }
    line(2) { normal { "model." }; purple { "items" }; normal { "." }; blue { "map" }; normal { " { it." }; purple { "itemId" }; normal { " }." }; blue { "joinToString" }; normal { "(" }; green { "\", \"" }; normal { ", " }; cyan { "prefix = " }; green { "\"[\"" }; normal { ", " }; cyan { "postfix = " }; green { "\"]\"" }; normal { ")" } }
    line(1) { normal { "}" } }
    line(1) { `val`; normal { " hasContent " }; `by`; normal { " derivedStateOf { model." }; purple { "items" }; normal { "." }; blue { "isNotEmpty" }; normal { "() }" } }
    line(1) { `val`; normal { " cornerRadius " }; `by`; normal { " animateDpAsState(" }; `if`; normal { " (hasContent) " }; cyan { "0" }; normal { "." }; purple { "dp " }; `else`; cyan { " 32" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(1) { `val`; normal { " tag = stringResource(Res.string." }; purple { "animations_animated_visibility_lazy_row" }; normal { ")" } }
    line(1) { normal { "Module(tag, { visible = !visible }) {" } }
    line(2) { normal { "AnimatedVisibility(" } }
    line(3) { cyan { "visible = " }; normal { "visible," } }
    line(3) { cyan { "enter = " }; normal { "slideInVertically() + fadeIn()," } }
    line(3) { cyan { "exit = " }; normal { "fadeOut() + slideOutVertically()," } }
    line(3) { cyan { "modifier = " }; normal { "Modifier" } }
    line(4) { normal { "." }; blue { "clipToBounds" }; normal { "()" } }
    line(4) { normal { "." }; blue { "testTag" }; normal { "(" }; green { "\"" }; orange { "\$" }; normal { "tag" }; green { ".Content\"" }; normal { ")" } }
    line(2) { normal { ") {" } }
    line(3) { normal { "Column {" } }
    line(4) { normal { "Row(" } }
    line(5) { cyan { "horizontalArrangement = " }; normal { "Arrangement.spacedBy(" }; cyan { "4" }; normal { "." }; purple { "dp" }; normal { ")," } }
    line(5) { cyan { "modifier = " }; normal { "Modifier." }; blue { "fillMaxWidth" }; normal { "()" } }
    line(4) { normal { ") {" } }
    line(5) { normal { "Button(" } }
    line(6) { cyan { "onClick = " }; normal { "{ model.addNewItem() }," } }
    line(6) { cyan { "colors = " }; normal { "ButtonDefaults.buttonColors(" } }
    line(7) { cyan { "containerColor = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "secondaryContainer" }; normal { "," } }
    line(7) { cyan { "contentColor = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "onSecondaryContainer" }; normal { "," } }
    line(6) { normal { ")," } }
    line(6) { cyan { "shape = " }; normal { "RoundedCornerShape(" }; cyan { "bottomStart = " }; normal { "cornerRadius)," } }
    line(6) { cyan { "modifier = " }; normal { "Modifier." }; blue { "weight" }; normal { "(" }; cyan { "1f" }; normal { ")" } }
    line(5) { normal { ") {" } }
    line(6) { normal { "Text(stringResource(Res.string." }; purple { "add" }; normal { "))" } }
    line(5) { normal { "}" } }
    line(5) { normal { "Button(" } }
    line(6) { cyan { "onClick =" }; normal { " { model.removeAll() }," } }
    line(6) { cyan { "colors = " }; normal { "ButtonDefaults.buttonColors(" } }
    line(7) { cyan { "containerColor = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "tertiaryContainer" }; normal { "," } }
    line(7) { cyan { "contentColor = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "onTertiaryContainer" }; normal { "," } }
    line(6) { normal { ")," } }
    line(6) { cyan { "shape = " }; normal { "RoundedCornerShape(" }; cyan { "bottomEnd =" }; normal { " cornerRadius)," } }
    line(6) { cyan { "modifier = " }; normal { "Modifier." }; blue { "weight" }; normal { "(" }; cyan { "1f" }; normal { ")" } }
    line(5) { normal { ") {" } }
    line(6) { normal { "Text(stringResource(Res.string." }; purple { "clear_all" }; normal { "))" } }
    line(5) { normal { "}" } }
    line(4) { normal { "}" } }
    line(4) { normal { "LaunchedEffect(model) {" } }
    line(5) { normal { "snapshotFlow {" } }
    line(6) { normal { "model." }; purple { "items" }; normal { "." }; blue { "firstOrNull" }; normal { " { it." }; purple { "visible" }; normal { "." }; blue { "run" }; normal { " { " }; purple { "isIdle" }; normal { " && !" }; purple { "targetState" }; normal { " } }" } }
    line(5) { normal { "}.collect {" } }
    line(6) { `if`; normal { " (it != " }; `null`; normal { ") model.pruneItems()" } }
    line(5) { normal { "}" } }
    line(4) { normal { "}" } }
    line(4) { normal { "LazyRow" } }
    line(5) { normal { "Modifier" } }
    line(6) { normal { "." }; blue { "padding" }; normal { "(" }; cyan { "top = 1" }; normal { "." }; purple { "dp" }; normal { ")" } }
    line(6) { normal { "." }; blue { "testTag" }; normal { "(" }; green { "\"" }; orange { "\$" }; normal { "tag" }; green { ".List\"" }; normal { ")" } }
    line(6) { normal { "." }; blue { "semantics" }; normal { " { " }; purple { "stateDescription" }; normal { " = " }; green { "\"State: " }; orange { "\$" }; normal { "state" }; green { "\"" }; normal { " }" } }
    line(4) { normal { ") {" } }
    line(5) { blue { "items" }; normal { "(model." }; purple { "items" }; normal { ", " }; cyan { "key =" }; normal { " { it." }; purple { "itemId" }; normal { " }) { item ->" } }
    line(6) { blue { "AnimatedVisibility" }; normal { "(" } }
    line(7) { cyan { "visibleState =" }; normal { " item." }; purple { "visible" }; normal { "," } }
    line(7) { cyan { "enter = " }; normal { "expandHorizontally()," } }
    line(7) { cyan { "exit = " }; normal { "shrinkHorizontally()," } }
    line(6) { normal { ") {" } }
    line(7) { normal { "IconButton(" } }
    line(8) { normal { "{ model.removeItem(item) }," } }
    line(8) { cyan { "colors = " }; normal { "IconButtonDefaults.iconButtonColors(" } }
    line(9) { cyan { "containerColor = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "error" }; normal { "," } }
    line(9) { cyan { "contentColor = " }; normal { "MaterialTheme." }; purple { "colorScheme" }; normal { "." }; purple { "onError" }; normal { "," } }
    line(8) { normal { ")," } }
    line(8) { cyan { "modifier = " }; normal { "Modifier." }; blue { "background" }; normal { "(item." }; purple { "color" }; normal { ")" } }
    line(7) { normal { ") {" } }
    line(8) { normal { "Icon(" } }
    line(9) { normal { "Icons." }; purple { "Default" }; normal { "." }; purple { "Delete" }; normal { "," } }
    line(9) { normal { "stringResource(" } }
    line(10) { normal { "Res.string." }; purple { "animations_animated_visibility_lazy_row_remove" }; normal { "," } }
    line(10) { normal { "item." }; purple { "itemId" }; normal { "," } }
    line(9) { normal { ")," } }
    line(8) { normal { ")" } }
    line(7) { normal { "}" } }
    line(6) { normal { "}" } }
    line(5) { normal { "}" } }
    line(4) { normal { "}" } }
    line(3) { normal { "}" } }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
    appendTurquoiseColors()
    appendMyModel()
}

@Suppress("SpellCheckingInspection")
private fun CodeEditor.appendTurquoiseColors() {
    line { orange { "private val " }; purple { "turquoiseColors" }; normal { " = listOf(" } }
    line(1) { normal { "Color(" }; cyan { "0xff07688C" }; normal { ")," } }
    line(1) { normal { "Color(" }; cyan { "0xff1986AF" }; normal { ")," } }
    line(1) { normal { "Color(" }; cyan { "0xff50B6CD" }; normal { ")," } }
    line(1) { normal { "Color(" }; cyan { "0xffBCF8FF" }; normal { ")," } }
    line(1) { normal { "Color(" }; cyan { "0xff8AEAE9" }; normal { ")," } }
    line(1) { normal { "Color(" }; cyan { "0xff46CECA" }; normal { ")," } }
    line { normal { ")" } }
    line()
}

private fun CodeEditor.appendMyModel() {
    line { orange { "private class" }; normal { " MyModel {" } }
    line(1) { orange { "private val " }; purple { "_items" }; normal { ": MutableList<ColoredItem> = mutableStateListOf()" } }
    line(1) { orange { "private var " }; purple { "lastItemId" }; normal { " = " }; cyan { "0" } }
    line(1) { `val`; purple { " items" }; normal { ": List<ColoredItem> = " }; purple { "_items" } }
    line()
    line(1) { orange { "inner class" }; normal { " ColoredItem(" } }
    line(2) { `val`; purple { " visible" }; normal { ": MutableTransitionState<Boolean>," } }
    line(2) { `val`; purple { " itemId" }; normal { ": Int," } }
    line(1) { normal { ") {" } }
    line(2) { `val`; purple { " color" }; normal { ": Color " }; `get`; normal { "() = " }; purple { "turquoiseColors" }; normal { "." }; blue { "run" }; normal { " { " }; `this`; normal { "[" }; purple { "itemId" }; normal { " % " }; purple { "size" }; normal { "] }" } }
    line(1) { normal { "}" } }
    line()
    line(1) { `fun`; blue { " addNewItem" }; normal { "() {" } }
    line(2) { purple { "lastItemId" }; normal { "++" } }
    line(2) { purple { "_items" }; normal { ".add(" } }
    line(3) { normal { "ColoredItem(" } }
    line(4) { normal { "MutableTransitionState(" }; `false`; normal { ")." }; blue { "apply" }; normal { " { " }; purple { "targetState" }; normal { " = " }; `true`; normal { " }," } }
    line(4) { purple { "lastItemId" }; normal { "," } }
    line(3) { normal { ")" } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line()
    line(1) { `fun`; blue { " removeItem" }; normal { "(item: ColoredItem) {" } }
    line(2) { normal { "item." }; purple { "visible" }; normal { "." }; purple { "targetState" }; normal { " = " }; `false` }
    line(1) { normal { "}" } }
    line()
    line(1) { `fun`; blue { " pruneItems" }; normal { "() {" } }
    line(2) { purple { "_items" }; normal { ".removeAll(" } }
    line(3) { purple { "items" }; normal { "." }; blue { "filter" }; normal { " {" } }
    line(4) { normal { "it." }; purple { "visible" }; normal { "." }; blue { "run" }; normal { " { " }; purple { "isIdle" }; normal { " && !" }; purple { "targetState" }; normal { " }" } }
    line(3) { normal { "}" } }
    line(2) { normal { ")" } }
    line(1) { normal { "}" } }
    line()
    line(1) { `fun`; blue { " removeAll" }; normal { "() {" } }
    line(2) { purple { "_items" }; normal { "." }; blue { "forEach" }; normal { " {" } }
    line(3) { normal { "it." }; purple { "visible" }; normal { "." }; purple { "targetState" }; normal { " = " }; `false` }
    line(2) { normal { "}" } }
    line(1) { normal { "}" } }
    line { normal { "}" } }
    line()
}
