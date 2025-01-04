package com.payam1991gr.kmp.playground.ui.screens.io.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.ui.module.robot.SampleListRobot
import com.payam1991gr.kmp.playground.ui.module.robot.sampleListRobot

interface IoRobot {
    fun items(block: SampleListRobot.Scope.() -> Unit): Any
}

class IoRobotImpl(private val rule: ComposeContentTestRule) : IoRobot {
    override fun items(block: SampleListRobot.Scope.() -> Unit) = sampleListRobot(rule) {
        items(block)
    }
}

fun ioRobot(
    rule: ComposeContentTestRule,
    block: IoRobot.() -> Unit,
): IoRobot = IoRobotImpl(rule).apply(block)
