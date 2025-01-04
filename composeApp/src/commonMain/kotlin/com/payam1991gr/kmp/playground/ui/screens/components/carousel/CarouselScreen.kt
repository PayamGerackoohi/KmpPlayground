package com.payam1991gr.kmp.playground.ui.screens.components.carousel

import com.payam1991gr.kmp.playground.platform.KmpParcelize
import com.payam1991gr.kmp.playground.ui.module.SamplePage
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.PersistentList

@KmpParcelize
data object CarouselScreen : Screen {
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
