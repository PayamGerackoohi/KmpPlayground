package com.payam1991gr.kmp.playground.presenter.screens.components

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.model.Component
import com.payam1991gr.kmp.playground.presenter.screens.components.robot.robot
import com.payam1991gr.kmp.playground.view.screens.components.ComponentsScreen
import com.payam1991gr.kmp.playground.view.screens.components.ComponentsScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.components.dialog.DialogScreen
import com.payam1991gr.kmp.playground.view.screens.components.carousel.CarouselScreen
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefreshScreen
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ComponentsPresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(ComponentsScreen)
        ComponentsPresenter(navigator).robot {
            onState {
                assertThat(components.size).isEqualTo(4)

                event(Event.OnClick(Component.Carousel))
                assertThat(navigator.awaitNextScreen()).isEqualTo(CarouselScreen)

                event(Event.OnClick(Component.PullToRefresh))
                assertThat(navigator.awaitNextScreen()).isEqualTo(PullToRefreshScreen)

                event(Event.OnClick(Component.Dialog))
                assertThat(navigator.awaitNextScreen()).isEqualTo(DialogScreen)

                event(Event.OnClick(Component.DateTimePicker))
                assertThat(navigator.awaitNextScreen()).isEqualTo(DateTimePickerScreen)

                event(Event.OnBackPressed)
                assertThat(navigator.awaitPop())
                    .isEqualTo(FakeNavigator.PopEvent(DateTimePickerScreen))
            }
        }
    }
}
