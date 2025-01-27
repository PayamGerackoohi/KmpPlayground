package com.payam1991gr.kmp.playground.view.screens.animations

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.data.model.AnimationItem
import com.payam1991gr.kmp.playground.view.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.view.screens.animations.AnimationsScreen.State
import com.payam1991gr.kmp.playground.view.screens.animations.AnimationsScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.animations.robot.animationsRobot
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.toPersistentList
import org.junit.Test

class AnimationsTest : BaseTest() {
    @Test
    fun test() {
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            Animations().Content(
                State(
                    items = AnimationItem.entries.toPersistentList(),
                    event = event,
                ), Modifier
            )
        }
        samplePageRobot(rule) {
            toolbar {
                title("Animations")
                on("Back") { performClick() }
                verify { event(Event.OnBackPressed) }
            }
            assertThatCodePageIsNotDisplayed()
            preview {
                animationsRobot(rule) {
                    items {
                        find("Animated Content") { performClick() }
                        verify { event(Event.OnClick(AnimationItem.AnimatedContent)) }

                        find("Animated Visibility") { performClick() }
                        verify { event(Event.OnClick(AnimationItem.AnimatedVisibility)) }

                        find("Crossfade") { performClick() }
                        verify { event(Event.OnClick(AnimationItem.Crossfade)) }

                        find("Animate X as") { performClick() }
                        verify { event(Event.OnClick(AnimationItem.AnimateXAs)) }
                    }
                }
            }
        }
        confirmVerified()
    }
}
