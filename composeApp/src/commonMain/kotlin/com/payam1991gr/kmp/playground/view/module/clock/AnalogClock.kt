package com.payam1991gr.kmp.playground.view.module.clock

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.data.math.Mat
import com.payam1991gr.kmp.playground.data.math.Vec
import com.payam1991gr.kmp.playground.data.string.merge
import com.payam1991gr.kmp.playground.data.time.isNight
import com.payam1991gr.kmp.playground.data.time.timeFormat
import com.payam1991gr.kmp.playground.view.module.clock.AnalogClock.Colors
import com.payam1991gr.kmp.playground.view.module.clock.AnalogClock.Defaults
import com.payam1991gr.kmp.playground.view.module.clock.AnalogClock.Hands
import com.payam1991gr.kmp.playground.view.module.clock.AnalogClock.State
import com.payam1991gr.kmp.playground.view.module.clock.AnalogClock.Strokes
import com.payam1991gr.kmp.playground.view.module.clock.AnalogClock.Background
import com.payam1991gr.kmp.playground.view.theme.KmpPlaygroundTheme
import kotlinx.collections.immutable.toPersistentList
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalTime
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

typealias Offsets = Pair<Offset, Offset>

data class TimeZoneData(val name: String, val offset: String)

data class DateTimeData(
    val date: LocalDate,
    val time: LocalTime,
    val zone: TimeZoneData,
)

@Composable
fun AnalogClock(
    date: LocalDate,
    time: LocalTime,
    zone: TimeZoneData,
    modifier: Modifier = Modifier,
    tag: String = "",
) = KmpPlaygroundTheme(darkTheme = time.isNight()) {
    DateTimeData(date, time, zone).BaseAnalogClock(modifier, tag)
}

@Composable
fun DateTimeData.BaseAnalogClock(
    modifier: Modifier = Modifier,
    tag: String = "",
    strokes: Strokes = Defaults.strokes(),
    colors: Colors = Defaults.colors(),
) {
    val state = rememberAnalogClockState(time)
    Box(
        modifier
            .aspectRatio(1f)
            .testTag("AnalogClock" merge tag)
            .clearAndSetSemantics { stateDescription = timeFormat.format(time) }
    ) {
        Background(state, colors, strokes)
        Hands(state, colors, strokes)
    }
}

object AnalogClock {
    private const val HOUR_RADIUS = .5f
    private const val MINUTE_RADIUS = .93f
    private const val SECOND_LONG_RADIUS = .96f
    private const val SECOND_SHORT_RADIUS = .2f
    private const val TICK_START = .9f
    private const val TICK_END = .96f
    private const val NUMBER_POSITION = .84f

    private fun Vec.toOffset() = Offset(get(0), get(1))

    @Composable
    fun Background(state: State, colors: Colors, strokes: Strokes) = Background.apply {
        Box(Modifier
            .fillMaxSize()
            .drawWithCache {
                onDrawBehind {
                    drawCircle(colors.background)
                    drawTicks(state, colors, strokes)
                }
            }
        ) {
            Numbers(colors.foreground)
        }
    }

    @Composable
    fun Hands(state: State, colors: Colors, strokes: Strokes) = Hands.apply {
        Box(Modifier
            .fillMaxSize()
            .drawBehind {
                state.run { hands() }.apply {
                    drawHour(hour, colors.foreground, strokes.hour)
                    drawMinute(minute, colors.foreground, strokes.minute)
                    drawSecond(second, colors, strokes.thin)
                }
            }
        )
    }

    private fun DrawScope.radius() = size.width / 2f

    class State(
        private val hour: Float,
        private val minute: Float,
        private val second: Float,
    ) {
        fun DrawScope.hands(): Hands = radius().let { radius ->
            Hands(
                hour = center to center + hour.toOffset() * (HOUR_RADIUS * radius),
                minute = center to center + minute.toOffset() * (MINUTE_RADIUS * radius),
                second = second.toOffset().let { offset ->
                    center - offset * (SECOND_SHORT_RADIUS * radius) to
                            center + offset * (SECOND_LONG_RADIUS * radius)
                },
            )
        }

        fun DrawScope.tick(p1: Offset, p2: Offset): Offsets = radius().let { radius ->
            center + p1 * radius to center + p2 * radius
        }

        private fun Float.toOffset() = Offset(cos(this), sin(this))
    }

