package com.payam1991gr.kmp.playground.view.screens.animations.animated.content.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope

interface AnimatedContentRobot : SimpleSampleRobot,
    IncrementDecrementRobot,
    TransitionSpecRobot,
    TransitionExtensionRobot,
    SlideIntoContainerRobot {
    fun CodeScope.shared(): Any
}

class AnimatedContentRobotImpl(private val rule: ComposeContentTestRule) : AnimatedContentRobot,
    SimpleSampleRobot by SimpleSampleRobotImpl(rule),
    IncrementDecrementRobot by IncrementDecrementRobotImpl(rule),
    TransitionSpecRobot by TransitionSpecRobotImpl(rule),
    TransitionExtensionRobot by TransitionExtensionRobotImpl(rule),
    SlideIntoContainerRobot by SlideIntoContainerRobotImpl(rule) {
    override fun CodeScope.shared() {
        title("Shared") { performClick() }
        snippet("fun Item(size: Dp, color: Color) = Box(Modifier.size(size).background(color))")
    }
}

fun animatedContentRobot(
    rule: ComposeContentTestRule,
    block: AnimatedContentRobot.() -> Unit,
): AnimatedContentRobot = AnimatedContentRobotImpl(rule).apply(block)
