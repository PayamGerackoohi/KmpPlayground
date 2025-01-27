package com.payam1991gr.kmp.playground.view.module.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import com.payam1991gr.kmp.playground.view.module.robot.SampleListRobot.Scope
import com.payam1991gr.kmp.playground.view.test.util.SniBlock

interface SampleListRobot {
    fun items(block: Scope.() -> Unit): Any

    interface Scope {
        fun find(name: String, block: SniBlock = {}): Any
    }
}

class SampleListRobotImpl(private val rule: ComposeContentTestRule) : SampleListRobot {
    override fun items(block: Scope.() -> Unit) = ScopeImpl().apply(block)

    inner class ScopeImpl : Scope {
        override fun find(name: String, block: SniBlock) = rule.onNodeWithText(name)
            .assertIsDisplayed()
            .block()
    }
}

fun sampleListRobot(
    rule: ComposeContentTestRule,
    block: SampleListRobot.() -> Unit,
): SampleListRobot = SampleListRobotImpl(rule).apply(block)
