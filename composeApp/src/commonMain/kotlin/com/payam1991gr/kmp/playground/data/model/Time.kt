package com.payam1991gr.kmp.playground.data.model

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState

data class Time(val hour: Int = 0, val minute: Int = 0)

@OptIn(ExperimentalMaterial3Api::class)
fun TimePickerState.time() = Time(hour, minute)
