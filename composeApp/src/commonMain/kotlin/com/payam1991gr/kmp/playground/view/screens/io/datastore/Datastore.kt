package com.payam1991gr.kmp.playground.view.screens.io.datastore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import com.payam1991gr.kmp.playground.data.string.isDecimal
import com.payam1991gr.kmp.playground.data.string.isDecimalArray
import com.payam1991gr.kmp.playground.data.string.isFloat
import com.payam1991gr.kmp.playground.data.model.sample.appendConverters
import com.payam1991gr.kmp.playground.data.model.textfield.ByteArrayConverter
import com.payam1991gr.kmp.playground.data.model.textfield.FloatConverter
import com.payam1991gr.kmp.playground.data.model.textfield.IntConverter
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.SamplePage.preview
import com.payam1991gr.kmp.playground.view.module.TextFieldInput
import com.payam1991gr.kmp.playground.view.module.editor.CodePanel
import com.payam1991gr.kmp.playground.view.module.editor.rememberCodeEditor
import com.payam1991gr.kmp.playground.view.module.sample.appendTextFieldInput
import com.payam1991gr.kmp.playground.view.screens.io.datastore.DatastoreScreen.State
import com.payam1991gr.kmp.playground.view.screens.io.datastore.DatastoreScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.io.datastore.sample.appendByteArrayItem
import com.payam1991gr.kmp.playground.view.screens.io.datastore.sample.appendFloatItem
import com.payam1991gr.kmp.playground.view.screens.io.datastore.sample.appendIntItem
import com.payam1991gr.kmp.playground.view.screens.io.datastore.sample.appendState
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

class Datastore : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) = SamplePage(
        showCode = state.showCode,
        titleRes = Res.string.io_datastore,
        actions = state.toolbarActions,
        onClick = { state.event(Event.OnToolbarAction(it)) },
        preview = { state.Preview() },
        code = { Code() },
    )

    @Composable
    fun State.Preview() = preview(
        { IntItem() },
        { FloatItem() },
        { ByteArrayItem() },
    )

    @Composable
    fun State.IntItem() = TextFieldInput(
        tag = "Int",
        title = stringResource(Res.string.io_datastore_int_template, int),
        initialData = int,
        converter = remember { IntConverter() },
        onTextChanged = { text -> text.filter { it.isDecimal() } },
    ) { event(Event.OnItemChanged(this)) }

    @Composable
    fun State.FloatItem() = TextFieldInput(
        tag = "Float",
        title = stringResource(Res.string.io_datastore_float_template, float),
        initialData = float,
        converter = remember { FloatConverter() },
        onTextChanged = { text -> text.filter { it.isFloat() } },
    ) { event(Event.OnItemChanged(this)) }

    @Composable
    fun State.ByteArrayItem() = TextFieldInput(
        tag = "ByteArray",
        title = stringResource(
            Res.string.io_datastore_byte_array_template,
            byteArray.joinToString(",", prefix = "[", postfix = "]"),
        ),
        initialData = byteArray,
        converter = remember { ByteArrayConverter() },
        onTextChanged = { text -> text.filter { it.isDecimalArray() } },
        canConfirm = { !(hasError || value contentEquals byteArray) },
        visualTransformation = { text ->
            TransformedText(AnnotatedString("[$text]"), object : OffsetMapping {
                override fun originalToTransformed(offset: Int): Int = offset + 1
                override fun transformedToOriginal(offset: Int): Int = when (offset) {
                    in 1..<text.length -> offset - 1
                    else -> 0
                }
            })
        },
    ) { event(Event.OnItemChanged(this)) }

    @Composable
    fun Code() = CodePanel(rememberCodeEditor {
        appendIntItem()
        appendFloatItem()
        appendByteArrayItem()
        appendTextFieldInput()
        appendConverters()
        appendState()
    })
}
