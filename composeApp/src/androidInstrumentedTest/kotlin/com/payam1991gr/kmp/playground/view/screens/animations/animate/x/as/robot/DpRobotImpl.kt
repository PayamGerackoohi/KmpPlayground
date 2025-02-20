package com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl

private const val TAG = "Animate DP as State"

class DpRobotImpl(private val rule: ComposeContentTestRule) : DpRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    override fun PreviewScope.dpSample() = title {
        content(true) {
            state("Collapsed")
            performClick()
            state("Expanded")
        }
    }

    override fun CodeScope.dpSample() {
        title(TAG) { performClick() }
        snippet("fun DpSample() {")
    }
}
