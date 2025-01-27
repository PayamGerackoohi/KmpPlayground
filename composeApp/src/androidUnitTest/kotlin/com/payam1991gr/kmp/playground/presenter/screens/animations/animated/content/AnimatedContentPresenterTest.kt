package com.payam1991gr.kmp.playground.presenter.screens.animations.animated.content

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.animations.animated.content.robot.robot
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContentScreen
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContentScreen.State.Event
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AnimatedContentPresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(AnimatedContentScreen)
        AnimatedContentPresenter(navigator).robot {
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
