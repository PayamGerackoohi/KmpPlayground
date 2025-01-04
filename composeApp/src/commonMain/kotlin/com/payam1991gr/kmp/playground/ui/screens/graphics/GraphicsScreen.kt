package com.payam1991gr.kmp.playground.ui.screens.graphics

import com.payam1991gr.kmp.playground.data.model.GraphicItem
import com.payam1991gr.kmp.playground.platform.KmpParcelize
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.PersistentList

@KmpParcelize
data object GraphicsScreen : Screen {
    data class State(
        val items: PersistentList<GraphicItem>,
        val event: (Event) -> Unit,
    ) : CircuitUiState {
        sealed interface Event : CircuitUiEvent {
            data class OnClick(val item: GraphicItem) : Event
            data object OnBackPressed : Event
        }
    }
}
