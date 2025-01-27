package com.payam1991gr.kmp.playground.presenter.screens.components.picker.datetime

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.payam1991gr.kmp.playground.data.model.Time
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.toolbar.rememberToolbarState
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen.State
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen.State.TimeState.Event as TimeStateEvent
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.state.rememberDatePickerState
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.state.rememberDateRangePickerState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant

class DateTimePickerPresenter(private val navigator: Navigator) : Presenter<State> {
    private val initialDate = LocalDateTime(1991, 10, 14, 0, 0)
        .toInstant(TimeZone.UTC)
        .toEpochMilliseconds()

    @Composable
    override fun present(): State {
        val toolbarState = rememberToolbarState()
        val datePickerState = rememberDatePickerState(initialDate)
        val dateRangePicker = rememberDateRangePickerState(initialDate)
        val timePickerTime = remember { mutableStateOf(Time()) }
        val timeInputTime = remember { mutableStateOf(Time()) }
        return State(
            showCode = toolbarState.showCode,
            toolbarActions = toolbarState.actions,
            datePicker = datePickerState,
            dateRangePicker = dateRangePicker,
            timePicker = timePickerTime.toTimeState(),
            timeInput = timeInputTime.toTimeState(),
        ) {
            when (it) {
                is State.Event.OnToolbarAction -> when (it.action) {
                    SamplePage.Action.Back -> navigator.pop()
                    SamplePage.Action.Code -> toolbarState.code()
                    SamplePage.Action.Preview -> toolbarState.preview()
                }
            }
        }
    }

    private fun MutableState<Time>.toTimeState() = State.TimeState(value) {
        value = it.update(value)
    }

    private fun TimeStateEvent.update(time: Time) = when (this) {
        is TimeStateEvent.OnHourChanged -> time.copy(hour = hour)
        is TimeStateEvent.OnMinuteChanged -> time.copy(minute = minute)
    }
}
