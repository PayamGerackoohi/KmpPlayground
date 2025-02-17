package com.payam1991gr.kmp.playground.view.module.robot

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.hasTextExactly
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.SettingsScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.ToolbarScope
import com.payam1991gr.kmp.playground.view.test.util.Sni
import com.payam1991gr.kmp.playground.view.test.util.SniBlock

interface SamplePageRobot {
    fun toolbar(block: ToolbarScope.() -> Unit = {}): Any
    fun header(title: String): Any
    fun description(description: String): Any
    fun preview(block: PreviewScope.() -> Unit = {}): Any
    fun code(block: CodeScope.() -> Unit = {}): Any
    fun assertThatPreviewPageIsNotDisplayed(): Any
    fun assertThatCodePageIsNotDisplayed(): Any
    fun settings(block: SettingsScope.() -> Unit): Any

    interface ToolbarScope {
        fun title(string: String): Any
        fun on(actionLabel: String, block: SniBlock = {}): Any
    }

    interface SettingsScope {
        fun on(label: String, block: SniBlock = {}): Any
    }

    interface PreviewScope {
        val sni: Sni
    }

    interface CodeScope {
        val sni: Sni
        fun title(text: String, block: SniBlock = {})
        fun snippet(text: String): Any
    }
}

class SamplePageRobotImpl(private val rule: ComposeContentTestRule) : SamplePageRobot {
    override fun toolbar(block: ToolbarScope.() -> Unit) = rule
        .onNodeWithTag("SamplePage.Toolbar")
        .assertIsDisplayed()
        .apply { ToolbarScopeImpl(this).block() }

    override fun header(title: String) = rule
        .onNodeWithText(title)
        .assertIsDisplayed()

    override fun description(description: String) = rule
        .onNodeWithText(description, substring = true)
        .assertIsDisplayed()

    override fun preview(block: PreviewScope.() -> Unit) = rule
        .onNodeWithTag("SamplePage.Preview")
        .onChild()
        .assertIsDisplayed()
        .apply { PreviewScopeImpl(this).block() }

    override fun code(block: CodeScope.() -> Unit) = rule
        .onNodeWithTag("SamplePage.Code")
        .onChild()
        .assertIsDisplayed()
        .apply { CodeScopeImpl(this).block() }

    override fun assertThatPreviewPageIsNotDisplayed() = rule
        .onAllNodesWithTag("SamplePage.Preview")
        .assertCountEquals(0)

    override fun assertThatCodePageIsNotDisplayed() = rule
        .onAllNodesWithTag("SamplePage.Code")
        .assertCountEquals(0)

    override fun settings(block: SettingsScope.() -> Unit) = rule.onNode(
        hasAnyChild(hasTextExactly("Settings"))
    ).assertIsDisplayed().apply {
        SettingsScopeImpl(this).block()
    }

    inner class ToolbarScopeImpl(private val sni: Sni) : ToolbarScope {
        override fun title(string: String) = sni.onChildren()
            .filterToOne(hasTextExactly(string))
            .assertIsDisplayed()

        override fun on(actionLabel: String, block: SniBlock) = sni.onChildren()
            .filterToOne(hasContentDescription(actionLabel))
            .assertIsDisplayed()
            .block()
    }

    inner class SettingsScopeImpl(private val sni: Sni) : SettingsScope {
        override fun on(label: String, block: SniBlock) = sni.onChildren()
            .filterToOne(hasTextExactly(label))
            .assertIsDisplayed()
            .block()
    }

    inner class PreviewScopeImpl(override val sni: Sni) : PreviewScope

    inner class CodeScopeImpl(override val sni: Sni) : CodeScope {
        override fun title(text: String, block: SniBlock) {
            sni.performScrollToNode(hasText(text))
            rule.onNodeWithText(text)
                .assertIsDisplayed()
                .block()
        }

        override fun snippet(text: String) {
            sni.performScrollToNode(hasText(text, substring = true))
            rule.onNodeWithText(text, substring = true)
                .assertIsDisplayed()
        }
    }
}

fun samplePageRobot(
    rule: ComposeContentTestRule,
    block: SamplePageRobot.() -> Unit,
): SamplePageRobot = SamplePageRobotImpl(rule).apply(block)
