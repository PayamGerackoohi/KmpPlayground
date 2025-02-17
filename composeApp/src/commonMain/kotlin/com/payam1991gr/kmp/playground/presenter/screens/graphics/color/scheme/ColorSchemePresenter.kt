package com.payam1991gr.kmp.playground.presenter.screens.graphics.color.scheme

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.module.toolbar.rememberToolbarState
import com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.ColorSchemeScreen.State
import com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.ColorSchemeScreen.State.Event
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter

class ColorSchemePresenter(private val navigator: Navigator) : Presenter<State> {
    @Composable
    override fun present(): State {
        val toolbarState = rememberToolbarState()
        return State(toolbarState.showCode, toolbarState.actions) {
            when (it) {
                is Event.OnToolbarAction -> when (it.action) {
                    Action.Back -> navigator.pop()
                    Action.Code -> toolbarState.code()
                    Action.Preview -> toolbarState.preview()
                }
            }
        }
    }
}
