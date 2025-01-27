package com.payam1991gr.kmp.playground.view.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.data.model.HomeItem
import com.payam1991gr.kmp.playground.view.ScreenDimension
import com.payam1991gr.kmp.playground.view.dimensions
import com.payam1991gr.kmp.playground.view.isLtr
import com.payam1991gr.kmp.playground.view.rememberScreenDimension
import com.payam1991gr.kmp.playground.view.screens.home.HomeScreen.State
import com.payam1991gr.kmp.playground.view.screens.home.HomeScreen.State.Event
import com.slack.circuit.runtime.ui.Ui
import kotlinx.collections.immutable.PersistentList
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import kotlin.math.sqrt

class Home : Ui<State> {
    enum class ScreenClass { A, B, C, D }

    @Composable
    override fun Content(state: State, modifier: Modifier) {
        var screenDimension by rememberScreenDimension()
        val screenClass = remember(screenDimension) { screenDimension.klass() }
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier.fillMaxSize().dimensions { screenDimension = it }
        ) {
            ItemList(
                largeItem = screenClass == ScreenClass.D,
                items = state.items,
                onClick = { state.event(Event.OnItemClicked(it)) },
                modifier = Modifier.run {
                    when (screenClass) {
                        ScreenClass.B, ScreenClass.D -> width(500.dp)
                        ScreenClass.C -> width(900.dp)
                        ScreenClass.A -> this
                    }
                }
            )
        }
    }

    private fun ScreenDimension.klass() = when {
        height < 400.dp -> ScreenClass.A
        width in 500.dp..700.dp -> ScreenClass.B
        height < 600.dp -> when {
            900.dp < width -> ScreenClass.C
            else -> ScreenClass.A
        }

        else -> when {
            500.dp < width -> ScreenClass.D
            else -> ScreenClass.A
        }
    }

    companion object {
        @Composable
        fun ItemList(
            largeItem: Boolean,
            items: PersistentList<HomeItem>,
            onClick: (HomeItem) -> Unit,
            modifier: Modifier = Modifier,
        ) {
            LazyVerticalGrid(
                GridCells.Adaptive(300.dp),
                contentPadding = PaddingValues(8.dp),
                modifier = modifier
            ) {
                items(items) { Item(largeItem, data = it, onClick = onClick) }
            }
        }

        @Composable
        fun Item(
            isLarge: Boolean,
            data: HomeItem,
            onClick: (HomeItem) -> Unit,
            modifier: Modifier = Modifier,
        ) {
            Card(
                modifier.padding(8.dp),
                shape = RoundedCornerShape(16.dp),
                elevation = 2.dp,
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onClick(data) }
                        .itemBackground(data.color),
                ) {
                    Row(
                        Modifier.padding(if (isLarge) 32.dp else 24.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            vectorResource(data.iconRes),
                            null,
                            Modifier.size(if (isLarge) 100.dp else 50.dp),
                            tint = data.color,
                        )
                        Text(
                            stringResource(data.labelRes),
                            color = data.color,
                            style = if (isLarge)
                                MaterialTheme.typography.headlineLarge
                            else
                                MaterialTheme.typography.titleLarge,
                        )
                    }
                }
            }
        }

        @Composable
        private fun Modifier.itemBackground(color: Color): Modifier {
            var containerSize by remember { mutableStateOf(IntSize.Zero) }
            val isLtr = isLtr()
            return background(
                Brush.linearGradient(
                    listOf(Color.White, color),
                    start = containerSize.run { Offset(-width * .5f, -height.toFloat()) },
                    end = containerSize.run { Offset(width * 1.5f, height * 3f) },
                )
            ).drawBehind {
                makeCircles(isLtr, size.width, size.height).forEach { circle ->
                    drawCircle(
                        color = color.copy(circle.alpha),
                        center = circle.origin,
                        radius = circle.radius,
                    )
                }
            }.onGloballyPositioned { containerSize = it.size }
        }

        private data class Circle(val origin: Offset, val radius: Float, val alpha: Float)

        private fun makeCircles(isLtr: Boolean, width: Float, height: Float) = buildList {
            val ar = width / height
            var alpha = .5f
            var x = (sqrt(ar * (ar + 4)) - ar) / 2
            var rx = 1 - x
            repeat(6) {
                add(Circle(Offset((if (isLtr) x else 1 - x) * width, height), rx * width, alpha))
                x -= rx
                rx = x * x / ar
                alpha *= .75f
            }
        }.asReversed()
    }
}
