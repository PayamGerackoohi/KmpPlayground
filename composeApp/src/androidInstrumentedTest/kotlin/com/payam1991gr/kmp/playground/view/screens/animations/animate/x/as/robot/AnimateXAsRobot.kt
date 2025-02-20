package com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope

interface AnimateXAsRobot :
    FloatRobot,
    OffsetRobot,
    DpRobot,
    ColorRobot,
    IntRobot,
    ValueRobot {
    fun CodeScope.shared(): Any
}

fun animateXAsRobot(
    rule: ComposeContentTestRule,
    block: AnimateXAsRobot.() -> Unit,
): AnimateXAsRobot = AnimateXAsRobotImpl(rule).apply(block)
