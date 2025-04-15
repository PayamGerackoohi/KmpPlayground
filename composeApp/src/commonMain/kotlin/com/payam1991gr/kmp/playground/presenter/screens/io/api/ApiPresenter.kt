package com.payam1991gr.kmp.playground.presenter.screens.io.api

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.payam1991gr.kmp.playground.data.model.WrittenNumber
import com.payam1991gr.kmp.playground.data.model.RemoteData
import com.payam1991gr.kmp.playground.data.model.RemoteData.Init
import com.payam1991gr.kmp.playground.data.remote.Api
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.toolbar.rememberToolbarState
import com.payam1991gr.kmp.playground.view.screens.io.api.ApiScreen.State
import com.payam1991gr.kmp.playground.view.screens.io.api.ApiScreen.State.Event
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

typealias Numbers = List<WrittenNumber>

class ApiPresenter(
    private val navigator: Navigator,
    private val realApi: Api,
    private val fakeApi: Api,
) : Presenter<State> {
    private var shouldUseRealApi by mutableStateOf(true)
    private val api: Api get() = if (shouldUseRealApi) realApi else fakeApi
    private val host get() = api.host
    private var home by mutableStateOf<RemoteData<String>>(Init)
    private var writtenNumbers by mutableStateOf<RemoteData<Numbers>>(Init)

    @Composable
    override fun present(): State {
        val toolbarState = rememberToolbarState()
        val scope = rememberCoroutineScope()
        return State(
            toolbarState.showCode,
            toolbarState.actions,
            shouldUseRealApi,
            host,
            home,
            writtenNumbers,
        ) { event ->
            when (event) {
                is Event.OnHostChanged -> api.host = event.host
                is Event.OnServerModeChanged -> shouldUseRealApi = event.shouldUseRealApi
                is Event.OnToolbarAction -> when (event.action) {
                    SamplePage.Action.Back -> navigator.pop()
                    SamplePage.Action.Code -> toolbarState.code()
                    SamplePage.Action.Preview -> toolbarState.preview()
                }

                Event.CallHomeApi -> scope.launch {
                    api.home().collectLatest { home = it }
                }

                is Event.CallWrittenNumbersApi -> scope.launch {
                    api.writtenNumbers(event.from, event.to).collectLatest { writtenNumbers = it }
                }
            }
        }
    }
}
