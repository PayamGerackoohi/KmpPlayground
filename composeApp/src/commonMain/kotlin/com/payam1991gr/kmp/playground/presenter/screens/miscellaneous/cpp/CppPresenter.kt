package com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.cpp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.payam1991gr.kmp.playground.data.Native
import com.payam1991gr.kmp.playground.data.math.Factor
import com.payam1991gr.kmp.playground.data.string.RandomULongGenerator
import com.payam1991gr.kmp.playground.data.string.decimalFormat
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.toolbar.rememberToolbarState
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen.State
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen.State.Event
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class CppPresenter(
    private val navigator: Navigator,
    randomULongGenerator: RandomULongGenerator,
    private val calculationCount: Int = 10,
    private val bg: CoroutineContext = Dispatchers.Default,
) : Presenter<State>, RandomULongGenerator by randomULongGenerator {
    @Composable
    override fun present(): State {
        val toolbarState = rememberToolbarState()
        var canEdit by remember { mutableStateOf(true) }
        var canCalculate by remember { mutableStateOf(true) }
        var inputNumber by remember { mutableStateOf("") }
        var factors by remember { mutableStateOf<State.Number>(State.Number.Data()) }
        var calculations by remember { mutableStateOf(persistentListOf<State.Number>()) }
        val scope = rememberCoroutineScope()
        return State(
            showCode = toolbarState.showCode,
            toolbarActions = toolbarState.actions,
            canEdit = canEdit,
            canCalculate = canCalculate,
            inputNumber = inputNumber,
            inputCalculation = factors,
            calculations = calculations,
        ) {
            when (it) {
                is Event.OnToolbarAction -> when (it.action) {
                    SamplePage.Action.Back -> navigator.pop()
                    SamplePage.Action.Code -> toolbarState.code()
                    SamplePage.Action.Preview -> toolbarState.preview()
                }

                is Event.OnNumberChanged -> scope.launch(bg) {
                    canEdit = false
                    it.number.let { n ->
                        inputNumber = n
                        factors = State.Number.Calculating(n)
                        factors = State.Number.Data(n, Factor.parse(Native.primeFactors(n)))
                    }
                    canEdit = true
                }

                Event.PerformRandomCalculations -> scope.launch(bg) {
                    canCalculate = false
                    val list = mutableListOf<State.Number>()
                    calculations = list.toPersistentList()
                    repeat(calculationCount) { index ->
                        val n = randomNumber()
                        val formattedNumber = n.decimalFormat()
                        list.add(State.Number.Calculating(formattedNumber))
                        calculations = list.toPersistentList()
                        list[index] = State.Number.Data(
                            formattedNumber,
                            Factor.parse(Native.primeFactors(n)),
                        )
                        calculations = list.toPersistentList()
                    }
                    canCalculate = true
                }
            }
        }
    }
}
