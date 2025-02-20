package com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl

private const val TAG = "Animate Int as State"

class IntRobotImpl(private val rule: ComposeContentTestRule) : IntRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    private fun text(value: String) = rule
        .onNodeWithText(value)
        .assertIsDisplayed()

    override fun PreviewScope.intSample() = title {
        content(true) {
            state("Selected: false, Count: 0")
            text("  0%")
            performClick()
            state("Selected: true, Count: 100")
            text("100%")
        }
    }

    override fun CodeScope.intSample() {
        title(TAG) { performClick() }
        snippet("fun IntSample(maxCount: Int = 100, spacing: Dp = 2.dp) {")
    }
}
