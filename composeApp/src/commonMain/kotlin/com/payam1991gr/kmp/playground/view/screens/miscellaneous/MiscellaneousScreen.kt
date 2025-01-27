package com.payam1991gr.kmp.playground.view.screens.miscellaneous

import com.payam1991gr.kmp.playground.data.model.MiscellaneousItem
import com.payam1991gr.kmp.playground.platform.KmpParcelize
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.PersistentList

@KmpParcelize
data object MiscellaneousScreen : Screen {
    data class State(
        val items: PersistentList<MiscellaneousItem>,
        val event: (Event) -> Unit,
    ) : CircuitUiState {
        sealed interface Event : CircuitUiEvent {
            data class OnClick(val item: MiscellaneousItem) : Event
            data object OnBackPressed : Event
        }
    }
}
