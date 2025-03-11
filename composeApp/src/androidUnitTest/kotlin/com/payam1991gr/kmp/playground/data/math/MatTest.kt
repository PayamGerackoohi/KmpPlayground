package com.payam1991gr.kmp.playground.data.math

import com.google.common.truth.Truth.*
import io.mockk.InternalPlatformDsl.toStr
import org.junit.Test
import kotlin.math.PI

class MatTest {
    companion object {
        private const val C = (PI / 180).toFloat()
    }

    @Test
    fun `definition - test`() {
        data class Case(val matrix: Mat, val string: String, val dimension: Pair<Int, Int>)
        listOf(
            Case(Mat.rix(), "", 0 to 0),
            Case(Mat.rix().row(1f), "[1.0]", 1 to 1),
            Case(Mat.rix().row(1f, 2f, 3f), "[1.0, 2.0, 3.0]", 1 to 3),
            Case(
                Mat.rix()
                    .row(1f)
                    .row(2f)
                    .row(3f),
                "[1.0]\n[2.0]\n[3.0]",
                3 to 1,
            ),
            Case(Mat.rix().col(1f, 2f, 3f), "[1.0]\n[2.0]\n[3.0]", 3 to 1),
            Case(
                Mat.rix()
                    .row(1f, 2f, 3f)
                    .row(4f, 5f, 6f),
                "[1.0, 2.0, 3.0]\n[4.0, 5.0, 6.0]",
                2 to 3,
            ),
            Case(
                Mat.rix()
                    .col(1f, 4f)
                    .col(2f, 5f)
                    .col(3f, 6f),
                "[1.0, 2.0, 3.0]\n[4.0, 5.0, 6.0]",
                2 to 3,
            ),
        ).forEach { (matrix, string, dimension) ->
            assertThat(matrix.toStr()).isEqualTo(string)
            assertThat(matrix.dimension()).isEqualTo(dimension)
        }
    }

    @Test
    fun `get - valid - test`() {
        val matrix = Mat.rix()
            .row(1f, 2f, 3f)
            .row(4f, 5f, 6f)
        assertThat(matrix[0][0]).isEqualTo(1f)
        assertThat(matrix[0][1]).isEqualTo(2f)
        assertThat(matrix[0][2]).isEqualTo(3f)
        assertThat(matrix[1][0]).isEqualTo(4f)
        assertThat(matrix[1][1]).isEqualTo(5f)
        assertThat(matrix[1][2]).isEqualTo(6f)
    }

    @Test
    fun `get - out of bound exception - test`() {
        data class Case(val matrix: Mat, val i: Int, val j: Int, val error: String)
        listOf(
            Case(Mat.rix(), 0, 0, "Index 0 out of bounds for length 0"),
            Case(Mat.rix().row(1f), 0, 1, "Index 1 out of bounds for length 1"),
            Case(Mat.rix().row(1f), 1, 0, "Index 1 out of bounds for length 1"),
            Case(
                Mat.rix()
                    .row(1f, 2f, 3f)
                    .row(4f, 5f, 6f),
                1, 3, "Index 3 out of bounds for length 3"
            ),
        ).forEach { (matrix, i, j, error) ->
            try {
                matrix[i][j]
            } catch (e: IndexOutOfBoundsException) {
                assertThat(e.message).isEqualTo(error)
            }
        }
    }

    @Test
    fun `vector multiplication - test`() {
        data class Case(val matrix: Mat, val vector: Vec, val result: Mat)
        listOf(
            Case(
                Mat.rix()
                    .row(1f, 0f)
                    .row(0f, 1f),
                Vec.tor(2f, 3f),
                Mat.rix().col(2f, 3f)
            ),
            Case(
                Mat.rix()
                    .row(1f, 2f, 3f)
                    .row(4f, 5f, 6f),
                Vec.tor(7f, 8f, 9f),
                Mat.rix().col(50f, 122f),
            ),
        ).forEach { (matrix, vector, result) ->
            assertThat(matrix * vector).isEqualTo(result)
        }
    }

