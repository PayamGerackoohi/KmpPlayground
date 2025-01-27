package com.payam1991gr.kmp.playground.preview

import com.payam1991gr.kmp.playground.data.model.Time
import com.payam1991gr.kmp.playground.data.time.Date
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen.State
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen.State.TimeState.Event

private typealias DTDate = Date

object Fake {
    val timestamp by lazy { 687398400000 } // Monday, October 14, 1991
    val timestamp2 by lazy { 688867200000 } // Thursday, October 31, 1991

    class Date(
        override var ms: Long? = null,
        override val text: String? = null,
    ) : DTDate

    class DatePickerState(
        override val initialDisplayDate: Long? = null,
        override val date: DTDate = Date(),
    ) : State.DatePicker

    class DateRangePickerState(
        override val initialDisplayDate: Long? = null,
        override val startDate: DTDate = Date(),
        override val endDate: DTDate = Date(),
    ) : State.DateRangePicker {
        override fun msRange(): String = ""
        override fun textRange(): String = ""
    }

    fun timeState(time: Time = Time(), event: (Event) -> Unit = {}) = State.TimeState(time, event)
}
