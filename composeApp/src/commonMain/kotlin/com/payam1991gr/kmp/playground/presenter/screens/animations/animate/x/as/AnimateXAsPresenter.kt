package com.payam1991gr.kmp.playground.presenter.screens.animations.animate.x.`as`

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.toolbar.rememberToolbarState
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.AnimateXAsScreen.State
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter

class AnimateXAsPresenter(private val navigator: Navigator) : Presenter<State> {
    @Composable
    override fun present(): State = rememberToolbarState().run {
        return State(showCode, actions) {
            when (it) {
                is State.Event.OnToolbarAction -> when (it.action) {
                    SamplePage.Action.Back -> navigator.pop()
                    SamplePage.Action.Code -> code()
                    SamplePage.Action.Preview -> preview()
                }
            }
        }
    }
}
