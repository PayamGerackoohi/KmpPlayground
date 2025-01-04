package com.payam1991gr.kmp.playground.data.math

import com.google.common.truth.Truth.*
import io.mockk.InternalPlatformDsl.toStr
import org.junit.Test

class VecTest {
    @Test
    fun `definition - test`() {
        data class Case(val vector: Vec, val string: String, val size: Int)
        listOf(
            Case(Vec.tor(), "[]", 0),
            Case(Vec.tor(1f), "[1.0]", 1),
            Case(Vec.tor(2f, 1f), "[2.0, 1.0]", 2),
            Case(Vec.tor(2f, 1f, 3f), "[2.0, 1.0, 3.0]", 3),
        ).forEach { (vector, string, size) ->
            assertThat(vector.toStr()).isEqualTo(string)
            assertThat(vector.size()).isEqualTo(size)
        }
    }

    @Test
    fun `get - valid - test`() {
        val vector = Vec.tor(2f, 3f, 5f, 7f)
        assertThat(vector[0]).isEqualTo(2f)
        assertThat(vector[1]).isEqualTo(3f)
        assertThat(vector[2]).isEqualTo(5f)
        assertThat(vector[3]).isEqualTo(7f)
    }

    @Test
    fun `get - out of bound exception - test`() {
        data class Case(val vector: Vec, val index: Int, val error: String)
        listOf(
            Case(Vec.tor(), 0, "Index 0 out of bounds for length 0"),
            Case(Vec.tor(1f), 1, "Index 1 out of bounds for length 1"),
            Case(Vec.tor(1f, 2f), 2, "Index 2 out of bounds for length 2"),
        ).forEach { (vector, index, error) ->
            try {
                vector[index]
            } catch (e: IndexOutOfBoundsException) {
                assertThat(e.message).isEqualTo(error)
            }
        }
    }

    @Test
    fun `equality - test`() {
        assertThat(Vec.tor()).isEqualTo(Vec.tor())
        assertThat(Vec.tor(1f, 2f, 3f)).isEqualTo(Vec.tor(1f, 2f, 3f))
    }
}
