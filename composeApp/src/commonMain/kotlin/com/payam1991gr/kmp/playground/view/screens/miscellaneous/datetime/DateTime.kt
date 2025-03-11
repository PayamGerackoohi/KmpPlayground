package com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.data.model.time.LabeledTimeZone
import com.payam1991gr.kmp.playground.data.time.offsetStringAt
import com.payam1991gr.kmp.playground.data.time.sample.appendTimeZoneData
import com.payam1991gr.kmp.playground.data.time.timeFormat
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.SamplePage.preview
import com.payam1991gr.kmp.playground.view.module.clock.AnalogClock
import com.payam1991gr.kmp.playground.view.module.clock.TimeZoneData
import com.payam1991gr.kmp.playground.view.module.editor.codes
import com.payam1991gr.kmp.playground.view.module.sample.appendAnalogClock
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.DateTimeScreen.State
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.DateTimeScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.sample.*
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*
import kotlinx.datetime.Instant
import kotlinx.datetime.toLocalDateTime

typealias ComposeFun = @Composable () -> Unit

class DateTime : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) = SamplePage(
        showCode = state.showCode,
        titleRes = Res.string.miscellaneous_date_time,
        actions = state.toolbarActions,
        onClick = { state.event(Event.OnToolbarAction(it)) },
        preview = { state.Preview() },
        code = { Code() },
    )

    @Composable
    private fun State.Preview() = preview(
        contents = timeZones.map { { AnalogClockCard(now, it) } },
        gridCells = GridCells.Adaptive(minSize = 175.dp),
    )

    private fun <T> Iterable<T>.map(transform: (T) -> ComposeFun) =
        map<T, ComposeFun>(transform).toTypedArray()

    @Composable
    private fun AnalogClockCard(now: Instant, labeledTimeZone: LabeledTimeZone) = Card {
        val (timeZone, label) = labeledTimeZone
        val zone = remember(now, timeZone) { TimeZoneData(label, timeZone.offsetStringAt(now)) }
        val (date, time) = remember(now) { now.toLocalDateTime(timeZone).run { date to time } }
        val dateString = remember(date) { date.toString() }
        val timeString = remember(time) { timeFormat.format(time) }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(8.dp)
                .semantics { contentDescription = "Clock - ${zone.name}" }
        ) {
            AnalogClock(date, time, zone, tag = zone.name)
            Title(zone.name)
            Date(dateString)
            Time(timeString)
            UtcOffset(zone.offset)
        }
    }

    @Composable
    private fun Title(text: String) = Text(
        text = text,
        style = typography.titleMedium,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
    )

    @Composable
    private fun Date(text: String) = Text(text, style = typography.bodyLarge)

    @Composable
    private fun Time(text: String) = Text(
        text = text,
        style = typography.titleSmall,
        fontFamily = FontFamily.Monospace,
    )

    @Composable
    private fun UtcOffset(text: String) = Text(
        text = text,
        style = typography.bodySmall,
        modifier = Modifier.semantics { contentDescription = "UTC Offset" }
    )

    @Composable
    private fun Code() = codes(
        Res.string.shared to {
            appendAnalogClockCard()
            appendTitle()
            appendDate()
            appendTime()
            appendUtcOffset()
        },
        Res.string.miscellaneous_date_time_analog_clock to { appendAnalogClock() },
        Res.string.miscellaneous_date_time_time_zone_data to { appendTimeZoneData() },
    )
}
