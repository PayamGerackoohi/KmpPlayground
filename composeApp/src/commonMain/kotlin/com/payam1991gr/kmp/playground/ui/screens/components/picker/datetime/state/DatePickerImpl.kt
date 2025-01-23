package com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.payam1991gr.kmp.playground.data.time.DateImpl
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.DateTimePickerScreen.State.DatePicker

@Stable
class DatePickerImpl(
    override val initialDisplayDate: Long? = null,
    initialDate: Long? = null,
) : DatePicker {
    override val date = DateImpl(initialTimestamp = initialDate)
    override fun toString(): String = listOf(
        "initialDisplayDate=$initialDisplayDate",
        "date=$date",
    ).joinToString(", ", "DatePicker(", ")")
}

@Composable
fun rememberDatePickerState(
    initialDisplayDate: Long? = null,
    initialDate: Long? = null,
): DatePicker = remember {
    DatePickerImpl(initialDisplayDate, initialDate)
}
