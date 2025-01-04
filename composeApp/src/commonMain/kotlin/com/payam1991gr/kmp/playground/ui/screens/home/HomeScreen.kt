package com.payam1991gr.kmp.playground.ui.screens.home

import com.payam1991gr.kmp.playground.data.model.HomeItem
import com.payam1991gr.kmp.playground.platform.KmpParcelize
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.PersistentList

@KmpParcelize
data object HomeScreen : Screen {
    data class State(
        val items: PersistentList<HomeItem>,
        val event: (Event) -> Unit,
    ) : CircuitUiState {
        sealed interface Event : CircuitUiEvent {
            data class OnItemClicked(val item: HomeItem) : Event
        }
    }
}
