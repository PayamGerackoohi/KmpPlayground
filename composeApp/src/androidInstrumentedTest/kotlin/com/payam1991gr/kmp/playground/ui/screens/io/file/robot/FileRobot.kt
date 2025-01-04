package com.payam1991gr.kmp.playground.ui.screens.io.file.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface FileRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class FileRobotImpl(private val rule: ComposeContentTestRule) : FileRobot {
    override fun previewContent() = rule
        .onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule
        .onNodeWithText("Code")
        .assertIsDisplayed()
}

fun fileRobot(
    rule: ComposeContentTestRule,
    block: FileRobot.() -> Unit,
): FileRobot = FileRobotImpl(rule).apply(block)
