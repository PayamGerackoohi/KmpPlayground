package com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.runtime.Stable
import com.payam1991gr.kmp.playground.platform.KmpParcelize
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.PersistentList

@KmpParcelize
data object PullToRefreshScreen : Screen {
    data class State(
        val showCode: Boolean,
        val toolbarActions: PersistentList<Action>,
        val linearProgressIndicator: PtrState,
        val pullToRefreshBox: PtrState,
        val scaling: PtrState,
        val customBehavior: PtrState,
        val event: (Event) -> Unit,
    ) : CircuitUiState {
        sealed interface Event : CircuitUiEvent {
            data class OnToolbarAction(val action: Action) : Event
        }

        @OptIn(ExperimentalMaterial3Api::class)
        @Stable
        interface PtrState {
            val state: PullToRefreshState
            var isRefreshing: Boolean
            var itemCount: Int
            fun onRefresh()
        }
    }
}
