package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl
import com.payam1991gr.kmp.playground.view.test.util.Sni
import com.payam1991gr.kmp.playground.view.test.util.hasState

private const val TAG = "Floating Action Button"

class FloatingActionButtonRobotImpl(
    private val rule: ComposeContentTestRule,
) : FloatingActionButtonRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    private fun favoriteButton(block: FavoriteButtonScope.() -> Unit = {}) = rule
        .onNodeWithContentDescription("Favorite")
        .assertIsDisplayed()
        .apply { FavoriteButtonScopeImpl(this).block() }

    interface FavoriteButtonScope {
        fun isCollapsed(): Any
        fun isExpanded(): Any
    }

    class FavoriteButtonScopeImpl(private val sni: Sni) : FavoriteButtonScope {
        override fun isCollapsed() = sni.hasState("Collapsed")
        override fun isExpanded() = sni.hasState("Expanded")
    }

    override fun PreviewScope.floatingActionButtonSample() = title {
        favoriteButton { isCollapsed() }
        content(true)
        performClick()
        favoriteButton { isExpanded() }
    }

    override fun CodeScope.animatedFloatingActionButtonSample() {
        title(TAG) { performClick() }
        snippet("fun AnimatedVisibility.FloatingActionButtonSample() {")
    }
}
