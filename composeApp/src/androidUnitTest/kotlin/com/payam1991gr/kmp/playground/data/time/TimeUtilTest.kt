package com.payam1991gr.kmp.playground.data.time

import androidx.compose.ui.test.junit4.createComposeRule
import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.model.Time
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import screenshots.util.setRobolectricContent

@RunWith(RobolectricTestRunner::class)
class TimeUtilTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun `format - test`() = rule.setRobolectricContent {
        listOf(
            Time() to "Time: 00:00",
            Time(1, 2) to "Time: 01:02",
            Time(13, 45) to "Time: 13:45",
        ).forEach { (time, output) ->
            assertThat(time.format()).isEqualTo(output)
        }
    }
}
