package com.payam1991gr.kmp.playground.ui.screens.animations.crossfade

import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.animations.crossfade.CrossfadeScreen.State.Event
import com.payam1991gr.kmp.playground.ui.screens.animations.crossfade.robot.robot
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CrossfadePresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(CrossfadeScreen)
        CrossfadePresenter(navigator).robot {
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
