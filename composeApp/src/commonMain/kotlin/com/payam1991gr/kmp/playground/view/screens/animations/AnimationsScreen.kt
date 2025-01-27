package com.payam1991gr.kmp.playground.view.screens.animations

import com.payam1991gr.kmp.playground.data.model.AnimationItem
import com.payam1991gr.kmp.playground.platform.KmpParcelize
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.PersistentList

@KmpParcelize
data object AnimationsScreen : Screen {
    data class State(
        val items: PersistentList<AnimationItem>,
        val event: (Event) -> Unit,
    ) : CircuitUiState {
        sealed interface Event : CircuitUiEvent {
            data class OnClick(val item: AnimationItem) : Event
            data object OnBackPressed : Event
        }
    }
}
