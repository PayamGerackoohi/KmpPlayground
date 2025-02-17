package com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme

import com.payam1991gr.kmp.playground.platform.KmpParcelize
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.PersistentList

@KmpParcelize
data object ColorSchemeScreen : Screen {
    data class State(
        val showCode: Boolean,
        val toolbarActions: PersistentList<SamplePage.Action>,
        val event: (Event) -> Unit,
    ) : CircuitUiState {
        sealed interface Event : CircuitUiEvent {
            data class OnToolbarAction(val action: SamplePage.Action) : Event
        }
    }
}
