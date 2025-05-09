package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl

private const val TAG = "Fade Transition"

class FadeTransitionRobotImpl(private val rule: ComposeContentTestRule) : FadeTransitionRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    override fun PreviewScope.fadeTransitionSample() = title {
        content()
        performClick()
        noContent()
    }

    override fun CodeScope.fadeTransitionSample(){
        title(TAG){performClick()}
        snippet("fun FadeTransitionSample() {")
    }
}
