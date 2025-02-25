package com.payam1991gr.kmp.playground.view.screens.animations.animated.content.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl

interface SlideIntoContainerRobot {
    fun PreviewScope.slideIntoContainerSample(): Any
    fun CodeScope.slideIntoContainerSample(): Any
}

private const val TAG = "Slide Into Container"

class SlideIntoContainerRobotImpl(private val rule: ComposeContentTestRule) :
    SlideIntoContainerRobot, SampleRobot by SampleRobotImpl(rule, TAG) {
    override fun PreviewScope.slideIntoContainerSample() = title {
        content(true) {
            state("Level1")
            performClick()
            state("Level2")
            performClick()
            state("Level3")
            performClick()
            state("Level1")
        }
    }

    override fun CodeScope.slideIntoContainerSample() {
        title(TAG) { performClick() }
        snippet("fun AnimatedContent.SlideIntoContainerSample() {")
    }
}
