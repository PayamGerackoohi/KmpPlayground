package com.payam1991gr.kmp.playground.ui.screens.components.carousel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.module.robot.randomImageRobot
import com.payam1991gr.kmp.playground.ui.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.ui.screens.components.carousel.CarouselScreen.State
import com.payam1991gr.kmp.playground.ui.screens.components.carousel.CarouselScreen.State.Event
import com.payam1991gr.kmp.playground.ui.screens.components.carousel.robot.carouselRobot
import com.payam1991gr.kmp.playground.ui.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test

class CarouselTest : BaseTest() {
    @Test
    fun test() {
        var showCode by mutableStateOf(false)
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            Carousel().Content(
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
            carouselRobot(rule) {
                randomImageRobot(rule) {
                    toolbar {
                        title("Carousel")

                        on("Back") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Back)) }

                        assertThatCodePageIsNotDisplayed()
                        preview {
                            horizontalMultiBrowseCarousel {
                                title()
                                description()
                                image("Image 1")
                                image("Image 2")
                                image("Image 3")
                            }
                            horizontalUncontainedCarousel {
                                title()
                                description()
                                image("Image 10")
                                image("Image 11")
                            }
                            on("Code") { performClick() }
                            verify { event(Event.OnToolbarAction(Action.Code)) }
                            showCode = true
                        }

                            assertThatPreviewPageIsNotDisplayed()
                        code {
                            horizontalMultiBrowseCarouselSampleCode()
                            horizontalUncontainedCarouselSampleCode()
                            on("Preview") { performClick() }
                            verify { event(Event.OnToolbarAction(Action.Preview)) }
                        }
                    }
                }
            }
        }
        confirmVerified()
    }
}
