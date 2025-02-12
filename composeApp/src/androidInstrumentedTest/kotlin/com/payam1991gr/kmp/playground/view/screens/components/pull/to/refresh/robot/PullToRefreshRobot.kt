package com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope

typealias ModuleScopeBlock = PullToRefreshRobot.ModuleScope.() -> Unit

interface PullToRefreshRobot {
    fun PreviewScope.linearProgressIndicatorSample(block: ModuleScopeBlock = {})
    fun PreviewScope.pullToRefreshBoxSample(block: ModuleScopeBlock = {})
    fun PreviewScope.scalingSample(block: ModuleScopeBlock = {})
    fun PreviewScope.customBehaviorSample(block: ModuleScopeBlock = {})
    fun CodeScope.pullToRefresh():Any
    fun CodeScope.linearProgressIndicatorSample(): Any
    fun CodeScope.pullToRefreshBoxSample(): Any
    fun CodeScope.scalingSample(): Any
    fun CodeScope.customBehaviorSample(): Any
    fun CodeScope.rememberPtrState(): Any
    fun CodeScope.module(): Any
    fun CodeScope.moduleList(): Any
    interface ModuleScope {
        fun item(label: String): Any
        fun refresh(): Any
        fun pull(): Any
    }
}

fun pullToRefreshRobot(
    rule: ComposeContentTestRule,
    block: PullToRefreshRobot.() -> Unit,
): PullToRefreshRobot = PullToRefreshRobotImpl(rule).apply(block)
