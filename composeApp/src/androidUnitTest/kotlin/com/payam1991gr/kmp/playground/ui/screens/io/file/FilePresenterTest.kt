package com.payam1991gr.kmp.playground.ui.screens.io.file

import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.screens.io.file.FileScreen.State.Event
import com.payam1991gr.kmp.playground.ui.screens.io.file.robot.robot
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FilePresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(FileScreen)
        FilePresenter(navigator).robot {
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
