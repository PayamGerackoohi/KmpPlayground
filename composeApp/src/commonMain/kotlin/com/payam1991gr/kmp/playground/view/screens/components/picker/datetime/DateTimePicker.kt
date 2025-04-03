package com.payam1991gr.kmp.playground.view.screens.components.picker.datetime

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.data.string.box
import com.payam1991gr.kmp.playground.data.model.sample.appendTime
import com.payam1991gr.kmp.playground.data.model.sample.rememberSetting
import com.payam1991gr.kmp.playground.data.time.format
import com.payam1991gr.kmp.playground.data.time.sample.appendTime_format
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.ContentList
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Description
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Header
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Settings
import com.payam1991gr.kmp.playground.view.module.SamplePage.preview
import com.payam1991gr.kmp.playground.view.module.editor.CodePanel
import com.payam1991gr.kmp.playground.view.module.editor.rememberCodeEditor
import com.payam1991gr.kmp.playground.view.module.sample.appendContentList
import com.payam1991gr.kmp.playground.view.sample.appendBox
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen.State
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen.State.TimeState.Event as TimeStateEvent
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.sample.appendDatePickerSample
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.sample.appendDateRangePickerSample
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.sample.appendRememberIs24HourSetting
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.sample.appendRememberModeToggleSetting
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.sample.appendResources
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.sample.appendState_TimeState
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.sample.appendTimeInputSample
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.sample.appendTimePickerSample
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.sample.appendTimeSample
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

class DateTimePicker : Ui<State> {
    private val timestampStringRes = Res.string.components_date_picker_timestamp_template
    private val dateStringRes = Res.string.components_date_picker_date_template

    @Composable
    override fun Content(state: State, modifier: Modifier) {
        SamplePage(
            showCode = state.showCode,
            titleRes = Res.string.components_date_time_picker,
            actions = state.toolbarActions,
            onClick = { state.event(Event.OnToolbarAction(it)) },
            preview = { Preview(state) },
            code = { Code() },
        )
    }

    @Composable
    fun Preview(state: State) = preview(
        { Header("Date Picker") },
        { Description(stringResource(Res.string.components_date_picker_description)) },
        { DatePickerSample(state.datePicker) },

        { Header("Date Range Picker") },
        { Description(stringResource(Res.string.components_date_range_picker_description)) },
        { DateRangePickerSample(state.dateRangePicker) },

        { Header("Time Picker") },
        { Description(stringResource(Res.string.components_time_picker_description)) },
        { TimePickerSample(state.timePicker) },

        { Header("Time Input") },
        { Description(stringResource(Res.string.components_time_input_description)) },
        { TimeInputSample(state.timeInput) },
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DatePickerSample(state: State.DatePicker) = ContentList {
        rememberDatePickerState(
            initialSelectedDateMillis = state.date.ms,
            initialDisplayedMonthMillis = state.initialDisplayDate,
        ).apply {
            val showModeToggle = rememberModeToggleSetting()
            Settings(showModeToggle)
            Text(stringResource(timestampStringRes, state.date.ms.box()))
            Text(stringResource(dateStringRes, state.date.text.box()))
            selectedDateMillis.let { remember(it) { state.date.ms = it } }
            DatePicker(
                state = this,
                showModeToggle = showModeToggle.value,
                modifier = Modifier.testTag("DatePicker")
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DateRangePickerSample(state: State.DateRangePicker) = ContentList {
        rememberDateRangePickerState(
            initialSelectedStartDateMillis = state.startDate.ms,
            initialSelectedEndDateMillis = state.endDate.ms,
            initialDisplayedMonthMillis = state.initialDisplayDate,
        ).apply {
            selectedStartDateMillis.let { remember(it) { state.startDate.ms = it } }
            selectedEndDateMillis.let { remember(it) { state.endDate.ms = it } }
            val showModeToggle = rememberModeToggleSetting()
            Settings(
                showModeToggle,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .testTag("DateRangePicker.Settings")
            )
            Text(stringResource(timestampStringRes, state.msRange()))
            Text(stringResource(dateStringRes, state.textRange()))
            DateRangePicker(
                state = this,
                showModeToggle = showModeToggle.value,
                modifier = Modifier
                    .height(450.dp)
                    .testTag("DateRangePicker")
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TimePickerSample(state: State.TimeState) = "TimePicker".let { tag ->
        TimeSample(state, tag) {
            TimePicker(this, Modifier.testTag(tag))
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TimeInputSample(state: State.TimeState) = "TimeInput".let { tag ->
        TimeSample(state, tag) {
            TimeInput(this, Modifier.testTag(tag))
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun TimeSample(
        state: State.TimeState,
        tag: String,
        content: @Composable TimePickerState.() -> Unit,
    ) = ContentList {
        val is24Hour = rememberIs24HourSetting()
        Settings(
            is24Hour,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .testTag("$tag.Settings")
        )
        state.time.run {
            rememberTimePickerState(
                initialHour = hour,
                initialMinute = minute,
            )
        }.apply {
            remember(hour) { state.event(TimeStateEvent.OnHourChanged(hour)) }
            remember(minute) { state.event(TimeStateEvent.OnMinuteChanged(minute)) }
            is24hour = is24Hour.value
            Text(
                state.time.format(),
                modifier = Modifier.testTag("$tag.Text")
            )
            Crossfade(is24Hour.value) { content() }
        }
    }

    @Composable
    fun rememberModeToggleSetting() = rememberSetting(true) {
        if (it) Res.string.components_date_picker_mode_toggle
        else Res.string.components_date_picker_no_mode_toggle
    }

    @Composable
    private fun rememberIs24HourSetting() = rememberSetting {
        if (it) Res.string.components_time_picker_24_hour
        else Res.string.components_time_picker_12_hour
    }

    @Composable
    fun Code() = CodePanel(rememberCodeEditor {
        appendResources()

        appendDatePickerSample()
        appendDateRangePickerSample()
        appendTimePickerSample()
        appendTimeInputSample()

        appendTimeSample()
        appendContentList()
        appendBox()
        appendRememberModeToggleSetting()
        appendRememberIs24HourSetting()
        appendTime()
        appendState_TimeState()
        appendTime_format()
    })
}
