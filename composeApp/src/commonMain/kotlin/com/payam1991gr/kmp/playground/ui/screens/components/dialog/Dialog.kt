package com.payam1991gr.kmp.playground.ui.screens.components.dialog

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TimePicker
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.payam1991gr.kmp.playground.data.box
import com.payam1991gr.kmp.playground.data.model.Time
import com.payam1991gr.kmp.playground.data.model.sample.appendTime
import com.payam1991gr.kmp.playground.data.model.sample.appendTimePickerState_time
import com.payam1991gr.kmp.playground.data.model.sample.rememberSetting
import com.payam1991gr.kmp.playground.data.model.time
import com.payam1991gr.kmp.playground.data.time.format
import com.payam1991gr.kmp.playground.data.time.sample.appendTime_format
import com.payam1991gr.kmp.playground.ui.LocalTestMode
import com.payam1991gr.kmp.playground.ui.module.SamplePage
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Preview.ContentList
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Preview.Description
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Preview.Header
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Preview.Settings
import com.payam1991gr.kmp.playground.ui.module.SamplePage.preview
import com.payam1991gr.kmp.playground.ui.module.editor.CodePanel
import com.payam1991gr.kmp.playground.ui.module.editor.rememberCodeEditor
import com.payam1991gr.kmp.playground.ui.sample.appendBox
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogScreen.State
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogScreen.State.Event
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.sample.appendBasicAlertDialogSample
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.sample.appendDatePickerDialogSample
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.sample.appendHasDate
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.sample.appendResources
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.sample.appendTimePickerDialog
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.sample.appendTimePickerDialogSample
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.DateTimePickerScreen.State.DatePicker
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

class Dialog : Ui<State> {
    private val timestampStringRes = Res.string.components_date_picker_timestamp_template
    private val dateStringRes = Res.string.components_date_picker_date_template

    @Composable
    override fun Content(state: State, modifier: Modifier) {
        SamplePage(
            showCode = state.showCode,
            titleRes = Res.string.components_dialog,
            actions = state.toolbarActions,
            onClick = { state.event(Event.OnToolbarAction(it)) },
            preview = { Preview(state) },
            code = { Code() },
        )
    }

