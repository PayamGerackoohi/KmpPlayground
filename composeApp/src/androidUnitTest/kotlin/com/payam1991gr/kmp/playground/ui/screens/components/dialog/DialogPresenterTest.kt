package com.payam1991gr.kmp.playground.ui.screens.components.dialog

import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogScreen.State.Event
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.robot.robot
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DialogPresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(DialogScreen)
        DialogPresenter(navigator).robot {
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
