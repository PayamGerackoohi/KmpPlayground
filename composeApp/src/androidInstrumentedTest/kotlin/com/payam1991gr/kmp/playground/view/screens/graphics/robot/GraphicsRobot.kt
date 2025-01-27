package com.payam1991gr.kmp.playground.view.screens.graphics.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.view.module.robot.SampleListRobot
import com.payam1991gr.kmp.playground.view.module.robot.sampleListRobot

interface GraphicsRobot {
    fun items(block: SampleListRobot.Scope.() -> Unit): Any
}

class GraphicsRobotImpl(private val rule: ComposeContentTestRule) : GraphicsRobot {
    override fun items(block: SampleListRobot.Scope.() -> Unit) = sampleListRobot(rule) {
        items(block)
    }
}

fun graphicsRobot(
    rule: ComposeContentTestRule,
    block: GraphicsRobot.() -> Unit,
): GraphicsRobot = GraphicsRobotImpl(rule).apply(block)
