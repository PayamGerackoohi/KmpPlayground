package com.payam1991gr.kmp.playground.ui.screens.io

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.data.model.IoItem
import com.payam1991gr.kmp.playground.ui.screens.io.IoScreen.State
import com.payam1991gr.kmp.playground.ui.screens.io.api.ApiScreen
import com.payam1991gr.kmp.playground.ui.screens.io.database.DatabaseScreen
import com.payam1991gr.kmp.playground.ui.screens.io.datastore.DatastoreScreen
import com.payam1991gr.kmp.playground.ui.screens.io.file.FileScreen
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.toPersistentList

class IoPresenter(private val navigator: Navigator) : Presenter<State> {
    private val items = IoItem.entries.toPersistentList()

    @Composable
    override fun present(): State {
        return State(items = items) {
            when (it) {
                is State.Event.OnClick -> when (it.item) {
                    IoItem.Datastore -> navigator.goTo(DatastoreScreen)
                    IoItem.Database -> navigator.goTo(DatabaseScreen)
                    IoItem.API -> navigator.goTo(ApiScreen)
                    IoItem.File -> navigator.goTo(FileScreen)
                }

                State.Event.OnBackPressed -> navigator.pop()
            }
        }
    }
}
