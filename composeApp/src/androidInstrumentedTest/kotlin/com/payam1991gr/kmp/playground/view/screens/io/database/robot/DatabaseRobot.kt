package com.payam1991gr.kmp.playground.view.screens.io.database.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface DatabaseRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class DatabaseRobotImpl(private val rule: ComposeContentTestRule) : DatabaseRobot {
    override fun previewContent() = rule
        .onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule
        .onNodeWithText("Code")
        .assertIsDisplayed()
}

fun databaseRobot(
    rule: ComposeContentTestRule,
    block: DatabaseRobot.() -> Unit,
): DatabaseRobot = DatabaseRobotImpl(rule).apply(block)
