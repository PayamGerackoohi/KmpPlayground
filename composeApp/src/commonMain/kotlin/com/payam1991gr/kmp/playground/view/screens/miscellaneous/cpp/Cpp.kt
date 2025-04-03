package com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.data.model.textfield.Converter
import com.payam1991gr.kmp.playground.data.model.textfield.Data
import com.payam1991gr.kmp.playground.data.model.textfield.buildData
import com.payam1991gr.kmp.playground.data.string.ScientificFormat.toScientific
import com.payam1991gr.kmp.playground.data.string.decimalFormat
import com.payam1991gr.kmp.playground.data.string.decimalFormatTransformation
import com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.cpp.sample.*
import com.payam1991gr.kmp.playground.view.module.MathFactors
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.SamplePage.preview
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.ContentList
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Description
import com.payam1991gr.kmp.playground.view.module.TextFieldInput
import com.payam1991gr.kmp.playground.view.module.editor.codes
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen.State
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.sample.*
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

class Cpp : Ui<State> {
    private val converter = object : Converter<String> {
        override fun from(data: String): String = data
        override fun String.toData(): Data<String> = buildData {
            trim().toULongOrNull()?.let { this@toData } ?: error() ?: ""
        }
    }

    @Composable
    override fun Content(state: State, modifier: Modifier) = SamplePage(
        showCode = state.showCode,
        titleRes = Res.string.miscellaneous_cpp,
        actions = state.toolbarActions,
        onClick = { state.event(Event.OnToolbarAction(it)) },
        preview = { state.Preview() },
        code = { Code() },
    )

    @Composable
    fun State.Preview() = preview(
        { SelectionContainer { Description(stringResource(Res.string.miscellaneous_cpp_description)) } },
        { NumberInput() },
        { RandomCalculations() },
    )

    @Composable
    private fun State.NumberInput(placeholder: String = "18446744073709551615".decimalFormat()) {
        var suffix by remember(inputNumber) {
            mutableStateOf(inputNumber.ifEmpty { placeholder }.toScientific())
        }
        TextFieldInput(
            tag = "Number",
            title = when (inputCalculation) {
                is State.Number.Calculating -> stringResource(Res.string.miscellaneous_cpp_calculating)
                is State.Number.Data -> MathFactors.annotatedString(inputCalculation.factors)
            },
            initialData = inputNumber,
            converter = converter,
            onTextChanged = { text ->
                text.filter { it.isDigit() }.apply {
                    suffix = ifEmpty { placeholder }.toScientific()
                }
            },
            confirmButtonLabel = stringResource(Res.string.miscellaneous_cpp_calculate),
            visualTransformation = decimalFormatTransformation,
            canEdit = canEdit,
            label = { Text(stringResource(Res.string.miscellaneous_cpp_number)) },
            placeholder = { Text(placeholder, maxLines = 1, overflow = TextOverflow.Clip) },
            suffix = { Text(suffix) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        ) { event(Event.OnNumberChanged(this)) }
    }

    @Composable
    private fun State.RandomCalculations() = ContentList(Modifier.testTag("Random Calculations")) {
        Button(
            onClick = { event(Event.PerformRandomCalculations) },
            enabled = canCalculate,
        ) { Text(stringResource(Res.string.miscellaneous_cpp_random_calculations)) }
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
            calculations.forEach { it.CalculationCard() }
        }
    }

    @Composable
    fun State.Number.CalculationCard() = Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor()),
        modifier = Modifier
            .fillMaxWidth()
            .stateOf(this)
    ) {
        Crossfade(this@CalculationCard) {
            SelectionContainer(Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
                when (it) {
                    is State.Number.Calculating -> Text("${it.number} = …")
                    is State.Number.Data -> MathFactors(it.factors, prefix = "${it.number} = ")
                }
            }
        }
    }

    private fun Modifier.stateOf(number: State.Number) = semantics(mergeDescendants = true) {
        stateDescription = when (number) {
            is State.Number.Calculating -> "Calculating"
            is State.Number.Data -> "Calculated"
        }
    }

    @Composable
    fun State.Number.cardColor() = when (this) {
        is State.Number.Calculating -> MaterialTheme.colorScheme.tertiaryContainer
        is State.Number.Data -> MaterialTheme.colorScheme.secondaryContainer
    }

    @Composable
    fun Code() = codes(
        Res.string.ui to {
            appendConverter()
            appendDecimalFormatTransformation()
            appendNumberInput()
            appendRandomCalculations()
            appendCalculationCard()
            appendStateOf()
            appendCardColor()
        },
        Res.string.state to { appendState() },
        Res.string.presenter to { appendPresenter() },
        Res.string.miscellaneous_cpp to { appendCpp() },
    )
}
