package com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.robot

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import com.payam1991gr.kmp.playground.ui.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.ui.test.util.SniBlock

class DatePickerRobotImpl(private val rule: ComposeContentTestRule) : DatePickerRobot {
    override fun PreviewScope.on(date: String, scrollTo: Boolean, block: SniBlock) {
        if (scrollTo) sni.performScrollToNode(hasText(date))
        rule.onNodeWithText(date)
            .assertIsDisplayed()
            .block()
    }

    override fun selectedDate(text: String) = rule
        .onNodeWithText(text)
        .assertIsDisplayed()

    override fun verifySwitchModeButton() = rule
        .onNodeWithContentDescription("Switch to text input mode")
        .assertIsDisplayed()

    override fun assertThatSwitchModeButtonIsNotShown() = rule
        .onAllNodesWithContentDescription("Switch to text input mode")
        .assertCountEquals(0)
}
