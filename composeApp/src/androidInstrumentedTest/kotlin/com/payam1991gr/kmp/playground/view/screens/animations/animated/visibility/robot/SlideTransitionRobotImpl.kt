package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl

private const val TAG = "Slide Transition"

class SlideTransitionRobotImpl(private val rule: ComposeContentTestRule) : SlideTransitionRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    override fun PreviewScope.slideTransitionSample() = title {
        content()
        performClick()
        noContent()
    }

    override fun CodeScope.slideTransitionSample() {
        title(TAG) { performClick() }
        snippet("fun AnimatedVisibility.SlideTransitionSample() {")
    }
}
