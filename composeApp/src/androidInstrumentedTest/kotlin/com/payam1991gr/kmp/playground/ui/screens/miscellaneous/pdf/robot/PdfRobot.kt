package com.payam1991gr.kmp.playground.ui.screens.miscellaneous.pdf.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText

interface PdfRobot {
    fun previewContent(): Any
    fun codeContent(): Any
}

class PdfRobotImpl(private val rule: ComposeContentTestRule) : PdfRobot {
    override fun previewContent() = rule
        .onNodeWithText("Preview")
        .assertIsDisplayed()

    override fun codeContent() = rule
        .onNodeWithText("Code")
        .assertIsDisplayed()
}

fun pdfRobot(
    rule: ComposeContentTestRule,
    block: PdfRobot.() -> Unit,
): PdfRobot = PdfRobotImpl(rule).apply(block)
