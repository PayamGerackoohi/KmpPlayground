package com.payam1991gr.kmp.playground.view.screens.io

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.data.model.IoItem
import com.payam1991gr.kmp.playground.view.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.view.screens.io.IoScreen.State
import com.payam1991gr.kmp.playground.view.screens.io.IoScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.io.robot.ioRobot
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.toPersistentList
import org.junit.Test

class IoTest : BaseTest() {
    @Test
    fun test() {
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            Io().Content(
                State(
                    items = IoItem.entries.toPersistentList(),
                    event = event,
                ), Modifier
            )
        }
        samplePageRobot(rule) {
            toolbar {
                title("I/O")
                on("Back") { performClick() }
                verify { event(Event.OnBackPressed) }
            }
            preview {
                ioRobot(rule) {
                    items {
                        find("Datastore") { performClick() }
                        verify { event(Event.OnClick(IoItem.Datastore)) }

//                        find("Database") { performClick() }
//                        verify { event(Event.OnClick(IoItem.Database)) }

                        find("API") { performClick() }
                        verify { event(Event.OnClick(IoItem.API)) }

//                        find("File I/O") { performClick() }
//                        verify { event(Event.OnClick(IoItem.File)) }
                    }
                }
            }
        }
        confirmVerified()
    }
}
