package com.payam1991gr.kmp.playground.view.screens.graphics

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.data.model.GraphicItem
import com.payam1991gr.kmp.playground.view.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.view.screens.graphics.GraphicsScreen.State
import com.payam1991gr.kmp.playground.view.screens.graphics.GraphicsScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.graphics.robot.graphicsRobot
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.toPersistentList
import org.junit.Test

class GraphicsTest : BaseTest() {
    @Test
    fun test() {
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            Graphics().Content(
                State(
                    items = GraphicItem.entries.toPersistentList(),
                    event = event,
                ), Modifier
            )
        }
        samplePageRobot(rule) {
            toolbar {
                title("Graphics")
                on("Back") { performClick() }
                verify { event(Event.OnBackPressed) }
            }
            preview {
                graphicsRobot(rule) {
                    items {
                        find("Charts") { performClick() }
                        verify { event(Event.OnClick(GraphicItem.Charts)) }

                        find("OpenGL") { performClick() }
                        verify { event(Event.OnClick(GraphicItem.OpenGL)) }
                    }
                }
            }
        }
        confirmVerified()
    }
}
