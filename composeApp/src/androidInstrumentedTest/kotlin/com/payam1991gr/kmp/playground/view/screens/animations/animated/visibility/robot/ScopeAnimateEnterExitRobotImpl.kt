package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.robot

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl

private const val TAG = "Scope Animate Enter-Exit"

class ScopeAnimateEnterExitRobotImpl(
    private val rule: ComposeContentTestRule,
) : ScopeAnimateEnterExitRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    private val transitionTextTag = "Transition Finished"
    private fun transitionText() = rule
        .onNodeWithText(transitionTextTag)
        .assertIsDisplayed()

    private fun noTransitionText() = rule
        .onAllNodesWithText(transitionTextTag)
        .assertCountEquals(0)

    override fun PreviewScope.scopeAnimateEnterExitSample() = title {
        content(true)
        transitionText()
        performClick()
        noContent(true)
        noTransitionText()
    }

    override fun CodeScope.scopeAnimateEnterExitSample() {
        title(TAG) { performClick() }
        snippet("fun ScopeAnimateEnterExitSample() {")
    }
}
