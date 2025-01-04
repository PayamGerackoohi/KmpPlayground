package com.payam1991gr.kmp.playground.ui.module.toolbar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import kotlinx.collections.immutable.toPersistentList

@Stable
class ToolbarState(
    showCode: MutableState<Boolean>,
    actions: SnapshotStateList<Action>,
) {
    var showCode by showCode
        private set
    val actions = actions.toPersistentList()

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
        mutableStateListOf(
            Action.Back,
            if (showCode.value) Action.Preview else Action.Code,
        )
    }
    return remember(showCode.value) { ToolbarState(showCode, actions) }
}
