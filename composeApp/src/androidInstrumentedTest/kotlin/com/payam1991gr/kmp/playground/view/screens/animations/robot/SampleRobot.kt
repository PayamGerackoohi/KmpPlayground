package com.payam1991gr.kmp.playground.view.screens.animations.robot

import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyAncestor
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToNode
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot.ContentScope
import com.payam1991gr.kmp.playground.view.test.util.Sni
import com.payam1991gr.kmp.playground.view.test.util.SniBlock
import com.payam1991gr.kmp.playground.view.test.util.hasState

interface SampleRobot {
    fun PreviewScope.title(block: SniBlock = {}): Sni
    fun PreviewScope.content(withTag: Boolean = false, block: ContentScope.() -> Unit = {}): Sni
    fun noContent(withTag: Boolean = false): Any
    fun hasModuleAsAncestor(tag: String): SemanticsMatcher

    interface ContentScope {
        fun state(value: String): Any
    }
}

class SampleRobotImpl(
    private val rule: ComposeContentTestRule,
    private val title: String,
) : SampleRobot {
    private val contentTag = "$title.Content"

    override fun PreviewScope.title(block: SniBlock): Sni {
        sni.performScrollToNode(hasText(title))
        return rule
            .onNodeWithText(title)
            .assertIsDisplayed()
            .apply(block)
    }

    override fun PreviewScope.content(withTag: Boolean, block: ContentScope.() -> Unit) =
        if (withTag) {
            sni.performScrollToNode(hasTestTag(contentTag))
            rule.onNodeWithTag(contentTag)
        } else {
            sni.performScrollToNode(hasContentDescription(contentTag))
            rule.onNodeWithContentDescription(contentTag)
        }.assertIsDisplayed().apply {
            ContentScopeImpl(this).block()
        }

    override fun noContent(withTag: Boolean) = rule.run {
        if (withTag) onAllNodesWithTag(contentTag)
        else onAllNodesWithContentDescription(contentTag)
    }.assertCountEquals(0)

    inner class ContentScopeImpl(private val sni: Sni) : ContentScope {
        override fun state(value: String) = sni
            .hasState(value)
            .assertIsDisplayed()
    }

    override fun hasModuleAsAncestor(tag: String) = hasAnyAncestor(hasTestTag("Module.$tag"))
}
