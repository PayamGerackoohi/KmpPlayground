package com.payam1991gr.kmp.playground.data.model.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.resources.StringResource

@Stable
class SettingItem(value: MutableState<Boolean>, val labelRes: StringResource) {
    var value by value

    fun toggle() {
        value = value.not()
    }
}

@Composable
fun rememberSettingItem(
    initialState: Boolean,
    labelOf: (Boolean) -> StringResource,
): State<SettingItem> {
    val state = remember { mutableStateOf(initialState) }
    val labelRes = remember(state.value) { labelOf(state.value) }
    return remember(state.value) { mutableStateOf(SettingItem(state, labelRes)) }
}
