package com.payam1991gr.kmp.playground.view.screens.io.api

import com.payam1991gr.kmp.playground.data.model.RemoteData
import com.payam1991gr.kmp.playground.platform.KmpParcelize
import com.payam1991gr.kmp.playground.presenter.screens.io.api.Numbers
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.PersistentList

@KmpParcelize
data object ApiScreen : Screen {
    data class State(
        val showCode: Boolean,
        val toolbarActions: PersistentList<Action>,
        val shouldUseRealApi: Boolean,
        val host: String,
        val home: RemoteData<String>,
        val writtenNumbers: RemoteData<Numbers>,
        val event: (Event) -> Unit,
    ) : CircuitUiState {
        sealed interface Event : CircuitUiEvent {
            data class OnToolbarAction(val action: Action) : Event
            data class OnHostChanged(val host: String) : Event
            data class OnServerModeChanged(val shouldUseRealApi: Boolean) : Event
            data object CallHomeApi : Event
            data class CallWrittenNumbersApi(val from: Int?, val to: Int?) : Event
        }
    }
}
