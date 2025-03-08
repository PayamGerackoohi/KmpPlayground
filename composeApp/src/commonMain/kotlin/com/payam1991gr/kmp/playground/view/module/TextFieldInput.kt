package com.payam1991gr.kmp.playground.view.module

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.payam1991gr.kmp.playground.data.merge
import com.payam1991gr.kmp.playground.data.model.textfield.Converter
import com.payam1991gr.kmp.playground.data.model.textfield.Data
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Header
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.save
import org.jetbrains.compose.resources.stringResource

/**
 * A generic data input with save button and title
 * @param title the title of the item
 * @param initialData the initial saved data
 * @param converter a two way data-text converter
 * @param onTextChanged the text changed callback, especially for character allowance
 * @param canSave whether the save button should be enabled or not
 * @param visualTransformation the standard [TextField] [VisualTransformation]
 * @param onSave the save button click callback
 */
@Composable
fun <T> TextFieldInput(
    title: String,
    initialData: T,
    converter: Converter<T>,
    modifier: Modifier = Modifier,
    tag: String = "",
    onTextChanged: (String) -> String = { it },
    canSave: Data<T>.() -> Boolean = { !(hasError || value == initialData) },
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onSave: T.() -> Unit,
) {
    var value by remember(initialData) { mutableStateOf(converter.from(initialData)) }
    val data = remember(value) { converter.run { value.toData() } }
    val localTag = "TextFieldInput" merge tag
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        Header(title)
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
                modifier = Modifier
                    .weight(1f)
                    .testTag(localTag)
            )
            Button(
                { onSave(data.value) },
                enabled = data.canSave(),
                modifier = Modifier.testTag(localTag merge "SaveButton")
            ) {
                Text(stringResource(Res.string.save))
            }
        }
    }
}
