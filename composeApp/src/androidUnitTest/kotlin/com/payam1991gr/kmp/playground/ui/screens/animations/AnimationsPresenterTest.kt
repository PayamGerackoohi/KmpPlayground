package com.payam1991gr.kmp.playground.ui.screens.animations

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.model.AnimationItem
import com.payam1991gr.kmp.playground.ui.screens.animations.animate.x.`as`.AnimateXAsScreen
import com.payam1991gr.kmp.playground.ui.screens.animations.robot.robot
import com.payam1991gr.kmp.playground.ui.screens.animations.AnimationsScreen.State.Event
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.content.AnimatedContentScreen
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.visibility.AnimatedVisibilityScreen
import com.payam1991gr.kmp.playground.ui.screens.animations.crossfade.CrossfadeScreen
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AnimationsPresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(AnimationsScreen)
        AnimationsPresenter(navigator).robot {
            onState {
                assertThat(items.size).isEqualTo(4)

                event(Event.OnClick(AnimationItem.AnimatedContent))
                assertThat(navigator.awaitNextScreen()).isEqualTo(AnimatedContentScreen)

                event(Event.OnClick(AnimationItem.AnimatedVisibility))
                assertThat(navigator.awaitNextScreen()).isEqualTo(AnimatedVisibilityScreen)

                event(Event.OnClick(AnimationItem.Crossfade))
                assertThat(navigator.awaitNextScreen()).isEqualTo(CrossfadeScreen)

                event(Event.OnClick(AnimationItem.AnimateXAs))
                assertThat(navigator.awaitNextScreen()).isEqualTo(AnimateXAsScreen)

                event(Event.OnBackPressed)
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(AnimateXAsScreen))
            }
        }
    }
}

/*
class DummyTest {
    @Test
    fun test() {
//        println(Order.Home.name)
        println(Order.Modules.RandomImage.name)
        println(Order.Miscellaneous.Pdf.Preview.name)
        println(Order.Io.Api.Preview.name)
        println(Order.Graphics.OpenGl.Code.name)
        println(Order.Animations.AnimateXAs.Code.name)
        println(Order.Components.PullToRefresh.Code.name)
        println(Order.Home.name)
    }
}
*/
