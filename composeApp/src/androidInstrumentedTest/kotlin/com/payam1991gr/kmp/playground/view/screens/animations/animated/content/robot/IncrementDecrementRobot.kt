package com.payam1991gr.kmp.playground.view.screens.animations.animated.content.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl

interface IncrementDecrementRobot {
    fun PreviewScope.incrementDecrementSample(): Any
    fun CodeScope.incrementDecrementSample(): Any
}

private const val TAG = "Increment / Decrement"

class IncrementDecrementRobotImpl(private val rule: ComposeContentTestRule) :
    IncrementDecrementRobot, SampleRobot by SampleRobotImpl(rule, TAG) {

    private fun increment() = rule.onNode(
        hasContentDescription("Increment").and(hasModuleAsAncestor(TAG))
    ).assertIsDisplayed()
        .performClick()

    private fun decrement() = rule.onNode(
        hasContentDescription("Decrement").and(hasModuleAsAncestor(TAG))
    ).assertIsDisplayed()
        .performClick()

    override fun PreviewScope.incrementDecrementSample() = title {
        content(withTag = true) {
            state("0")
            increment()
            state("1")
            increment()
            state("2")
            decrement()
            state("1")
            performClick()
            decrement()
            state("-1")
        }
    }

    override fun CodeScope.incrementDecrementSample() {
        title(TAG) { performClick() }
        snippet("fun IncrementDecrementSample() {")
    }
}
