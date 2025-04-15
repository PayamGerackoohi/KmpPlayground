package com.payam1991gr.kmp.playground.presenter.screens.graphics

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.model.GraphicItem
import com.payam1991gr.kmp.playground.presenter.screens.graphics.robot.robot
import com.payam1991gr.kmp.playground.view.screens.graphics.GraphicsScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.GraphicsScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.graphics.charts.ChartsScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.ColorSchemeScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.opengl.OpenGlScreen
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GraphicsPresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(GraphicsScreen)
        GraphicsPresenter(navigator).robot {
            onState {
                assertThat(items.size).isEqualTo(4)

//                event(Event.OnClick(GraphicItem.Charts))
//                assertThat(navigator.awaitNextScreen()).isEqualTo(ChartsScreen)

                event(Event.OnClick(GraphicItem.ColorScheme))
                assertThat(navigator.awaitNextScreen()).isEqualTo(ColorSchemeScreen)

                event(Event.OnClick(GraphicItem.Icons))
                assertThat(navigator.awaitNextScreen()).isEqualTo(IconsScreen)

//                event(Event.OnClick(GraphicItem.OpenGL))
//                assertThat(navigator.awaitNextScreen()).isEqualTo(OpenGlScreen)

                event(Event.OnBackPressed)
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(OpenGlScreen))
            }
        }
    }
}
