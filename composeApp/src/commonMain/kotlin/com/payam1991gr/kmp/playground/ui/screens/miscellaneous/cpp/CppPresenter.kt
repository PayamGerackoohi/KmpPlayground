package com.payam1991gr.kmp.playground.ui.screens.miscellaneous.cpp

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.ui.module.SamplePage
import com.payam1991gr.kmp.playground.ui.module.toolbar.rememberToolbarState
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.cpp.CppScreen.State
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter

class CppPresenter(private val navigator: Navigator) : Presenter<State> {
    @Composable
    override fun present(): State {
        val toolbarState = rememberToolbarState()
        return State(toolbarState.showCode, toolbarState.actions) {
            when (it) {
                is State.Event.OnToolbarAction -> when (it.action) {
                    SamplePage.Action.Back -> navigator.pop()
                    SamplePage.Action.Code -> toolbarState.code()
                    SamplePage.Action.Preview -> toolbarState.preview()
                }
            }
        }
    }
}
