package com.payam1991gr.kmp.playground.view.module

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.data.string.merge
import com.payam1991gr.kmp.playground.data.model.textfield.Converter
import com.payam1991gr.kmp.playground.data.model.textfield.Data
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Header
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.save
import org.jetbrains.compose.resources.stringResource

/**
 * A generic data input with confirm button and title
 * @param title the title of the item
 * @param initialData the initial confirmed data
 * @param converter a two way data-text converter
 * @param modifier the module modifier
 * @param tag it's used for test tag
 * @param onTextChanged the text changed callback, especially for character allowance
 * @param canEdit whether the text field is editable or not
 * @param canConfirm whether the confirm button should be enabled or not
 * @param visualTransformation the standard [TextField] [VisualTransformation]
 * @param confirmButtonLabel confirm button label
 * @param label label of the text field, positioned on the top
 * @param placeholder the placeholder of the text field
 * @param suffix the suffix of the text field, positioned on the end
 * @param keyboardOptions keyboardOptions of the text field
 * @param onConfirm the confirm button click callback
 */
@Composable
fun <T> TextFieldInput(
    title: CharSequence,
    initialData: T,
    converter: Converter<T>,
    modifier: Modifier = Modifier,
    tag: String = "",
    onTextChanged: (String) -> String = { it },
    canEdit: Boolean = true,
    canConfirm: Data<T>.() -> Boolean = { !(hasError || value == initialData) },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    confirmButtonLabel: String = stringResource(Res.string.save),
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    suffix: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onConfirm: T.() -> Unit,
) {
    var value by remember(initialData) { mutableStateOf(converter.from(initialData)) }
    val data = remember(value) { converter.run { value.toData() } }
    val localTag = "TextFieldInput" merge tag
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        SelectionContainer { Header(title, Modifier.testTag(localTag merge "Header")) }
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            TextField(
                value = value,
                onValueChange = { value = onTextChanged(it) },
                singleLine = true,
                visualTransformation = visualTransformation,
                isError = data.hasError,
                colors = TextFieldDefaults.colors(
                    errorContainerColor = MaterialTheme.colorScheme.errorContainer,
                ),
                enabled = canEdit,
                label = label,
                placeholder = placeholder,
                suffix = suffix,
                keyboardOptions = keyboardOptions,
                modifier = Modifier
                    .weight(1f)
                    .testTag(localTag)
            )
            Button(
                { onConfirm(data.value) },
                enabled = data.canConfirm(),
                modifier = Modifier.testTag(localTag merge "ConfirmButton")
            ) {
                Text(confirmButtonLabel)
            }
        }
    }
}
