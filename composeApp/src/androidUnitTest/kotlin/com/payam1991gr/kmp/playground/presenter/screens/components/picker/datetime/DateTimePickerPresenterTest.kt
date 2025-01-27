package com.payam1991gr.kmp.playground.presenter.screens.components.picker.datetime

import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.presenter.screens.components.picker.datetime.robot.robot
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen.State.TimeState.Event as TimeStateEvent
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DateTimePickerPresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(DateTimePickerScreen)
        DateTimePickerPresenter(navigator).robot {
            onState(
                Action.Back, Action.Code,
                showCode = false,
                datePicker = "DatePicker(initialDisplayDate=687398400000, date=Date(ms=null, text=null))",
                dateRangePicker = "DateRangePicker(initialDisplayDate=687398400000, startDate=Date(ms=null, text=null), endDate=Date(ms=null, text=null))",
                timePicker = "TimeState(time=Time(hour=0, minute=0))",
                timeInput = "TimeState(time=Time(hour=0, minute=0))",
            ) {
                event(Event.OnToolbarAction(Action.Back))
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(null))

                event(Event.OnToolbarAction(Action.Code))
            }
            onState(Action.Back, Action.Preview, showCode = true) {
                event(Event.OnToolbarAction(Action.Preview))
            }
            onState(Action.Back, Action.Code, showCode = false) {
                timePicker.event(TimeStateEvent.OnHourChanged(13))
            }
            onState(
                timePicker = "TimeState(time=Time(hour=13, minute=0))",
                timeInput = "TimeState(time=Time(hour=0, minute=0))",
            ) { timeInput.event(TimeStateEvent.OnMinuteChanged(31)) }
            onState(
                timePicker = "TimeState(time=Time(hour=13, minute=0))",
                timeInput = "TimeState(time=Time(hour=0, minute=31))",
            ) { timePicker.event(TimeStateEvent.OnMinuteChanged(2)) }
            onState(
                timePicker = "TimeState(time=Time(hour=13, minute=2))",
                timeInput = "TimeState(time=Time(hour=0, minute=31))",
            ) { timeInput.event(TimeStateEvent.OnHourChanged(4)) }
            onState(
                timePicker = "TimeState(time=Time(hour=13, minute=2))",
                timeInput = "TimeState(time=Time(hour=4, minute=31))",
            )
        }
    }
}
