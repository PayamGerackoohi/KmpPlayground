package com.payam1991gr.kmp.playground.view.screens.io.datastore.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface DatastoreRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class DatastoreRobotImpl(private val rule: ComposeContentTestRule) : DatastoreRobot {
    override fun previewContent() = rule
        .onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule
        .onNodeWithText("Code")
        .assertIsDisplayed()
}

fun datastoreRobot(
    rule: ComposeContentTestRule,
    block: DatastoreRobot.() -> Unit,
): DatastoreRobot = DatastoreRobotImpl(rule).apply(block)
