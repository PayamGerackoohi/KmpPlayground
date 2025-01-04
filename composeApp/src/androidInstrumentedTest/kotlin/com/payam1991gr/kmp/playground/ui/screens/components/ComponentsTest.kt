package com.payam1991gr.kmp.playground.ui.screens.components

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.data.model.Component
import com.payam1991gr.kmp.playground.ui.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.ui.screens.components.ComponentsScreen.State
import com.payam1991gr.kmp.playground.ui.screens.components.ComponentsScreen.State.Event
import com.payam1991gr.kmp.playground.ui.screens.components.robot.componentsRobot
import com.payam1991gr.kmp.playground.ui.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test

class ComponentsTest : BaseTest() {
    @Test
    fun test() {
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            Components().Content(
                State(
                    components = persistentListOf(
                        Component.Carousel,
                        Component.PullToRefresh,
                        Component.Dialog,
                        Component.DateTimePicker,
                    ),
                    event = event,
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            toolbar {
                title("Components")

                on("Back") { performClick() }
                verify { event(Event.OnBackPressed) }
            }
            preview {
                componentsRobot(rule) {
                    items {
                        find("Carousel") { performClick() }
                        verify { event(Event.OnClick(Component.Carousel)) }

                        find("Pull to Refresh") { performClick() }
                        verify { event(Event.OnClick(Component.PullToRefresh)) }

                        find("Dialog") { performClick() }
                        verify { event(Event.OnClick(Component.Dialog)) }

                        find("Date-Time Picker") { performClick() }
                        verify { event(Event.OnClick(Component.DateTimePicker)) }
                    }
                }
            }
        }
        confirmVerified()
    }
}
