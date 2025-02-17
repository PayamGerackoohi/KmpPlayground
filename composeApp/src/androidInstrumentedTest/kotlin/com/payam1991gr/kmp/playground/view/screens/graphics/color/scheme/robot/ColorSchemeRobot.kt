package com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.robot

import androidx.compose.ui.test.junit4.ComposeTestRule
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope

interface ColorSchemeRobot {
    fun PreviewScope.item(label: String): Any
    fun codeContent(): Any
}

fun colorSchemeRobot(
    rule: ComposeTestRule,
    block: ColorSchemeRobot.() -> Unit,
): ColorSchemeRobot = ColorSchemeRobotImpl(rule).apply(block)
