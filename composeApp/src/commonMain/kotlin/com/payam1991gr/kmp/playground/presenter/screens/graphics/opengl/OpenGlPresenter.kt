package com.payam1991gr.kmp.playground.presenter.screens.graphics.opengl

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.toolbar.rememberToolbarState
import com.payam1991gr.kmp.playground.view.screens.graphics.opengl.OpenGlScreen.State
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter

class OpenGlPresenter(private val navigator: Navigator) : Presenter<State> {
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
