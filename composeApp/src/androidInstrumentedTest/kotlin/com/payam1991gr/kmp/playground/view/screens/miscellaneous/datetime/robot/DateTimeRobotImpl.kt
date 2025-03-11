package com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.robot.DateTimeRobot.CardScope
import com.payam1991gr.kmp.playground.view.test.util.Sni

class DateTimeRobotImpl(private val rule: ComposeContentTestRule) : DateTimeRobot {
    override fun PreviewScope.card(tag: String, block: CardScope.() -> Unit) {
        val label = "Clock - $tag"
        sni.performScrollToNode(hasContentDescription(label))
        rule.onNodeWithContentDescription(label)
            .assertIsDisplayed()
            .apply { CardScopeImpl(tag, this).block() }
    }

    override fun CodeScope.analogClockCard() {
        title("Shared") { performClick() }
        snippet("private fun AnalogClockCard(now: Instant, labeledTimeZone: LabeledTimeZone)")
    }

    override fun CodeScope.analogClock() {
        title("Analog Clock") { performClick() }
        snippet("fun AnalogClock(")
    }

    override fun CodeScope.timeZoneData() {
        title("Time Zone Data") { performClick() }
        snippet("data class TimeZoneData(val name: String, val offset: String)")
        snippet("data class LabeledTimeZone(val value: TimeZone, val label: String)")
        snippet("fun TimeZone.offsetStringAt(instant: Instant) = offsetAt(instant).string()")
        snippet("infix fun TimeZone.label(label: String) = LabeledTimeZone(this, label)")
        snippet("fun UtcOffset.string() = if (this == UtcOffset.ZERO) \"+0 HRS\" else toString()")
        snippet("fun UtcOffset.asLabeledTimeZone() = asTimeZone().run { LabeledTimeZone(this, id) }")
    }

    inner class CardScopeImpl(private val tag: String, private val sni: Sni) : CardScope {
        override fun title() = sni
            .onChildren()
            .filterToOne(hasTextExactly(tag))
            .assertIsDisplayed()

        override fun date(value: String) = sni
            .onChildren()
            .filterToOne(hasTextExactly(value))
            .assertIsDisplayed()

        override fun time(value: String) = sni
            .onChildren()
            .filterToOne(hasTextExactly(value))
            .assertIsDisplayed()

        override fun utc(value: String) = sni
            .onChildren()
            .filterToOne(hasTextExactly(value))
            .assertIsDisplayed()
    }
}
