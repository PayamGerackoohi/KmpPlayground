package com.payam1991gr.kmp.playground.view.module

import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.data.string.isDecimal
import com.payam1991gr.kmp.playground.data.model.textfield.IntConverter
import com.payam1991gr.kmp.playground.view.module.robot.textFieldInputRobot
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import org.junit.Test

class TextFieldInputTest : BaseTest() {
    @Test
    fun casualTest() {
        val onSave = mockk<(Int) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            TextFieldInput(
                title = "Title",
                initialData = 123,
                converter = IntConverter(),
                onTextChanged = { text -> text.filter { it.isDecimal() } },
                onConfirm = onSave,
            )
        }
        textFieldInputRobot(rule) {
            title("Title")
            input {
                noError()
                hasText("123")
                confirmButton(isEnabled = false)
                putText("12")
                confirmButton { performClick() }
                verify { onSave(12) }

                putText("a")
                hasError()
                confirmButton(isEnabled = false)
            }
        }
        confirmVerified()
    }
}
