package com.payam1991gr.kmp.playground.preview.screens.miscellaneous.datetime

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.data.repository.TimeZoneRepositoryImpl
import com.payam1991gr.kmp.playground.preview.*
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.DateTime
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.DateTimeScreen.State
import kotlinx.collections.immutable.persistentListOf
import kotlinx.datetime.Instant
import kotlinx.datetime.Clock

@SinglePreview
@Composable
fun DateTime_Preview_Preview() = preview {
    val timeZoneRepository = remember { TimeZoneRepositoryImpl() }
    DateTime().Content(
        state = State(
            showCode = false,
            toolbarActions = persistentListOf(
                Action.Back,
                Action.Preview,
            ),
            now = Clock.System.now(),
            timeZones = timeZoneRepository.timeZones,
        ) {},
        modifier = Modifier
    )
}

@Composable
fun DateTime_Code_Preview() = preview {
    DateTime().Content(
        State(
            showCode = true,
            toolbarActions = persistentListOf(
                Action.Back,
                Action.Code,
            ),
            now = Instant.fromEpochSeconds(0),
            timeZones = persistentListOf(),
        ) {},
        modifier = Modifier
    )
}
