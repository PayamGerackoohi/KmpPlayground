package com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.robot

import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import com.payam1991gr.kmp.playground.ui.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.ui.test.util.Sni

class TimePickerRobotImpl(private val rule: ComposeContentTestRule) : TimePickerRobot {
    override fun verify12HourMode() = rule
        .onAllNodesWithContentDescription("23 hours")
        .assertCountEquals(0)

    override fun PreviewScope.verify24HourMode(scrollTo: Boolean) {
        val text = "23 hours"
        if (scrollTo) sni.performScrollToNode(hasContentDescription(text))
        rule
            .onNodeWithContentDescription(text)
            .assertIsDisplayed()
    }

    override fun daySection(block: TimePickerRobot.DaySectionScope.() -> Unit) = rule
        .onNodeWithContentDescription("Select AM or PM")
        .assertIsDisplayed()
        .apply { DaySectionScopeImpl(this).block() }

    override fun switchToMinutesMode() = rule
        .onNodeWithContentDescription("Select minutes")
        .assertIsDisplayed()
        .performClick()

    @OptIn(ExperimentalTestApi::class)
    override fun verifyMinutesMode() =
        rule.waitUntilExactlyOneExists(hasContentDescription("55 minutes"))

    override fun select(mode: TimePickerRobot.Mode, hour: String?, minute: String?) {
        select(
            hour,
            when (mode) {
                TimePickerRobot.Mode._12Hours -> "o'clock"
                TimePickerRobot.Mode._24Hours -> "hours"
            }
        )
        select(minute, "minutes")
    }

    private fun select(time: String?, label: String) = time?.let {
        rule.onNodeWithContentDescription("$it $label")
            .assertIsDisplayed()
            .performClick()
    }

    inner class DaySectionScopeImpl(private val sni: Sni) : TimePickerRobot.DaySectionScope {
        override fun am() = sni
            .onChildren()
            .filterToOne(hasTextExactly("AM"))
            .assertIsDisplayed()
            .performClick()

        override fun pm() = sni
            .onChildren()
            .filterToOne(hasTextExactly("PM"))
            .assertIsDisplayed()
            .performClick()
    }
}
