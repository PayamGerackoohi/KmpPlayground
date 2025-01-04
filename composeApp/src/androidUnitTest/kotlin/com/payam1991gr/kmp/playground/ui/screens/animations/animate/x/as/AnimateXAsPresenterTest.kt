package com.payam1991gr.kmp.playground.ui.screens.animations.animate.x.`as`

import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.animations.animate.x.`as`.robot.robot
import com.payam1991gr.kmp.playground.ui.screens.animations.animate.x.`as`.AnimateXAsScreen.State.Event
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AnimateXAsPresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(AnimateXAsScreen)
        AnimateXAsPresenter(navigator).robot {
            onState(false, Action.Back, Action.Code) {
                event(Event.OnToolbarAction(Action.Back))
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(null))

                event(Event.OnToolbarAction(Action.Code))
            }
            onState(true, Action.Back, Action.Preview) {
                event(Event.OnToolbarAction(Action.Preview))
            }
            onState(false, Action.Back, Action.Code)
        }
    }
}
