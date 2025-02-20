package com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope

class AnimateXAsRobotImpl(private val rule: ComposeContentTestRule) : AnimateXAsRobot,
    FloatRobot by FloatRobotImpl(rule),
    OffsetRobot by OffsetRobotImpl(rule),
    DpRobot by DpRobotImpl(rule),
    ColorRobot by ColorRobotImpl(rule),
    IntRobot by IntRobotImpl(rule),
    ValueRobot by ValueRobotImpl(rule) {
    override fun CodeScope.shared() {
        title("Shared") { performClick() }
        snippet("fun rememberBoolean(init: Boolean = true) = remember { mutableStateOf(init)")
        snippet("fun Module(")
        snippet("object Module {")
        snippet("fun Modifier.moduleSize(ratio: Float = 2f) = this")
    }
}
