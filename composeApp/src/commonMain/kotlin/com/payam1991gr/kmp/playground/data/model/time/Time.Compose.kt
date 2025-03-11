package com.payam1991gr.kmp.playground.data.model.time

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TimePickerState

@OptIn(ExperimentalMaterial3Api::class)
fun TimePickerState.time() = Time(hour, minute)
