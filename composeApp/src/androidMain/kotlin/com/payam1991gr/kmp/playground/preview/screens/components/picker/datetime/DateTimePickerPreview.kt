package com.payam1991gr.kmp.playground.preview.screens.components.picker.datetime

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.data.model.time.Time
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.preview.Fake.timestamp
import com.payam1991gr.kmp.playground.preview.Fake.timestamp2
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePicker
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen.State
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.state.rememberDatePickerState
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.state.rememberDateRangePickerState
import kotlinx.collections.immutable.persistentListOf

@SinglePreview
@Composable
fun DateTimePicker_Preview_Preview() = preview {
    val datePicker = rememberDatePickerState(timestamp)
    val dateRangePicker = rememberDateRangePickerState(timestamp)
    DateTimePicker().Content(
        State(
            showCode = false,
            toolbarActions = persistentListOf(Action.Back, Action.Code),
            datePicker = datePicker,
            dateRangePicker = dateRangePicker,
            timePicker = State.TimeState(Time()) {},
            timeInput = State.TimeState(Time()) {},
        ) {},
        Modifier,
    )
}

@Composable
fun DateTimePicker_Code_Preview() = preview {
    val datePicker = Fake.run { rememberDatePickerState(timestamp, timestamp) }
    val dateRangePicker = rememberDateRangePickerState(timestamp, timestamp, timestamp2)
    DateTimePicker().Content(
        State(
            showCode = true,
            toolbarActions = persistentListOf(Action.Back, Action.Preview),
            datePicker = datePicker,
            dateRangePicker = dateRangePicker,
            timePicker = State.TimeState(Time()) {},
            timeInput = State.TimeState(Time()) {},
        ) {},
        Modifier,
    )
}
