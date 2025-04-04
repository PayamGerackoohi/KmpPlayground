package com.payam1991gr.kmp.playground.presenter.screens.home

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.data.model.HomeItem
import com.payam1991gr.kmp.playground.view.screens.animations.AnimationsScreen
import com.payam1991gr.kmp.playground.view.screens.components.ComponentsScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.GraphicsScreen
import com.payam1991gr.kmp.playground.view.screens.home.HomeScreen.State
import com.payam1991gr.kmp.playground.view.screens.io.IoScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.MiscellaneousScreen
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.toPersistentList

class HomePresenter(private val navigator: Navigator) : Presenter<State> {
    private val items = HomeItem.entries.toPersistentList()

    @Composable
    override fun present(): State {
        return State(items) {
            when (it) {
                is State.Event.OnItemClicked -> when (it.item) {
                    HomeItem.Components -> navigator.goTo(ComponentsScreen)
                    HomeItem.Animations -> navigator.goTo(AnimationsScreen)
                    HomeItem.Graphics -> navigator.goTo(GraphicsScreen)
                    HomeItem.Io -> navigator.goTo(IoScreen)
                    HomeItem.Miscellaneous -> navigator.goTo(MiscellaneousScreen)
                }
            }
        }
    }
}
