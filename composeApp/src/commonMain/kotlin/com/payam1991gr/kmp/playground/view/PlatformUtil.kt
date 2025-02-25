package com.payam1991gr.kmp.playground.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.min

@Composable
fun Modifier.mirrorLtr() = graphicsLayer(rotationY = if (isLtr()) 180f else 0f)

@Composable
fun Modifier.mirrorRtl() = graphicsLayer(rotationY = if (isLtr()) 0f else 180f)

fun <T> effectOf() = mutableStateOf<T?>(null)

@Composable
fun <T> rememberEffectOf() = remember { effectOf<T>() }

@Composable
fun isLtr() = LocalLayoutDirection.current == LayoutDirection.Ltr

fun IntSize.minDimension() = min(width, height)

@Stable
@Composable
fun Modifier.minDimension(onResult: (Dp) -> Unit) = run {
    var sizePx by remember { mutableStateOf(0) }
    val sizeDp = LocalDensity.current.run { sizePx.toDp() }
    remember(sizeDp) { onResult(sizeDp) }
    onGloballyPositioned { sizePx = it.size.minDimension() }
}

@Stable
data class ScreenDimension(val width: Dp = 1.dp, val height: Dp = 1.dp)

@Composable
fun rememberScreenDimension(): MutableState<ScreenDimension> = remember {
    mutableStateOf(ScreenDimension())
}

@Stable
@Composable
fun Modifier.dimensions(onResult: (ScreenDimension) -> Unit) = run {
    var sizePx by remember { mutableStateOf(IntSize.Zero) }
    val (width, height) = LocalDensity.current.run { sizePx.run { width.toDp() to height.toDp() } }
    remember(width, height) { onResult(ScreenDimension(width, height)) }
    onGloballyPositioned { sizePx = it.size }
}

@Composable
fun rememberBoolean(init: Boolean = true) = remember { mutableStateOf(init) }

fun nothing() {}
