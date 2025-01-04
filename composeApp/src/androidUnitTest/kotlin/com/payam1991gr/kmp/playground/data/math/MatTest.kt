package com.payam1991gr.kmp.playground.data.math

import com.google.common.truth.Truth.*
import io.mockk.InternalPlatformDsl.toStr
import org.junit.Test

class MatTest {
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
    fun `equality - test`() {
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
    }
}
