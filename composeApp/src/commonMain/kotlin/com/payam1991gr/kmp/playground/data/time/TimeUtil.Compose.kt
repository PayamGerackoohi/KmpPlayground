package com.payam1991gr.kmp.playground.data.time

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.data.model.Time
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.components_time_picker_time_template
import org.jetbrains.compose.resources.stringResource

@Composable
fun Time.format() = stringResource(
    Res.string.components_time_picker_time_template,
    hour.toPaddedString(),
    minute.toPaddedString(),
)
