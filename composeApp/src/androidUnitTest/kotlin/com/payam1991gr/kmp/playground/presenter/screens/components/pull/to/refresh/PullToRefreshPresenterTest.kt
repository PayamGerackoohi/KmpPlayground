package com.payam1991gr.kmp.playground.presenter.screens.components.pull.to.refresh

import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.presenter.screens.components.pull.to.refresh.robot.robot
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefreshScreen
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefreshScreen.State.Event
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Test

class PullToRefreshPresenterTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(PullToRefreshScreen)
        PullToRefreshPresenter(navigator).robot {
            onState(false, Action.Back, Action.Code) {
                event(Event.OnToolbarAction(Action.Back))
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(null))

                event(Event.OnToolbarAction(Action.Code))
            }
            onState(true, Action.Back, Action.Preview) {
                event(Event.OnToolbarAction(Action.Preview))
            }
            onState(false, Action.Back, Action.Code) {
                assertThat(linearProgressIndicator.itemCount).isEqualTo(5)
                assertThat(pullToRefreshBox.itemCount).isEqualTo(5)
                assertThat(scaling.itemCount).isEqualTo(5)
                assertThat(customBehavior.itemCount).isEqualTo(5)

                linearProgressIndicator.apply {
                    onRefresh()
                    advanceTimeBy(501)
                    assertThat(itemCount).isEqualTo(6)
                    onRefresh()
                    advanceTimeBy(501)
                    assertThat(itemCount).isEqualTo(7)
                }

                pullToRefreshBox.apply {
                    onRefresh()
                    advanceTimeBy(501)
                    assertThat(itemCount).isEqualTo(6)
                }
            }
        }
    }
}