    @Test
    fun `content - equality - test`() {
        assertThat(Mat.rix()).isEqualTo(Mat.rix())

        assertThat(
            Mat.rix()
                .row(1f, 2f, 3f)
                .row(4f, 5f, 6f)
        ).isEqualTo(
            Mat.rix()
                .row(1f, 2f, 3f)
                .row(4f, 5f, 6f)
        )

        assertThat(Mat.rix()).isNotEqualTo(Mat.rix().row(1f))

        assertThat(
            Mat.rix()
                .row(1f, 2f, 3f)
                .row(4f, 5f, 6f)
        ).isNotEqualTo(
            Mat.rix()
                .row(1f, 2f, 3f)
                .row(4f, .5f, 6f)
        )
    }

    @Test
    fun `non-content equality - test`() { // to make kover happy
        Mat.rix().apply {
            assertThat(equals(this)).isTrue()
            assertThat(equals(null)).isFalse()
            assertThat(equals(0)).isFalse()
        }
    }

    @Test
    fun `hashCode - test`() {
        assertThat(Mat.rix().hashCode())
            .isEqualTo(Mat.rix().hashCode())
        assertThat(
            Mat.rix()
                .row(1f, 2f, 3f)
                .row(4f, 5f, 6f)
                .hashCode()
        ).isEqualTo(
            Mat.rix()
                .row(1f, 2f, 3f)
                .row(4f, 5f, 6f)
                .hashCode()
        )
    }

    @Test
    fun `rotationOf - test`() = listOf(
        0f to Mat.rix().row(1f, -0f).row(0f, 1f),
        30f to Mat.rix().row(0.8660254f, -0.5f).row(0.5f, 0.8660254f),
        45f to Mat.rix().row(0.70710677f, -0.70710677f).row(0.70710677f, 0.70710677f),
        60f to Mat.rix().row(0.49999997f, -0.86602545f).row(0.86602545f, 0.49999997f),
        90f to Mat.rix().row(-4.371139E-8f, -1.0f).row(1.0f, -4.371139E-8f),
        135f to Mat.rix().row(-0.70710677f, -0.70710677f).row(0.70710677f, -0.70710677f),
        180f to Mat.rix().row(-1.0f, 8.742278E-8f).row(-8.742278E-8f, -1.0f),
        270f to Mat.rix().row(1.1924881E-8f, 1.0f).row(-1.0f, 1.1924881E-8f),
        360f to Mat.rix().row(1.0f, -1.7484555E-7f).row(1.7484555E-7f, 1.0f),
    ).forEach { (input, output) ->
        assertWithMessage("rotationOf($input deg) = $output")
            .that(Mat.rotationOf(input * C))
            .isEqualTo(output)
    }

    @Test
    fun `flatten - valid - test`() {
        listOf(
            Mat.rix() to Vec.tor(),
            Mat.rix().row() to Vec.tor(),
            Mat.rix().row(1f) to Vec.tor(1f),
            Mat.rix().row(1f).row(2f) to Vec.tor(1f, 2f),
            Mat.rix().row(10f).row(20f).row(30f) to Vec.tor(10f, 20f, 30f),
        ).forEach { (input, output) ->
            assertWithMessage("${input}.flatten() = $output")
                .that(input.flatten())
                .isEqualTo(output)
        }
    }

    @Test
    fun `flatten - invalid - test`() {
        listOf(
            Mat.rix().row(1f, 2f),
            Mat.rix().row(1f, 2f).row(3f, 4f),
            Mat.rix().row(1f).row(3f, 4f),
        ).forEach { input ->
            try {
                input.flatten()
            } catch (e: Exception) {
                assertThat(e.message).isEqualTo("Invalid Dimension")
            }
        }
    }
}
