package com.payam1991gr.kmp.playground.view.screens.animations.animated.content.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl

interface TransitionSpecRobot {
    fun PreviewScope.transitionSpecSample(): Any
    fun CodeScope.transitionSpecSample(): Any
}

private const val TAG = "Transition Spec"

class TransitionSpecRobotImpl(private val rule: ComposeContentTestRule) : TransitionSpecRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    override fun PreviewScope.transitionSpecSample() = title {
        content(true) {
            state("Expanded")
            performClick()
            state("Collapsed")
            performClick()
            state("Expanded")
        }
    }

    override fun CodeScope.transitionSpecSample() {
        title(TAG) { performClick() }
        snippet("fun AnimatedContent.TransitionSpecSample() {")
    }
}
