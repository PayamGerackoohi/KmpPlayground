package com.payam1991gr.kmp.playground.view.screens.components.carousel.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import com.payam1991gr.kmp.playground.view.screens.components.carousel.robot.CarouselRobot.HorizontalMultiBrowseCarouselScope
import com.payam1991gr.kmp.playground.view.screens.components.carousel.robot.CarouselRobot.HorizontalUncontainedCarouselScope

interface CarouselRobot {
    fun horizontalMultiBrowseCarousel(block: HorizontalMultiBrowseCarouselScope.() -> Unit): Any
    fun horizontalUncontainedCarousel(block: HorizontalUncontainedCarouselScope.() -> Unit): Any
    fun horizontalMultiBrowseCarouselSampleCode(): Any
    fun horizontalUncontainedCarouselSampleCode(): Any

    interface HorizontalMultiBrowseCarouselScope {
        fun title(): Any
        fun description(): Any
    }

    interface HorizontalUncontainedCarouselScope {
        fun title(): Any
        fun description(): Any
    }
}

class CarouselRobotImpl(private val rule: ComposeContentTestRule) : CarouselRobot {
    override fun horizontalMultiBrowseCarousel(block: HorizontalMultiBrowseCarouselScope.() -> Unit) =
        HorizontalMultiBrowseCarouselScopeImpl().block()

    override fun horizontalUncontainedCarousel(block: HorizontalUncontainedCarouselScope.() -> Unit) =
        HorizontalUncontainedCarouselScopeImpl().block()

    override fun horizontalMultiBrowseCarouselSampleCode() = rule
        .onNodeWithText("fun HorizontalMultiBrowseCarouselSample() {", substring = true)
        .assertIsDisplayed()

    override fun horizontalUncontainedCarouselSampleCode() = rule
        .onNodeWithText("fun HorizontalUncontainedCarouselSample() {", substring = true)
        .assertIsDisplayed()

    inner class HorizontalMultiBrowseCarouselScopeImpl : HorizontalMultiBrowseCarouselScope {
        override fun title() = rule
            .onNodeWithText("Horizontal Uncontained Carousel")
            .assertIsDisplayed()

        override fun description() = rule
            .onNodeWithText("A horizontal carousel that displays its", substring = true)
            .assertIsDisplayed()
    }

    inner class HorizontalUncontainedCarouselScopeImpl : HorizontalUncontainedCarouselScope {
        override fun title() = rule
            .onNodeWithText("Horizontal Multi-Browse Carousel")
            .assertIsDisplayed()

        override fun description() = rule
            .onNodeWithText("A horizontal carousel meant to display many", substring = true)
            .assertIsDisplayed()
    }
}

fun carouselRobot(
    rule: ComposeContentTestRule,
    block: CarouselRobot.() -> Unit,
): CarouselRobot = CarouselRobotImpl(rule).apply(block)
