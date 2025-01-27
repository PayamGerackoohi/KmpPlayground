package com.payam1991gr.kmp.playground.view.screens.io.api.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface ApiRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class ApiRobotImpl(private val rule: ComposeContentTestRule) : ApiRobot {
    override fun previewContent() = rule
        .onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule
        .onNodeWithText("Code")
        .assertIsDisplayed()
}

fun apiRobot(
    rule: ComposeContentTestRule,
    block: ApiRobot.() -> Unit,
): ApiRobot = ApiRobotImpl(rule).apply(block)
