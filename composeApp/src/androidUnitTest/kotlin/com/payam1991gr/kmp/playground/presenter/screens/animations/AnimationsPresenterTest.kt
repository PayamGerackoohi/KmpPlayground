package com.payam1991gr.kmp.playground.presenter.screens.animations

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.model.AnimationItem
import com.payam1991gr.kmp.playground.presenter.screens.animations.robot.robot
import com.payam1991gr.kmp.playground.view.screens.animations.AnimationsScreen
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.AnimateXAsScreen
import com.payam1991gr.kmp.playground.view.screens.animations.AnimationsScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContentScreen
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibilityScreen
import com.payam1991gr.kmp.playground.view.screens.animations.crossfade.CrossfadeScreen
import com.slack.circuit.test.FakeNavigator
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AnimationsPresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(AnimationsScreen)
        AnimationsPresenter(navigator).robot {
            onState {
                assertThat(items).isEqualTo(
                    persistentListOf(
                        AnimationItem.AnimatedContent,
                        AnimationItem.AnimatedVisibility,
                        AnimationItem.Crossfade,
                        AnimationItem.AnimateXAs,
                    )
                )

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
