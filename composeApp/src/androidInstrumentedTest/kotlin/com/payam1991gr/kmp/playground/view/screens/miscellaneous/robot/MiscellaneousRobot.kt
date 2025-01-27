package com.payam1991gr.kmp.playground.view.screens.miscellaneous.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.view.module.robot.SampleListRobot
import com.payam1991gr.kmp.playground.view.module.robot.sampleListRobot

interface MiscellaneousRobot {
    fun items(block: SampleListRobot.Scope.() -> Unit): Any
}

class MiscellaneousRobotImpl(private val rule: ComposeContentTestRule) : MiscellaneousRobot {
    override fun items(block: SampleListRobot.Scope.() -> Unit) = sampleListRobot(rule) {
        items(block)
    }
}

fun miscellaneousRobot(
    rule: ComposeContentTestRule,
    block: MiscellaneousRobot.() -> Unit,
): MiscellaneousRobot = MiscellaneousRobotImpl(rule).apply(block)
