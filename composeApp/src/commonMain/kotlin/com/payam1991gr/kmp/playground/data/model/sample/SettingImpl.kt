package com.payam1991gr.kmp.playground.data.model.sample

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import org.jetbrains.compose.resources.StringResource

@Stable
class SettingImpl(
    initialValue: Boolean,
    private val labelOf: (Boolean) -> StringResource,
) : Setting {
    override var value: Boolean by mutableStateOf(initialValue)
    override val labelRes get() = labelOf(value)
    override fun toggle() {
        value = !value
    }
}

@Composable
fun rememberSetting(
    initialValue: Boolean = true,
    labelOf: (Boolean) -> StringResource,
): Setting = remember { SettingImpl(initialValue, labelOf) }
