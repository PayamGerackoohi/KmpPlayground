package com.payam1991gr.kmp.playground.view.screens.graphics.icons.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.test.util.SniBlock

interface IconsRobot {
    fun core(block: CoreScope.() -> Unit): Any
    fun extended(block: ExtendedScope.() -> Unit): Any
    fun snackbar(text: String): Any
    fun CodeScope.previewSample(): Any
    fun CodeScope.state(): Any
    fun CodeScope.lazyData(): Any
    fun CodeScope.iconData(): Any
    fun CodeScope.lazyGridScope_header(): Any
    fun CodeScope.lazyGridScope_icons(): Any
    fun CodeScope.iconSample(): Any
    fun CodeScope.loading(): Any
    fun CodeScope.state_ObserveEffects(): Any

    interface IconScope {
        fun icon(title: String, block: SniBlock = {}): Any
    }

    interface CoreScope : IconScope
    interface ExtendedScope : IconScope
}

fun iconsRobot(
    rule: ComposeContentTestRule,
    block: IconsRobot.() -> Unit,
): IconsRobot = IconsRobotImpl(rule).apply(block)
