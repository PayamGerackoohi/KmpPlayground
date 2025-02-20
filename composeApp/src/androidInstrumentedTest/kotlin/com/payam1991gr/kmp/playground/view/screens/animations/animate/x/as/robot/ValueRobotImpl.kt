package com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl

private const val TAG = "Animate Value as State"

class ValueRobotImpl(private val rule: ComposeContentTestRule) : ValueRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    override fun PreviewScope.valueSample() = title {
        content(true) {
            state("State 1")
            performClick()
            state("State 2")
        }
    }

    override fun CodeScope.valueSample() {
        title(TAG) { performClick() }
        snippet("fun ValueSample() {")
    }
}
