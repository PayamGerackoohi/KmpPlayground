package com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope

class ColorSchemeRobotImpl(private val rule: ComposeTestRule) : ColorSchemeRobot {
    override fun PreviewScope.item(label: String) {
        sni.performScrollToNode(hasText(label))
        rule.onNodeWithText(label).assertIsDisplayed()
    }

    override fun codeContent() = rule.onNodeWithText(
        "fun Item(label: String, color: Color) = Surface(",
        substring = true,
    ).assertIsDisplayed()
}
