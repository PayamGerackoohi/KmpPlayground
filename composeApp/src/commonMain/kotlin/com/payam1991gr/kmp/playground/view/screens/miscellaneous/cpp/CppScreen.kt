package com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp

import com.payam1991gr.kmp.playground.data.math.Factor
import com.payam1991gr.kmp.playground.platform.KmpParcelize
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@KmpParcelize
data object CppScreen : Screen {
    data class State(
        val showCode: Boolean,
        val toolbarActions: PersistentList<Action>,
        val canEdit: Boolean,
        val canCalculate: Boolean,
        val inputNumber: String,
        val inputCalculation: Number,
        val calculations: PersistentList<Number>,
        val event: (Event) -> Unit,
    ) : CircuitUiState {
        sealed interface Event : CircuitUiEvent {
            data class OnToolbarAction(val action: Action) : Event
            data class OnNumberChanged(val number: String) : Event
            data object PerformRandomCalculations : Event
        }

        sealed interface Number {
            data class Calculating(val number: String) : Number
            data class Data(
                val number: String = "",
                val factors: PersistentList<Factor> = persistentListOf(),
            ) : Number
        }
    }
}
