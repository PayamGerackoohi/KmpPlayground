package com.payam1991gr.kmp.playground.view.module.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.payam1991gr.kmp.playground.data.merge
import com.payam1991gr.kmp.playground.view.module.robot.TextFieldInputRobot.InputScope
import com.payam1991gr.kmp.playground.view.test.util.Sni
import com.payam1991gr.kmp.playground.view.test.util.SniBlock
import com.payam1991gr.kmp.playground.view.test.util.hasError
import com.payam1991gr.kmp.playground.view.test.util.hasNoError

interface TextFieldInputRobot {
    fun title(text: String): Any
    fun input(block: InputScope.() -> Unit): Any
    fun saveButton(isEnabled: Boolean = true, block: SniBlock = {}): Any

    interface InputScope {
        fun hasText(text: String): Any
        fun putText(text: String): Any
        fun hasError(): Any
        fun noError(): Any
    }
}

class TextFieldInputRobotImpl(
    private val rule: ComposeContentTestRule,
    private val tag: String,
) : TextFieldInputRobot {
    override fun title(text: String) = rule
        .onNodeWithText(text)
        .assertIsDisplayed()

    override fun input(block: InputScope.() -> Unit) = rule
        .onNodeWithTag("TextFieldInput" merge tag)
        .assertIsDisplayed()
        .apply { InputScopeImpl(this).block() }

    override fun saveButton(isEnabled: Boolean, block: SniBlock) = rule
        .onNodeWithTag("TextFieldInput" merge tag merge "SaveButton")
        .assertIsDisplayed()
        .assertTextEquals("Save")
        .apply {
            if (isEnabled) assertIsEnabled()
            else assertIsNotEnabled()
        }
        .block()

    inner class InputScopeImpl(private val sni: Sni) : InputScope {
        override fun hasText(text: String) = sni.assertTextEquals(text)

        override fun putText(text: String) = sni.apply {
            performTextClearance()
            performTextInput(text)
        }

        override fun hasError() = sni.hasError("Invalid input")
        override fun noError() = sni.hasNoError()
    }
}

fun textFieldInputRobot(
    rule: ComposeContentTestRule,
    tag: String = "",
    block: TextFieldInputRobot.() -> Unit,
): TextFieldInputRobot = TextFieldInputRobotImpl(rule, tag).apply(block)
