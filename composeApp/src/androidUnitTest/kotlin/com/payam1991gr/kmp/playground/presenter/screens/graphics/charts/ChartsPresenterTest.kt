package com.payam1991gr.kmp.playground.presenter.screens.graphics.charts

import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.presenter.screens.graphics.charts.robot.robot
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.graphics.charts.ChartsScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.charts.ChartsScreen.State.Event
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ChartsPresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(ChartsScreen)
        ChartsPresenter(navigator).robot {
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
