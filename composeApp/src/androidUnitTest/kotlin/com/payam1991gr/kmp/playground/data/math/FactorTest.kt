package com.payam1991gr.kmp.playground.data.math

import com.google.common.truth.Truth.*
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test

class FactorTest {
    @Test
    fun `parse - test`() {
        listOf(
            "" to persistentListOf(),
            "2" to persistentListOf(Factor("2")),
            "2*3" to persistentListOf(Factor("2"), Factor("3")),
            "2*3^2*5*7^4" to persistentListOf(
                Factor("2"),
                Factor("3", "2"),
                Factor("5"),
                Factor("7", "4"),
            ),
        ).forEach { (input, output) ->
            assertThat(Factor.parse(input)).isEqualTo(output)
        }
    }
}
