package com.payam1991gr.kmp.playground.view.screens.animations.crossfade.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasStateDescription
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope

interface CrossfadeRobot {
    fun PreviewScope.sample()
    fun CodeScope.sample(): Any
}

class CrossfadeRobotImpl(private val rule: ComposeContentTestRule) : CrossfadeRobot {
    private fun description() = rule
        .onNodeWithText("It allows to switch between two layouts with a crossfade animation.")
        .assertIsDisplayed()

    private fun toggle() = rule
        .onNodeWithText("Toggle")
        .assertIsDisplayed()
        .performClick()

    private fun state(value: String) = rule.onNode(hasStateDescription(value))
        .assertIsDisplayed()

    override fun PreviewScope.sample() {
        description()
        state("State 1")
        toggle()
        state("State 2")
        toggle()
        state("State 1")
        toggle()
    }

    override fun CodeScope.sample() = rule
        .onNodeWithText("fun CrossfadeSample() = ContentList {", substring = true)
        .assertIsDisplayed()
}

fun crossfadeRobot(
    rule: ComposeContentTestRule,
    block: CrossfadeRobot.() -> Unit,
): CrossfadeRobot = CrossfadeRobotImpl(rule).apply(block)
