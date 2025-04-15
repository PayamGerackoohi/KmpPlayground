package com.payam1991gr.kmp.playground.presenter.screens.graphics

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.data.model.GraphicItem
import com.payam1991gr.kmp.playground.view.screens.graphics.GraphicsScreen.State
import com.payam1991gr.kmp.playground.view.screens.graphics.charts.ChartsScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.ColorSchemeScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.opengl.OpenGlScreen
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.toPersistentList

class GraphicsPresenter(private val navigator: Navigator) : Presenter<State> {
    private val items = GraphicItem.entries.toPersistentList()

    @Composable
    override fun present(): State {
        return State(items = items) {
            when (it) {
                is State.Event.OnClick -> when (it.item) {
//                    GraphicItem.Charts -> navigator.goTo(ChartsScreen)
                    GraphicItem.ColorScheme -> navigator.goTo(ColorSchemeScreen)
                    GraphicItem.Icons -> navigator.goTo(IconsScreen)
//                    GraphicItem.OpenGL -> navigator.goTo(OpenGlScreen)
                }

                State.Event.OnBackPressed -> navigator.pop()
            }
        }
    }
}
