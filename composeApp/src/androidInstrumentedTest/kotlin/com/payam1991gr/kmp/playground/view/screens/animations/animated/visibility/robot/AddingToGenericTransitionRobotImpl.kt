package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl

private const val TAG = "Adding to Generic Transition"

class AddingToGenericTransitionRobotImpl(
    private val rule: ComposeContentTestRule,
) : AddingToGenericTransitionRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    private fun header() = rule
        .onNodeWithContentDescription("$TAG.Header")
        .assertIsDisplayed()

    private fun text() = rule
        .onNodeWithText("Lorem ipsum dolor sit amet,", substring = true)
        .assertIsDisplayed()

    override fun PreviewScope.addingToGenericTransitionSample() = title {
        header()
        noContent()
        performClick()
        content()
        text()
    }

    override fun CodeScope.addingToGenericTransitionSample() {
        title(TAG) { performClick() }
        snippet("fun AddingToGenericTransitionSample() {")
    }
}
