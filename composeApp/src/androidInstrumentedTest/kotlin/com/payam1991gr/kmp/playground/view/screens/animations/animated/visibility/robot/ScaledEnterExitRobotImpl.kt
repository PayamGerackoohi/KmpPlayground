package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.robot

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl
import com.payam1991gr.kmp.playground.view.test.util.SniBlock

private const val TAG = "Scaled Enter-Exit"

class ScaledEnterExitRobotImpl(private val rule: ComposeContentTestRule) : ScaledEnterExitRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    private val greenBoxTag = "$TAG.GreenBox"
    private val redBoxTag = "$TAG.RedBox"

    private fun greenBox() = rule
        .onNodeWithTag(greenBoxTag)
        .assertIsDisplayed()

    private fun noGreenBox() = rule
        .onAllNodesWithTag(greenBoxTag)
        .assertCountEquals(0)

    private fun redBox() = rule
        .onNodeWithTag(redBoxTag)
        .assertIsDisplayed()

    private fun noRedBox() = rule
        .onAllNodesWithTag(redBoxTag)
        .assertCountEquals(0)

    private fun greenButton(block: SniBlock = {}) = rule
        .onNodeWithText("Green")
        .assertIsDisplayed()
        .block()

    private fun redButton(block: SniBlock = {}) = rule
        .onNodeWithText("Red")
        .assertIsDisplayed()
        .block()

    override fun PreviewScope.scaledEnterExitSample() = title {
        content(true)

        greenBox()
        greenButton {
            performClick()
            noGreenBox()
        }

        redBox()
        redButton {
            performClick()
            noRedBox()
        }

        performClick()
        noContent(true)
    }

    override fun CodeScope.scaledEnterExitSample() {
        title(TAG) { performClick() }
        snippet("fun AnimatedVisibility.ScaledEnterExitSample() {")
    }
}
