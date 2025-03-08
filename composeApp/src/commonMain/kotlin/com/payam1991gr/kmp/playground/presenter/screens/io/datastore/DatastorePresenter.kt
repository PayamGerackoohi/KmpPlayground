package com.payam1991gr.kmp.playground.presenter.screens.io.datastore

import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.module.toolbar.rememberToolbarState
import com.payam1991gr.kmp.playground.view.nothing
import com.payam1991gr.kmp.playground.view.screens.io.datastore.DatastoreScreen.State
import com.payam1991gr.kmp.playground.view.screens.io.datastore.DatastoreScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.io.datastore.rememberData
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter

class DatastorePresenter(
    private val navigator: Navigator,
    private val dataStore: DataStore<Preferences>,
) : Presenter<State> {
    @Composable
    override fun present(): State {
        val toolbarState = rememberToolbarState()
        val data = rememberData(dataStore)
        return State(
            showCode = toolbarState.showCode,
            toolbarActions = toolbarState.actions,
            int = data.int,
            float = data.float,
            byteArray = data.byteArray,
        ) { event ->
            when (event) {
                is Event.OnToolbarAction -> when (event.action) {
                    Action.Back -> navigator.pop()
                    Action.Code -> toolbarState.code()
                    Action.Preview -> toolbarState.preview()
                }

                is Event.OnItemChanged<*> -> when (val v = event.value) {
                    is Int -> data.update(v)
                    is Float -> data.update(v)
                    is ByteArray -> data.update(v)
                    else -> nothing()
                }
            }
        }
    }
}
