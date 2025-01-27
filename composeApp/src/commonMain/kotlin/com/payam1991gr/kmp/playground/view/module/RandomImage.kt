package com.payam1991gr.kmp.playground.view.module

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.data.math.Mat
import com.payam1991gr.kmp.playground.data.math.Vec
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlin.random.Random

@Composable
fun RandomImage(
    modifier: Modifier = Modifier.size(200.dp),
    contentDescription: String? = null,
) {
    val model = remember { RandomImage.genModel(iterations = 4) }
    Box(
        modifier.drawBehind {
            clipRect {
                val (w, h) = size
                model.forEach { data ->
                    when (val curve = data.curve) {
                        is RandomImage.Curve.Quadratic -> {
                            drawPath(
                                Path().apply {
                                    curve.scale(w, h).apply {
                                        p1.apply { moveTo(x, y) }
                                        quadraticTo(control.x, control.y, p2.x, p2.y)
                                        close()
                                    }
                                },
                                data.color,
                            )
                        }

                        is RandomImage.Curve.Cubic1 -> {
                            drawPath(
                                Path().apply {
                                    curve.scale(w, h).apply {
                                        p1.apply { moveTo(x, y) }
                                        cubicTo(c1.x, c1.y, c2.x, c2.y, p2.x, p2.y)
                                        close()
                                    }
                                },
                                data.color,
                            )
                        }

                        is RandomImage.Curve.Cubic2 -> {
                            drawPath(
                                Path().apply {
                                    curve.scale(w, h).apply {
                                        p1.apply { moveTo(x, y) }
                                        cubicTo(c1.x, c1.y, c2.x, c2.y, p2.x, p2.y)
                                        lineTo(p3.x, p3.y)
                                        close()
                                    }
                                },
                                data.color,
                            )
                        }
                    }
                }
            }
        }.semantics { contentDescription?.let { this.contentDescription = it } }
    )
}

object RandomImage {
    private const val ALPHA = .4f
    private val randomFloat get() = Random.nextFloat()

    sealed interface Curve {
        fun noise(deviation: Float): Curve
        fun rotate(rotation: Mat): Curve
        fun scale(w: Float, h: Float): Curve
        fun Offset.scale(w: Float, h: Float) = Offset(x * w, y * h)
        fun Offset.rotate(rotation: Mat) = (rotation * Vec.tor(x - .5f, y - .5f)).let {
            Offset(it[0][0] + .5f, it[1][0] + .5f)
        }

        data class Quadratic(
            val control: Offset,
            val p1: Offset = Offset.Zero,
            val p2: Offset = Offset(1f, 0f),
        ) : Curve {
            override fun noise(deviation: Float): Quadratic = copy(
                p1 = Offset(-randomFloat * deviation, -randomFloat * deviation),
                p2 = Offset(1 + randomFloat * deviation, -randomFloat * deviation),
            )

            override fun rotate(rotation: Mat): Quadratic = copy(
                p1 = p1.rotate(rotation),
                control = control.rotate(rotation),
                p2 = p2.rotate(rotation),
            )

            override fun scale(w: Float, h: Float): Quadratic = copy(
                p1 = p1.scale(w, h),
                control = control.scale(w, h),
                p2 = p2.scale(w, h),
            )
        }

        data class Cubic1(
            val c1: Offset,
            val c2: Offset,
            val p1: Offset = Offset.Zero,
            val p2: Offset = Offset(1f, 1f),
        ) : Curve {
            override fun noise(deviation: Float): Cubic1 = copy(
                p1 = Offset(-randomFloat * deviation, -randomFloat * deviation),
                p2 = Offset(1 + randomFloat * deviation, 1 + randomFloat * deviation),
            )

            override fun rotate(rotation: Mat): Cubic1 = copy(
                p1 = p1.rotate(rotation),
                c1 = c1.rotate(rotation),
                c2 = c2.rotate(rotation),
                p2 = p2.rotate(rotation),
            )

            override fun scale(w: Float, h: Float): Cubic1 = copy(
                p1 = p1.scale(w, h),
                c1 = c1.scale(w, h),
                c2 = c2.scale(w, h),
                p2 = p2.scale(w, h),
            )
        }

        data class Cubic2(
            val c1: Offset,
            val c2: Offset,
            val p1: Offset = Offset.Zero,
            val p2: Offset = Offset(1f, 1f),
            val p3: Offset = Offset(1f, 0f),
        ) : Curve {
            override fun noise(deviation: Float): Cubic2 = copy(
                p2 = Offset(1 + randomFloat * deviation, 1 + randomFloat * deviation),
                p3 = Offset(1 + randomFloat * deviation, -randomFloat * deviation),
            )

            override fun rotate(rotation: Mat): Cubic2 = copy(
                p1 = p1.rotate(rotation),
                c1 = c1.rotate(rotation),
                c2 = c2.rotate(rotation),
                p2 = p2.rotate(rotation),
                p3 = p3.rotate(rotation),
            )

            override fun scale(w: Float, h: Float): Cubic2 = copy(
                p1 = p1.scale(w, h),
                c1 = c1.scale(w, h),
                c2 = c2.scale(w, h),
                p2 = p2.scale(w, h),
                p3 = p3.scale(w, h),
            )
        }
    }

    /**
     * [cos ø, -sin ø]
     * [sin ø,  cos ø]
     */
    private val rotations = listOf(
        Mat.rix()
            .row(1f, 0f)
            .row(0f, 1f),
        Mat.rix()
            .row(0f, -1f)
            .row(1f, 0f),
        Mat.rix()
            .row(-1f, 0f)
            .row(0f, -1f),
        Mat.rix()
            .row(0f, 1f)
            .row(-1f, 0f),
    )

    private fun seedCurves() = persistentListOf(
        Curve.Quadratic(Offset(randomFloat, ((randomFloat * 3 + 1) / 2))),
        Curve.Cubic1(
            Offset(randomFloat / 2, randomFloat + 1),
            Offset((randomFloat + 1) / 2, randomFloat - 1),
        ),
        Curve.Cubic2(
            Offset(randomFloat / 2, randomFloat + 1),
            Offset((randomFloat + 1) / 2, randomFloat - 1),
        ),
    )

    fun genModel(iterations: Int): PersistentList<CurveData> {
        val model = mutableListOf<CurveData>()
        val seedCurves = seedCurves()
        repeat(iterations) { iteration ->
            val deviation = randomFloat * 2
            val rotation = rotations[iteration.mod(4)]
            seedCurves.forEach { curve ->
                model.add(
                    CurveData(
                        curve.noise(deviation).rotate(rotation),
                        Color(
                            randomFloat,
                            randomFloat,
                            randomFloat,
                            ALPHA,
                        ),
                    )
                )
            }
        }
        return model.toPersistentList()
    }
}

data class CurveData(val curve: RandomImage.Curve, val color: Color)
