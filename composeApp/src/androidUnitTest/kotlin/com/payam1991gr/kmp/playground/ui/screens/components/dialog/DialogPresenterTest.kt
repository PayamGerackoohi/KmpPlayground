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
            onState(
                Action.Back, Action.Code,
                showCode = false,
                datePicker = "DatePicker(initialDisplayDate=null, date=Date(ms=null, text=null))",
            ) {
                event(Event.OnToolbarAction(Action.Back))
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(null))

                event(Event.OnToolbarAction(Action.Code))
            }
            onState(
                Action.Back, Action.Preview,
                showCode = true,
            ) {
                event(Event.OnToolbarAction(Action.Preview))
            }
            onState(
                Action.Back, Action.Code,
                showCode = false,
            )
        }
    }
}
