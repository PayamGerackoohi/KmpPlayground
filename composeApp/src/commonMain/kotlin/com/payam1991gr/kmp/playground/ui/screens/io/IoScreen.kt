package com.payam1991gr.kmp.playground.ui.screens.io

import com.payam1991gr.kmp.playground.data.model.IoItem
import com.payam1991gr.kmp.playground.platform.KmpParcelize
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.PersistentList

@KmpParcelize
data object IoScreen : Screen {
    data class State(
        val items: PersistentList<IoItem>,
        val event: (Event) -> Unit,
    ) : CircuitUiState {
        sealed interface Event : CircuitUiEvent {
            data class OnClick(val item: IoItem) : Event
            data object OnBackPressed : Event
        }
    }
}
