package com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime

import androidx.compose.runtime.Stable
import com.payam1991gr.kmp.playground.data.model.Time
import com.payam1991gr.kmp.playground.data.time.Date
import com.payam1991gr.kmp.playground.platform.KmpParcelize
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.PersistentList

@KmpParcelize
data object DateTimePickerScreen : Screen {
    data class State(
        val showCode: Boolean,
        val toolbarActions: PersistentList<Action>,
        val datePicker: DatePicker,
        val dateRangePicker: DateRangePicker,
        val timePicker: TimeState,
        val timeInput: TimeState,
        val event: (Event) -> Unit,
    ) : CircuitUiState {
        sealed interface Event : CircuitUiEvent {
            data class OnToolbarAction(val action: Action) : Event
        }

        @Stable
        interface DatePicker {
            val initialDisplayDate: Long?
            val date: Date
        }

        @Stable
        interface DateRangePicker {
            val initialDisplayDate: Long?
            val startDate: Date
            val endDate: Date
            fun msRange(): String
            fun textRange(): String
        }

        data class TimeState(
            val time: Time,
            val event: (Event) -> Unit,
        ) {
            sealed interface Event {
                data class OnHourChanged(val hour: Int) : Event
                data class OnMinuteChanged(val minute: Int) : Event
            }

            override fun toString(): String = "TimeState(time=$time)"
        }
    }
}
