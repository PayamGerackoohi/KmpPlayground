package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibilityScreen.State
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibilityScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.robot.animatedVisibilityRobot
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test

class AnimatedVisibilityTest : BaseTest() {
    @Test
    fun test() {
        var showCode by mutableStateOf(false)
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            AnimatedVisibility().Content(
                State(
                    showCode = showCode,
                    toolbarActions = persistentListOf(
                        Action.Back,
                        if (showCode) Action.Preview else Action.Code,
                    ),
                    event = event,
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            animatedVisibilityRobot(rule) {
                toolbar {
                    title("Animated Visibility")

                    on("Back") { performClick() }
                    verify { event(Event.OnToolbarAction(Action.Back)) }

                    assertThatCodePageIsNotDisplayed()
                    preview {
                        horizontalTransitionSample()
                        slideTransitionSample()
                        fadeTransitionSample()
                        fullyLoadedTransitionSample()
                        booleanVisibleParamNoReceiverSample()
                        floatingActionButtonSample()
                        slideInOutSample()
                        expandShrinkVerticallySample()
                        expandInShrinkOutSample()
                        rowSample()
                        scopeAnimateEnterExitSample()
                        addingToGenericTransitionSample()
                        lazyRowSample()
                        rowScopeWithMutableTransitionStateSample()
                        animateEnterExitPartialContentSample()
                        scaledEnterExitSample()

                        on("Code") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Code)) }
                        showCode = true
                    }

                    assertThatPreviewPageIsNotDisplayed()
                    code {
                        shared()
                        horizontalTransitionSample()
                        slideTransitionSample()
                        fadeTransitionSample()
                        fullyLoadedTransitionSample()
                        booleanVisibleParamNoReceiverSample()
                        animatedFloatingActionButtonSample()
                        slideInOutSample()
                        expandShrinkVerticallySample()
                        expandInShrinkOutSample()
                        rowSample()
                        scopeAnimateEnterExitSample()
                        addingToGenericTransitionSample()
                        lazyRowSample()
                        rowScopeWithMutableTransitionStateSample()
                        animateEnterExitPartialContentSample()
                        scaledEnterExitSample()

                        on("Preview") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Preview)) }
                    }
                }
            }
        }
        confirmVerified()
    }
}
