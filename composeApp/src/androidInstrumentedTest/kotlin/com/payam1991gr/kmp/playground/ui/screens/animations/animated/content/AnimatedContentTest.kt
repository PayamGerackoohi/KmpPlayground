package com.payam1991gr.kmp.playground.ui.screens.animations.animated.content

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.content.AnimatedContentScreen.State
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.content.AnimatedContentScreen.State.Event
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.content.robot.animatedContentRobot
import com.payam1991gr.kmp.playground.ui.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test

class AnimatedContentTest : BaseTest() {
    @Test
    fun test() {
        var showCode by mutableStateOf(false)
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            AnimatedContent().Content(
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
            animatedContentRobot(rule) {
                toolbar {
                    title("Animated Content")
                    on("Back") { performClick() }
                    verify { event(Event.OnToolbarAction(Action.Back)) }

                    assertThatCodePageIsNotDisplayed()
                    preview {
                        previewContent()
                        on("Code") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Code)) }
                        showCode = true
                    }

                    assertThatPreviewPageIsNotDisplayed()
                    code {
                        codeContent()
                        on("Preview") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Preview)) }
                    }
                }
            }
        }
        confirmVerified()
    }
}
