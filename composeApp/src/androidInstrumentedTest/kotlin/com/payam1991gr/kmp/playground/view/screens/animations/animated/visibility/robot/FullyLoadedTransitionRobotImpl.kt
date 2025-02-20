package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl

private const val TAG = "Fully Loaded Transition"

class FullyLoadedTransitionRobotImpl(
    private val rule: ComposeContentTestRule,
) : FullyLoadedTransitionRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    override fun PreviewScope.fullyLoadedTransitionSample() = title {
        content()
        performClick()
        noContent()
    }

    override fun CodeScope.fullyLoadedTransitionSample() {
        title(TAG) { performClick() }
        snippet("fun FullyLoadedTransitionSample() = ContentList {")
    }
}
