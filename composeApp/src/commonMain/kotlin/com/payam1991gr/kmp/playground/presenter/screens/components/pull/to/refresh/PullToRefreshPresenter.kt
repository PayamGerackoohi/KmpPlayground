package com.payam1991gr.kmp.playground.presenter.screens.components.pull.to.refresh

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.toolbar.rememberToolbarState
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefreshScreen.State
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.state.rememberPtrState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.CoroutineScope

class PullToRefreshPresenter(
    private val navigator: Navigator,
    private val scope: CoroutineScope? = null,
) : Presenter<State> {
    @Composable
    override fun present(): State {
        val toolbarState = rememberToolbarState()
        return State(
            showCode = toolbarState.showCode,
            toolbarActions = toolbarState.actions,
            linearProgressIndicator = prtState(),
            pullToRefreshBox = prtState(),
            scaling = prtState(),
            customBehavior = prtState(),
        ) {
            when (it) {
                is State.Event.OnToolbarAction -> when (it.action) {
                    SamplePage.Action.Back -> navigator.pop()
                    SamplePage.Action.Code -> toolbarState.code()
                    SamplePage.Action.Preview -> toolbarState.preview()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun prtState() = rememberPtrState(
        coroutineScope = scope ?: rememberCoroutineScope(),
    )
}
