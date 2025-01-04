package com.payam1991gr.kmp.playground.ui.screens.components.dialog

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.ui.LocalTestMode
import com.payam1991gr.kmp.playground.ui.module.SamplePage.Action
import com.payam1991gr.kmp.playground.ui.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogScreen.State
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogScreen.State.Event
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.robot.dialogRobot
import com.payam1991gr.kmp.playground.ui.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test

class DialogTest : BaseTest() {
    @Test
    fun test() {
        var showCode by mutableStateOf(false)
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            CompositionLocalProvider(LocalTestMode provides true) {
                Dialog().Content(
                    State(
                        showCode = showCode,
                        toolbarActions = persistentListOf(
                            Action.Back,
                            if (showCode) Action.Preview else Action.Code,
                        ),
                        event = event,
                    ),
                    Modifier,
                )
            }
        }
        samplePageRobot(rule) {
            dialogRobot(rule) {
                toolbar {
                    title("Dialog")
                    on("Back") { performClick() }
                    verify { event(Event.OnToolbarAction(Action.Back)) }

                    assertThatCodePageIsNotDisplayed()
                    preview {
                        basicAlertDialogSection {
                            header("Basic Alert Dialog")
                            description("Dialogs provide important prompts")
                            verifyDismissibleDialog()

                            settings {
                                on("Dismissible") { performClick() }
                                on("Mandatory")

                                on("Animate") { performClick() }
                                on("Instant")
                            }

                            verifyMandatoryDialog()
                        }

                        on("Code") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Code)) }
                        showCode = true
                    }

                    assertThatPreviewPageIsNotDisplayed()
                    code {
                        basicAlertDialogSampleCode()

                        on("Preview") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Preview)) }
                    }
                }
            }
        }
        confirmVerified()
    }
}
