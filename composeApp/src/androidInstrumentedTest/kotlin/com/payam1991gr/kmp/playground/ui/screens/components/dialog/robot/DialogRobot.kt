package com.payam1991gr.kmp.playground.ui.screens.components.dialog.robot

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.robot.DialogRobot.BasicAlertDialogScope
import com.payam1991gr.kmp.playground.ui.test.util.SniBlock
import com.payam1991gr.kmp.playground.ui.test.util.uiDevice

interface DialogRobot {
    fun basicAlertDialogSection(block: BasicAlertDialogScope.() -> Unit): Any
    fun basicAlertDialogSampleCode(): Any

    interface BasicAlertDialogScope {
        fun showButton(block: SniBlock = {}): Any
        fun dialog(block: DialogScope.() -> Unit): Any
        fun assertThatDialogIsNotShown(): Any
        fun verifyDismissibleDialog()
        fun verifyMandatoryDialog()

        interface DialogScope {
            fun content(): Any
            fun confirmButton(block: SniBlock = {}): Any
            fun clickOutside(): Any
        }
    }
}

class DialogRobotImpl(private val rule: ComposeContentTestRule) : DialogRobot {
    override fun basicAlertDialogSection(block: BasicAlertDialogScope.() -> Unit) =
        BasicAlertDialogScopeImpl().block()

    override fun basicAlertDialogSampleCode() = rule.apply {
        onNodeWithText("@OptIn(ExperimentalMaterial3Api::class)", substring = true)
            .assertIsDisplayed()
        onNodeWithText("@Composable", substring = true)
            .assertIsDisplayed()
        onNodeWithText("fun BasicAlertDialogSample() = Column(", substring = true)
            .assertIsDisplayed()
    }

    inner class BasicAlertDialogScopeImpl : BasicAlertDialogScope {
        override fun showButton(block: SniBlock) = rule
            .onNodeWithTag("BasicAlertDialog.Show")
            .assertIsDisplayed()
            .block()

        override fun dialog(block: BasicAlertDialogScope.DialogScope.() -> Unit) = rule
            .onNodeWithTag("BasicAlertDialogSample")
            .assertIsDisplayed()
            .apply { DialogScopeImpl().block() }

        override fun assertThatDialogIsNotShown() = rule
            .onAllNodesWithTag("BasicAlertDialogSample")
            .assertCountEquals(0)

        override fun verifyDismissibleDialog() {
            assertThatDialogIsNotShown()
            showButton { performClick() }
            dialog {
                content()
                confirmButton { performClick() }
            }
            assertThatDialogIsNotShown()
            showButton { performClick() }
            dialog {
                content()
                clickOutside()
            }
            assertThatDialogIsNotShown()
        }

        override fun verifyMandatoryDialog() {
            assertThatDialogIsNotShown()
            showButton { performClick() }
            dialog {
                content()
                clickOutside()
                confirmButton { performClick() }
            }
            assertThatDialogIsNotShown()
        }

        inner class DialogScopeImpl : BasicAlertDialogScope.DialogScope {
            override fun content() = rule.onNodeWithText("This area typically", substring = true)
                .assertIsDisplayed()

            override fun confirmButton(block: SniBlock) = rule.onNodeWithText("Confirm")
                .assertIsDisplayed()
                .block()

            // t o d o actually not clicking outside!
//            override fun clickOutside() = rule.onRoot().performTouchInput { click(Offset.Zero) } // not working
//            override fun clickOutside() = rule.onAllNodes(isRoot())[0].performTouchInput { click(Offset.Zero) } // not working
//            override fun clickOutside() = rule.onAllNodes(isRoot())[1].performTouchInput { click(Offset.Zero) } // not working
//            override fun clickOutside() = rule.onNodeWithTag("TestRoot").performTouchInput { click(Offset.Zero) } // not working
//            override fun clickOutside() = uiDevice.click(0, 0) // not working
//            override fun clickOutside() = rule.onAllNodes(isRoot())[0].performKeyPress(KeyEvent(NativeKeyEvent(NativeKeyEvent.ACTION_DOWN, NativeKeyEvent.KEYCODE_BACK))) // not working
//            override fun clickOutside() = rule.onAllNodes(isRoot())[1].performKeyPress(KeyEvent(NativeKeyEvent(NativeKeyEvent.ACTION_DOWN, NativeKeyEvent.KEYCODE_BACK))) // not working
            override fun clickOutside() = uiDevice.pressBack()
        }
    }
}

fun dialogRobot(
    rule: ComposeContentTestRule,
    block: DialogRobot.() -> Unit,
): DialogRobot = DialogRobotImpl(rule).apply(block)
