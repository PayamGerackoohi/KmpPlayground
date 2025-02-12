package com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.robot

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToNode
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeDown
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.robot.PullToRefreshRobot.ModuleScope
import com.payam1991gr.kmp.playground.view.test.util.Sni
import com.payam1991gr.kmp.playground.view.test.util.onDescendant

class PullToRefreshRobotImpl(private val rule: ComposeContentTestRule) : PullToRefreshRobot {
    private fun PreviewScope.title(label: String, scrollTo: Boolean = true) {
        if (scrollTo) sni.performScrollToNode(hasText(label))
        rule.onNodeWithText(label)
            .assertIsDisplayed()
    }

    private fun PreviewScope.module(label: String, block: ModuleScopeBlock) {
        title(label)
        val module = rule.onNodeWithTag("Module.$label").assertIsDisplayed()
        val list = rule.onNodeWithTag("Module.List.$label").assertIsDisplayed()
        ModuleScopeImpl(module, list).block()
    }

    override fun PreviewScope.linearProgressIndicatorSample(block: ModuleScopeBlock) =
        module("Linear Progress Indicator", block)

    private fun ComposeTestRule.hasCodeSnippet(text: String) =
        onNodeWithText(text, true).assertIsDisplayed()

    override fun CodeScope.linearProgressIndicatorSample() = rule.hasCodeSnippet(
        "private fun State.PtrState.LinearProgressIndicatorSample() = PullToRefresh("
    )

    override fun PreviewScope.pullToRefreshBoxSample(block: ModuleScopeBlock) {
        module("Pull to Refresh Box", block)
    }

    override fun CodeScope.pullToRefreshBoxSample() = rule.hasCodeSnippet(
        "private fun State.PtrState.PullToRefreshBoxSample() = PullToRefresh("
    )

    override fun PreviewScope.scalingSample(block: ModuleScopeBlock) {
        module("Scaling", block)
    }

    override fun CodeScope.scalingSample() = rule.hasCodeSnippet(
        "private fun State.PtrState.ScalingSample() {"
    )

    override fun PreviewScope.customBehaviorSample(block: ModuleScopeBlock) {
        module("Custom Behavior", block)
    }

    override fun CodeScope.customBehaviorSample() = rule.hasCodeSnippet(
        "private fun State.PtrState.CustomBehaviorSample() {"
    )

    override fun CodeScope.rememberPtrState() = rule.hasCodeSnippet(
        "fun rememberPtrState("
    )

    override fun CodeScope.pullToRefresh() = rule.hasCodeSnippet(
        "private fun State.PtrState.PullToRefresh("
    )

    override fun CodeScope.module() = rule.hasCodeSnippet(
        "private fun Modifier.module(tag: String) = this"
    )

    override fun CodeScope.moduleList() = rule.hasCodeSnippet(
        "private fun Modifier.moduleList(tag: String) = this"
    )

    inner class ModuleScopeImpl(private val sni: Sni, private val list: Sni) : ModuleScope {
        override fun item(label: String) = sni
            .assert(hasAnyDescendant(hasText("Item $label")))

        override fun refresh() = sni
            .onDescendant(hasContentDescription("Trigger Refresh"), rule)
            .assertIsDisplayed()
            .performClick()

        override fun pull() {
            list.performTouchInput { swipeDown() }
            rule.waitForIdle()
        }
    }
}
