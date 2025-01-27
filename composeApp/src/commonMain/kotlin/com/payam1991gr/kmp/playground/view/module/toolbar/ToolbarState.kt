package com.payam1991gr.kmp.playground.view.module.toolbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Stable
class ToolbarState(
    showCode: MutableState<Boolean>,
    val actions: PersistentList<Action>,
) {
    var showCode by showCode
        private set

    fun code() {
        showCode = true
    }

    fun preview() {
        showCode = false
    }
}

@Composable
fun rememberToolbarState(): ToolbarState {
    val showCode = remember { mutableStateOf(false) }
    val actions = remember(showCode.value) {
        persistentListOf(
            Action.Back,
            if (showCode.value) Action.Preview else Action.Code,
        )
    }
    return remember(showCode.value) { ToolbarState(showCode, actions) }
}
