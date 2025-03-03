package com.payam1991gr.kmp.playground.presenter.screens.home

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.model.HomeItem
import com.payam1991gr.kmp.playground.presenter.screens.home.robot.robot
import com.payam1991gr.kmp.playground.view.screens.animations.AnimationsScreen
import com.payam1991gr.kmp.playground.view.screens.components.ComponentsScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.GraphicsScreen
import com.payam1991gr.kmp.playground.view.screens.home.HomeScreen
import com.payam1991gr.kmp.playground.view.screens.home.HomeScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.io.IoScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.MiscellaneousScreen
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class HomePresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(HomeScreen)
        HomePresenter(navigator).robot {
            onState {
                assertThat(items.size).isEqualTo(HomeItem.entries.size)

                event(Event.OnItemClicked(HomeItem.Components))
                assertThat(navigator.awaitNextScreen()).isEqualTo(ComponentsScreen)

                event(Event.OnItemClicked(HomeItem.Animations))
                assertThat(navigator.awaitNextScreen()).isEqualTo(AnimationsScreen)

                event(Event.OnItemClicked(HomeItem.Graphics))
                assertThat(navigator.awaitNextScreen()).isEqualTo(GraphicsScreen)

                event(Event.OnItemClicked(HomeItem.Io))
                assertThat(navigator.awaitNextScreen()).isEqualTo(IoScreen)

                event(Event.OnItemClicked(HomeItem.Miscellaneous))
                assertThat(navigator.awaitNextScreen()).isEqualTo(MiscellaneousScreen)
            }
        }
    }
}
