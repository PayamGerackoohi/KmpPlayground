package com.payam1991gr.kmp.playground.view.screens.animations.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.view.module.robot.SampleListRobot
import com.payam1991gr.kmp.playground.view.module.robot.sampleListRobot

interface AnimationsRobot {
    fun items(block: SampleListRobot.Scope.() -> Unit): Any
}

class AnimationsRobotImpl(private val rule: ComposeContentTestRule) : AnimationsRobot {
    override fun items(block: SampleListRobot.Scope.() -> Unit) = sampleListRobot(rule) {
        items(block)
    }
}

fun animationsRobot(
    rule: ComposeContentTestRule,
    block: AnimationsRobot.() -> Unit,
): AnimationsRobot = AnimationsRobotImpl(rule).apply(block)
