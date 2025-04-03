package com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.cpp

import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.data.math.Factor
import com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.cpp.robot.robot
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen.State.Number
import com.slack.circuit.test.FakeNavigator
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class CppPresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(CppScreen)
        CppPresenter(navigator, { "3600" }, 1, coroutineContext).robot {
            onState(false, Action.Back, Action.Code) {
                event(Event.OnToolbarAction(Action.Back))
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(null))

                event(Event.OnToolbarAction(Action.Code))
            }
            onState(true, Action.Back, Action.Preview) {
                event(Event.OnToolbarAction(Action.Preview))
            }
            onState(false, Action.Back, Action.Code) {
                assertThat(canEdit).isTrue()
                assertThat(canCalculate).isTrue()
                assertThat(inputNumber).isEmpty()
                assertThat(inputCalculation).isEqualTo(Number.Data())
                assertThat(calculations).isEqualTo(persistentListOf<Number>())
                event(Event.OnNumberChanged("2400"))
            }
            onState { assertThat(canEdit).isFalse() }
            onState { assertThat(inputNumber).isEqualTo("2400") }
            onState { assertThat(inputCalculation).isEqualTo(Number.Calculating("2400")) }
            onState {
                val factors = persistentListOf(
                    Factor("2", "5"),
                    Factor("3"),
                    Factor("5", "2"),
                )
                assertThat(inputCalculation).isEqualTo(Number.Data("2400", factors))
            }
            onState {
                assertThat(canEdit).isTrue()
                event(Event.PerformRandomCalculations)
            }
            onState { assertThat(canCalculate).isFalse() }
            onState { assertThat(calculations).isEqualTo(persistentListOf(Number.Calculating("3,600"))) }
            onState {
                val factors = persistentListOf(
                    Factor("2", "4"),
                    Factor("3", "2"),
                    Factor("5", "2"),
                )
                assertThat(calculations).isEqualTo(
                    persistentListOf(Number.Data("3,600", factors))
                )
            }
            onState { assertThat(canCalculate).isTrue() }
        }
    }
}
