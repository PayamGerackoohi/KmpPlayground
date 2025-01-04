package com.payam1991gr.kmp.playground.ui.module

import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.ui.module.robot.sampleListRobot
import com.payam1991gr.kmp.playground.ui.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.toPersistentList
import org.junit.Test

class SampleListTest : BaseTest() {
    @Test
    fun test() {
        val onClick = mockk<(String) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            SampleList(
                samples = List(3) { "Item ${it + 1}" }.toPersistentList(),
                nameOf = { it },
                onClick = onClick,
            )
        }
        sampleListRobot(rule) {
            items {
                find("Item 1") { performClick() }
                verify { onClick("Item 1") }

                find("Item 2") { performClick() }
                verify { onClick("Item 2") }

                find("Item 3") { performClick() }
                verify { onClick("Item 3") }
            }
        }
        confirmVerified()
    }
}
