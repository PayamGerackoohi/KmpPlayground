package com.payam1991gr.kmp.playground.ui.screens.components

import com.payam1991gr.kmp.playground.data.model.Component
import com.payam1991gr.kmp.playground.platform.KmpParcelize
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.PersistentList

@KmpParcelize
data object ComponentsScreen : Screen {
    data class State(
        val components: PersistentList<Component>,
        val event: (Event) -> Unit,
    ) : CircuitUiState {
        sealed interface Event {
            data class OnClick(val component: Component) : Event
            data object OnBackPressed : Event
        }
    }
}
