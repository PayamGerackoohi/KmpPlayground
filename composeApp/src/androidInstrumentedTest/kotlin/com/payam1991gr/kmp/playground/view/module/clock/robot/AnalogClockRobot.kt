package com.payam1991gr.kmp.playground.view.module.clock.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import com.payam1991gr.kmp.playground.data.string.merge
import com.payam1991gr.kmp.playground.view.module.clock.robot.AnalogClockRobot.ClockScope
import com.payam1991gr.kmp.playground.view.test.util.Sni
import com.payam1991gr.kmp.playground.view.test.util.hasState

interface AnalogClockRobot {
    fun clock(tag: String = "", block: ClockScope.() -> Unit): Any

    interface ClockScope {
        fun state(value: String): Any
    }
}

class AnalogClockRobotImpl(private val rule: ComposeContentTestRule) : AnalogClockRobot {
    override fun clock(tag: String, block: ClockScope.() -> Unit) = rule
        .onNodeWithTag("AnalogClock" merge tag)
        .assertIsDisplayed()
        .apply { ClockScopeImpl(this).block() }

    inner class ClockScopeImpl(private val sni: Sni) : ClockScope {
        override fun state(value: String) = sni.hasState(value)
    }
}

fun analogClockRobot(
    rule: ComposeContentTestRule,
    block: AnalogClockRobot.() -> Unit,
) = AnalogClockRobotImpl(rule).block()
