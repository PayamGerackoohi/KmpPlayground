package com.payam1991gr.kmp.playground.presenter.screens.components.carousel

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.components.carousel.robot.robot
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.components.carousel.CarouselScreen
import com.payam1991gr.kmp.playground.view.screens.components.carousel.CarouselScreen.State.Event
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CarouselPresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(CarouselScreen)
        CarouselPresenter(navigator).robot {
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
