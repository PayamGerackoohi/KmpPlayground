package com.payam1991gr.kmp.playground.view.module.robot

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.isEnabled
import androidx.compose.ui.test.isNotEnabled
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import com.payam1991gr.kmp.playground.data.string.merge
import com.payam1991gr.kmp.playground.view.module.robot.TextFieldInputRobot.InputScope
import com.payam1991gr.kmp.playground.view.test.util.Sni
import com.payam1991gr.kmp.playground.view.test.util.SniBlock
import com.payam1991gr.kmp.playground.view.test.util.hasError
import com.payam1991gr.kmp.playground.view.test.util.hasNoError

interface TextFieldInputRobot {
    fun title(text: String = ""): Any
    fun input(block: InputScope.() -> Unit = {}): Any
    fun confirmButton(isEnabled: Boolean = true, label: String = "Save", block: SniBlock = {}): Any

    interface InputScope {
        fun hasText(vararg text: String): Any
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
        .onNodeWithTag("TextFieldInput" merge tag merge "Header")
        .assertTextEquals(text)
        .apply { if (text.isEmpty()) assertExists() else assertIsDisplayed() }

    override fun input(block: InputScope.() -> Unit) = rule
        .onNodeWithTag("TextFieldInput" merge tag)
        .assertIsDisplayed()
        .apply { InputScopeImpl(this).block() }

    override fun confirmButton(isEnabled: Boolean, label: String, block: SniBlock) = rule
        .onNodeWithTag("TextFieldInput" merge tag merge "ConfirmButton")
        .assertIsDisplayed()
        .assertTextEquals(label)
        .assert(if (isEnabled) isEnabled() else isNotEnabled())
        .block()

    inner class InputScopeImpl(private val sni: Sni) : InputScope {
        override fun hasText(vararg text: String) = sni.assertTextEquals(*text)

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
