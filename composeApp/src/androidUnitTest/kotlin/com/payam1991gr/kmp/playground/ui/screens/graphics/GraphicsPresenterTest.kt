package com.payam1991gr.kmp.playground.ui.screens.graphics

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.model.GraphicItem
import com.payam1991gr.kmp.playground.ui.screens.graphics.robot.robot
import com.payam1991gr.kmp.playground.ui.screens.graphics.GraphicsScreen.State.Event
import com.payam1991gr.kmp.playground.ui.screens.graphics.charts.ChartsScreen
import com.payam1991gr.kmp.playground.ui.screens.graphics.opengl.OpenGlScreen
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class GraphicsPresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(GraphicsScreen)
        GraphicsPresenter(navigator).robot {
            onState {
                assertThat(items.size).isEqualTo(2)

                event(Event.OnClick(GraphicItem.Charts))
                assertThat(navigator.awaitNextScreen()).isEqualTo(ChartsScreen)

                event(Event.OnClick(GraphicItem.OpenGL))
                assertThat(navigator.awaitNextScreen()).isEqualTo(OpenGlScreen)

                event(Event.OnBackPressed)
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(OpenGlScreen))
            }
        }
    }
}
