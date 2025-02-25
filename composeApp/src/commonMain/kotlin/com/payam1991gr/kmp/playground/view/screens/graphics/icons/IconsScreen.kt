package com.payam1991gr.kmp.playground.view.screens.graphics.icons

import androidx.compose.runtime.MutableState
import com.payam1991gr.kmp.playground.data.model.IconData
import com.payam1991gr.kmp.playground.data.model.LazyData
import com.payam1991gr.kmp.playground.platform.KmpParcelize
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.PersistentList

@KmpParcelize
data object IconsScreen : Screen {
    data class State(
        val showCode: Boolean,
        val toolbarActions: PersistentList<SamplePage.Action>,
        val coreIcons: PersistentList<IconData>,
        val extendedIcons: LazyData<PersistentList<IconData>>,
        val effect: MutableState<Effect?>,
        val event: (Event) -> Unit,
    ) : CircuitUiState {
        sealed interface Event : CircuitUiEvent {
            data class OnToolbarAction(val action: SamplePage.Action) : Event
            data class OnIconClicked(val icon: IconData) : Event
        }

        sealed interface Effect {
            data class ShowMessage(val text: String) : Effect
        }
    }
}
