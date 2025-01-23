package com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.payam1991gr.kmp.playground.data.box
import com.payam1991gr.kmp.playground.data.time.DateImpl
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.DateTimePickerScreen.State.DateRangePicker

@Stable
class DateRangePickerImpl(
    override val initialDisplayDate: Long? = null,
    initialStartDate: Long? = null,
    initialEndDate: Long? = null,
) : DateRangePicker {
    override val startDate = DateImpl(initialStartDate)
    override val endDate = DateImpl(initialEndDate)
    override fun msRange(): String = "${startDate.ms.box()} - ${endDate.ms.box()}"
    override fun textRange(): String = "${startDate.text.box()} - ${endDate.text.box()}"
    override fun toString(): String = listOf(
        "initialDisplayDate=$initialDisplayDate",
        "startDate=$startDate",
        "endDate=$endDate",
    ).joinToString(", ", "DateRangePicker(", ")")
}

@Composable
fun rememberDateRangePickerState(
    initialDisplayDate: Long? = null,
    initialStartDate: Long? = null,
    initialEndDate: Long? = null,
): DateRangePicker = remember {
    DateRangePickerImpl(
        initialDisplayDate = initialDisplayDate,
        initialStartDate = initialStartDate,
        initialEndDate = initialEndDate,
    )
}
