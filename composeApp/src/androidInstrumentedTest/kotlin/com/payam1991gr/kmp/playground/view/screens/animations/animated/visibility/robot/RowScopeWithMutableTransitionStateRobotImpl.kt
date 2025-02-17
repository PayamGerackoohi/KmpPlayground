package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl
import com.payam1991gr.kmp.playground.view.test.util.hasState

private const val TAG = "Row-Scope with Mutable Transition State"

class RowScopeWithMutableTransitionStateRobotImpl(
    private val rule: ComposeContentTestRule,
) : RowScopeWithMutableTransitionStateRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    override fun PreviewScope.rowScopeWithMutableTransitionStateSample() =
        (title() to content(true)).let { (title, content) ->
            content.hasState("Expanded")
            title.performClick()
            content.hasState("Collapsed")
        }

    override fun CodeScope.rowScopeWithMutableTransitionStateSample() {
        title(TAG) { performClick() }
        snippet("fun AnimatedVisibility.RowScopeWithMutableTransitionStateSample() {")
    }
}
