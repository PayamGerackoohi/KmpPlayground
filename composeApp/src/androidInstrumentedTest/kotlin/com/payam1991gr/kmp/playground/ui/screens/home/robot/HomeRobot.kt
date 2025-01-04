package com.payam1991gr.kmp.playground.ui.screens.home.robot

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.test.performTouchInput
import androidx.compose.ui.test.swipeUp
import com.payam1991gr.kmp.playground.ui.screens.home.robot.HomeRobot.ItemsScope
import com.payam1991gr.kmp.playground.ui.test.util.SniBlock

interface HomeRobot {
    fun items(block: ItemsScope.() -> Unit): Any

    interface ItemsScope {
        fun find(name: String, block: SniBlock = {}): Any
        fun swipeUp(): Any
    }
}

class HomeRobotImpl(private val rule: ComposeContentTestRule) : HomeRobot {
    override fun items(block: ItemsScope.() -> Unit) = ItemsScopeImpl().apply(block)

    inner class ItemsScopeImpl : ItemsScope {
        override fun find(name: String, block: SniBlock) = rule.onNodeWithText(name)
            .assertIsDisplayed()
            .apply(block)

        override fun swipeUp() = rule.onRoot().performTouchInput { swipeUp() }
    }
}

fun homeRobot(
    rule: ComposeContentTestRule,
    block: HomeRobot.() -> Unit,
) = HomeRobotImpl(rule).apply(block)
