package com.payam1991gr.kmp.playground.ui.screens.miscellaneous

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.data.model.MiscellaneousItem
import com.payam1991gr.kmp.playground.ui.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.MiscellaneousScreen.State
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.MiscellaneousScreen.State.Event
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.robot.miscellaneousRobot
import com.payam1991gr.kmp.playground.ui.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.toPersistentList
import org.junit.Test

class MiscellaneousTest : BaseTest() {
    @Test
    fun test() {
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            Miscellaneous().Content(
                State(
                    items = MiscellaneousItem.entries.toPersistentList(),
                    event = event,
                ), Modifier
            )
        }
        samplePageRobot(rule) {
            toolbar {
                title("Miscellaneous")
                on("Back") { performClick() }
                verify { event(Event.OnBackPressed) }
            }
            preview {
                miscellaneousRobot(rule) {
                    items {
                        find("C++") { performClick() }
                        verify { event(Event.OnClick(MiscellaneousItem.Cpp)) }

                        find("BLE") { performClick() }
                        verify { event(Event.OnClick(MiscellaneousItem.Ble)) }

                        find("PDF") { performClick() }
                        verify { event(Event.OnClick(MiscellaneousItem.Pdf)) }
                    }
                }
            }
        }
        confirmVerified()
    }
}
