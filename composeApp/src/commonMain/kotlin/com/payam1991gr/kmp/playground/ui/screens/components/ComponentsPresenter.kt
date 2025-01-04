package com.payam1991gr.kmp.playground.ui.screens.components

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.data.model.Component
import com.payam1991gr.kmp.playground.ui.screens.components.ComponentsScreen.State
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogScreen
import com.payam1991gr.kmp.playground.ui.screens.components.carousel.CarouselScreen
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.DateTimePickerScreen
import com.payam1991gr.kmp.playground.ui.screens.components.pull.to.refresh.PullToRefreshScreen
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.toPersistentList

class ComponentsPresenter(private val navigator: Navigator) : Presenter<State> {
    private val components = Component.entries.toPersistentList()

    @Composable
    override fun present(): State {
        return State(components = components) {
            when (it) {
                is State.Event.OnClick -> when (it.component) {
                    Component.Carousel -> navigator.goTo(CarouselScreen)
                    Component.PullToRefresh -> navigator.goTo(PullToRefreshScreen)
                    Component.Dialog -> navigator.goTo(DialogScreen)
                    Component.DateTimePicker -> navigator.goTo(DateTimePickerScreen)
                }

                State.Event.OnBackPressed -> navigator.pop()
            }
        }
    }
}
