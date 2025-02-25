package com.payam1991gr.kmp.playground.view.screens.animations.animated.content.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl

interface TransitionExtensionRobot {
    fun PreviewScope.transitionExtensionSample(): Any
    fun CodeScope.transitionExtensionSample(): Any
}

private const val TAG = "Transition Extension"

class TransitionExtensionRobotImpl(private val rule: ComposeContentTestRule) :
    TransitionExtensionRobot, SampleRobot by SampleRobotImpl(rule, TAG) {
    override fun PreviewScope.transitionExtensionSample() = title {
        content(true) {
            state("Collapsed")
            performClick()
            state("Expanded")
            performClick()
            state("Collapsed")
        }
    }

    override fun CodeScope.transitionExtensionSample() {
        title(TAG) { performClick() }
        snippet("fun AnimatedContent.TransitionExtensionSample() {")
    }
}
