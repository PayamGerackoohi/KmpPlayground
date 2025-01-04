package com.payam1991gr.kmp.playground.ui.screens.miscellaneous.cpp.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface CppRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class CppRobotImpl(private val rule: ComposeContentTestRule) : CppRobot {
    override fun previewContent() = rule
        .onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule
        .onNodeWithText("Code")
        .assertIsDisplayed()
}

fun cppRobot(
    rule: ComposeContentTestRule,
    block: CppRobot.() -> Unit,
): CppRobot = CppRobotImpl(rule).apply(block)