    @Composable
    fun Preview(state: State) = preview(
        { Header("Basic Alert Dialog") },
        { Description(stringResource(Res.string.alert_dialog)) },
        { BasicAlertDialogSample() },

        { Header("Date Picker Dialog") },
        { Description(stringResource(Res.string.date_picker_dialog)) },
        { DatePickerDialogSample(state.datePicker) },

        { Header("Time Picker Dialog") },
        { Description(stringResource(Res.string.components_time_picker_description)) },
        { TimePickerDialogSample() },
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun BasicAlertDialogSample() = ContentList {
        val animationDuration = if (LocalTestMode.current) 0 else 300
        var showDialog by remember { mutableStateOf(false) }
        var animateDialog by remember { mutableStateOf(false) }
        val animationSpec = tween<Float>(animationDuration)
        val alpha by animateFloatAsState(
            if (animateDialog) 1f else 0f,
            animationSpec = animationSpec,
        )
        val scope = rememberCoroutineScope()
        val shouldAnimate = rememberSetting {
            if (it) Res.string.animate else Res.string.instant
        }
        val isDismissible = rememberSetting {
            if (it) Res.string.dismissible else Res.string.mandatory
        }
        Settings(isDismissible, shouldAnimate)

        fun onClose(): Any = if (shouldAnimate.value) scope.launch {
            animateDialog = false
            delay(animationDuration.toLong())
            showDialog = false
        } else showDialog = false

        Button(
            onClick = {
                showDialog = true
                if (shouldAnimate.value) scope.launch {
                    delay(animationDuration.toLong())
                    animateDialog = true
                }
            },
            modifier = Modifier.testTag("BasicAlertDialog.Show")
        ) { Text(stringResource(Res.string.show)) }
        if (showDialog) BasicAlertDialog(
            onDismissRequest = { if (isDismissible.value) onClose() },
        ) {
            Surface(
                shape = MaterialTheme.shapes.large,
                tonalElevation = AlertDialogDefaults.TonalElevation,
                modifier = Modifier
                    .wrapContentWidth()
                    .wrapContentHeight().run { if (shouldAnimate.value) alpha(alpha) else this }
                    .testTag("BasicAlertDialogSample")
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "This area typically contains the supportive text " +
                                "which presents the details regarding the Dialog's purpose."
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    TextButton(
                        onClick = { onClose() },
                        modifier = Modifier.align(Alignment.End),
                    ) { Text(stringResource(Res.string.confirm)) }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun DatePickerDialogSample(state: DatePicker) = ContentList {
        var showDialog by remember { mutableStateOf(false) }
        state.run {
            rememberDatePickerState(
                initialDisplayedMonthMillis = initialDisplayDate,
                initialSelectedDateMillis = date.ms,
            )
        }.apply {
            state.date.apply {
                Text(stringResource(timestampStringRes, ms.box()))
                Text(stringResource(dateStringRes, text.box()))
            }
            Button(
                onClick = { showDialog = true },
                modifier = Modifier.testTag("DatePickerDialog.Show")
            ) {
                Text(stringResource(Res.string.show))
            }
            if (showDialog) {
                val confirmEnabled = remember { derivedStateOf { hasDate } }
                DatePickerDialog(
                    onDismissRequest = { showDialog = false },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showDialog = false
                                selectedDateMillis?.let { ms -> state.date.ms = ms }
                            },
                            enabled = confirmEnabled.value,
                        ) { Text(stringResource(Res.string.ok)) }
                    },
                    dismissButton = {
                        TextButton({ showDialog = false }) {
                            Text(stringResource(Res.string.cancel))
                        }
                    },
                ) {
                    DatePicker(this@apply)
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TimePickerDialogSample() = ContentList {
        var time by remember { mutableStateOf(Time()) }
        var showTimePicker by remember { mutableStateOf(false) }

        rememberTimePickerState(
            initialHour = time.hour,
            initialMinute = time.minute,
        ).apply {
            Text(
                text = time.format(),
                modifier = Modifier.testTag("TimePicker.Text")
            )
            Button(
                onClick = { showTimePicker = true },
                modifier = Modifier.testTag("TimePicker.SetTime")
            ) {
                Text(stringResource(Res.string.time_picker_dialog_set_time))
            }

            if (showTimePicker) TimePickerDialog(
                title = stringResource(Res.string.time_picker_dialog_select_time),
                onCancel = { showTimePicker = false },
                onConfirm = {
                    time = time()
                    showTimePicker = false
                },
            ) { TimePicker(this) }
        }
    }

    @Composable
    fun TimePickerDialog(
        title: String,
        onCancel: () -> Unit,
        onConfirm: () -> Unit,
        content: @Composable () -> Unit,
    ) {
        Dialog(
            onDismissRequest = onCancel,
            properties = DialogProperties(usePlatformDefaultWidth = false),
        ) {
            Surface(
                shape = MaterialTheme.shapes.extraLarge,
                tonalElevation = 8.dp,
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min)
                    .background(
                        shape = MaterialTheme.shapes.extraLarge,
                        color = MaterialTheme.colorScheme.surface,
                    ),
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.labelMedium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                    content()
                    Row(Modifier.fillMaxWidth()) {
                        Spacer(Modifier.weight(1f))
                        TextButton(onCancel) { Text(stringResource(Res.string.cancel)) }
                        TextButton(onConfirm) { Text(stringResource(Res.string.ok)) }
                    }
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private val DatePickerState.hasDate get() = selectedDateMillis != null

    @Composable
    fun Code() = CodePanel(rememberCodeEditor {
        appendResources()

        appendBasicAlertDialogSample()
        appendDatePickerDialogSample()
        appendTimePickerDialogSample()

        appendTimePickerDialog()
        appendHasDate()
        appendBox()
        appendTime()
        appendTime_format()
        appendTimePickerState_time()
    })
}
