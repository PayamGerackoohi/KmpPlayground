package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl
import com.payam1991gr.kmp.playground.view.test.util.Sni
import com.payam1991gr.kmp.playground.view.test.util.SniBlock
import com.payam1991gr.kmp.playground.view.test.util.hasState

private const val TAG = "Lazy Row"

class LazyRowRobotImpl(private val rule: ComposeContentTestRule) : LazyRowRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    private fun items(state: String = "[]") = rule
        .onNodeWithTag("$TAG.List")
        .assertExists()
        .hasState("State: $state")

    private fun addButton(block: SniBlock = {}) = rule
        .onNodeWithText("Add")
        .assertIsDisplayed()
        .block()

    private fun clearAllButton(block: SniBlock = {}) = rule
        .onNodeWithText("Clear All")
        .assertIsDisplayed()
        .block()

    private fun item(id: Int, block: ItemScope.() -> Unit = {}) = rule
        .onNodeWithContentDescription("Remove Item: $id")
        .assertIsDisplayed()
        .apply { ItemScopeImpl(this).block() }

    interface ItemScope {
        fun remove(): Any
    }

    class ItemScopeImpl(private val sni: Sni) : ItemScope {
        override fun remove() = sni.performClick()
    }

    override fun PreviewScope.lazyRowSample() = title {
        content(true)
        items()
        addButton {
            performClick()
            items("[1]")
        }
        addButton {
            performClick()
            items("[1, 2]")
        }
        addButton {
            performClick()
            items("[1, 2, 3]")
        }
        item(2) {
            remove()
            items("[1, 3]")
        }
        addButton {
            performClick()
            items("[1, 3, 4]")
        }
        item(1) {
            remove()
            items("[3, 4]")
        }
        clearAllButton { performClick() }
        items()
        performClick()
        noContent(true)
    }

    override fun CodeScope.lazyRowSample() {
        title(TAG) { performClick() }
        snippet("fun LazyRowSample() {")
    }
}
