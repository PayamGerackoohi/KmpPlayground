package com.payam1991gr.kmp.playground.view.screens.components.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.view.module.robot.SampleListRobot
import com.payam1991gr.kmp.playground.view.module.robot.sampleListRobot

interface ComponentsRobot {
    fun items(block: SampleListRobot.Scope.() -> Unit): Any
}

class ComponentsRobotImpl(private val rule: ComposeContentTestRule) : ComponentsRobot {
    override fun items(block: SampleListRobot.Scope.() -> Unit) = sampleListRobot(rule) {
        items { block() }
    }
}

fun componentsRobot(
    rule: ComposeContentTestRule,
    block: ComponentsRobot.() -> Unit,
): ComponentsRobot = ComponentsRobotImpl(rule).apply(block)
