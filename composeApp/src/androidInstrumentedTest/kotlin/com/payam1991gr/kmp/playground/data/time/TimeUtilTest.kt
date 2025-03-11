package com.payam1991gr.kmp.playground.data.time

import androidx.compose.ui.test.junit4.createComposeRule
import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.data.model.time.Time
import org.junit.Rule
import org.junit.Test

class TimeUtilTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun formatTest() {
        val cases = listOf(
            Time() to "Time: 00:00",
            Time(1, 2) to "Time: 01:02",
            Time(13, 45) to "Time: 13:45",
        )
        rule.setContent {
            cases.forEach { (time, output) ->
                assertThat(time.format()).isEqualTo(output)
            }
        }
    }
}