    data class Hands(val hour: Offsets, val minute: Offsets, val second: Offsets) {
        companion object {
            fun DrawScope.drawHour(data: Offsets, color: Color, stroke: Float) = data.apply {
                drawLine(color, first, second, stroke, StrokeCap.Round)
            }

            fun DrawScope.drawMinute(data: Offsets, color: Color, stroke: Float) = data.apply {
                drawLine(color, first, second, stroke, StrokeCap.Round)
            }

            fun DrawScope.drawSecond(data: Offsets, colors: Colors, stroke: Float) = colors.apply {
                val radius = radius()
                drawCircle(foreground, radius = radius / 20f)
                drawCircle(secondsHand, radius = radius / 27f)
                data.apply {
                    drawLine(secondsHand, first, second, stroke, StrokeCap.Round)
                }
                drawCircle(background, radius = radius / 50f)
            }
        }
    }

    object Background {
        private val rotation30deg by lazy { Mat.rotationOf((PI / 6.0).toFloat()) }
        private val rotation6deg by lazy { Mat.rotationOf((PI / 30.0).toFloat()) }

        private val tickPositions by lazy {
            mutableListOf<Offsets>().apply {
                var start = Vec.tor(0f, -TICK_START)
                var end = Vec.tor(0f, -TICK_END)
                add(start.toOffset() to end.toOffset())
                repeat(59) {
                    start = (rotation6deg * start).flatten()
                    end = (rotation6deg * end).flatten()
                    add(start.toOffset() to end.toOffset())
                }
            }
        }

        private val numberPositions by lazy {
            mutableListOf<Vec>().apply {
                var position = Vec.tor(0f, -NUMBER_POSITION)
                repeat(12) {
                    position = (rotation30deg * position).flatten()
                    add(position)
                }
            }.toPersistentList()
        }

        @Composable
        fun BoxScope.Numbers(color: Color) = numberPositions.forEachIndexed { index, position ->
            Text(
                (index + 1).toString(),
                color = color,
                modifier = Modifier.align(BiasAlignment(position[0], position[1]))
            )
        }

        fun DrawScope.drawTicks(
            state: State,
            colors: Colors,
            strokes: Strokes,
        ) = tickPositions.forEachIndexed { index, (p1, p2) ->
            val (start, end) = state.run { tick(p1, p2) }
            val isMajor = index % 5 == 0
            drawLine(
                color = colors.run { if (isMajor) foreground else lightForeground },
                start = start,
                end = end,
                strokeWidth = strokes.run { if (isMajor) thin else default },
                cap = if (isMajor) StrokeCap.Round else Stroke.DefaultCap,
            )
        }
    }

    @Immutable
    data class Colors(
        val secondsHand: Color,
        val background: Color,
        val foreground: Color,
        val lightForeground: Color,
    )

    @Immutable
    data class Strokes(
        val hour: Float,
        val minute: Float,
        val thin: Float,
        val default: Float = Stroke.HairlineWidth,
    )

    object Defaults {
        @Composable
        fun colors(
            secondsHand: Color = Color(0xFFEA9436),
            background: Color = MaterialTheme.colorScheme.surfaceVariant,
            foreground: Color = MaterialTheme.colorScheme.onBackground,
            lightForeground: Color = MaterialTheme.colorScheme.onSurfaceVariant,
        ) = Colors(
            secondsHand = secondsHand,
            background = background,
            foreground = foreground,
            lightForeground = lightForeground,
        )

        @Composable
        fun strokes(
            hour: Dp = 4.dp,
            minute: Dp = 2.dp,
            thin: Dp = 1.dp,
        ) = LocalDensity.current.run {
            Strokes(
                hour = hour.toPx(),
                minute = minute.toPx(),
                thin = thin.toPx(),
            )
        }
    }
}

private const val A = (2 * PI).toFloat()
private const val B = (-PI / 2).toFloat()

@Composable
fun rememberAnalogClockState(time: LocalTime): State {
    val (initialSecond, minute, hour) = remember(time) {
        val secondRatio = time.second / 60f
        val minuteRatio = (time.minute + secondRatio) / 60f
        val hourRatio = (time.hour + minuteRatio) / 12f // PM hours simply add 360° to the AM angle
        listOf(secondRatio, minuteRatio, hourRatio).map { it * A + B }
    }
    val second by rememberInfiniteTransition().animateFloat(
        initialValue = initialSecond,
        targetValue = initialSecond + A,
        animationSpec = infiniteRepeatable(tween(60_000, easing = LinearEasing)),
    )
    return remember(time, second) { State(hour, minute, second) }
}
