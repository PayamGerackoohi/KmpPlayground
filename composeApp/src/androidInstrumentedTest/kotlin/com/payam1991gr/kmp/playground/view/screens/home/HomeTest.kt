package com.payam1991gr.kmp.playground.view.screens.home

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.data.model.HomeItem
import com.payam1991gr.kmp.playground.view.screens.home.HomeScreen.State
import com.payam1991gr.kmp.playground.view.screens.home.HomeScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.home.robot.homeRobot
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.toPersistentList
import org.junit.Test

class HomeTest : BaseTest() {
    @Test
    fun test() {
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            Home().Content(
                State(
                    items = HomeItem.entries.toPersistentList(),
                    event = event,
                ),
                Modifier,
            )
        }
        homeRobot(rule) {
            items {
                find("Animations") { performClick() }
                verify { event(Event.OnItemClicked(HomeItem.Animations)) }

                find("Components") { performClick() }
                verify { event(Event.OnItemClicked(HomeItem.Components)) }

                find("Graphics") { performClick() }
                verify { event(Event.OnItemClicked(HomeItem.Graphics)) }

                find("I/O") { performClick() }
                verify { event(Event.OnItemClicked(HomeItem.Io)) }

                swipeUp()

                find("Miscellaneous") { performClick() }
                verify { event(Event.OnItemClicked(HomeItem.Miscellaneous)) }
            }
        }
        confirmVerified()
    }
}
