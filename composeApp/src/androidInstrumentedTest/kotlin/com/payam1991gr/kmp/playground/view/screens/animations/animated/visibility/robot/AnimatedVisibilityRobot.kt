package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope

interface AnimatedVisibilityRobot :
    HorizontalTransitionRobot,
    SlideTransitionRobot,
    FadeTransitionRobot,
    FullyLoadedTransitionRobot,
    BooleanVisibleParamNoReceiverRobot,
    FloatingActionButtonRobot,
    SlideInOutRobot,
    ExpandShrinkVerticallyRobot,
    ExpandInShrinkOutRobot,
    RowRobot,
    ScopeAnimateEnterExitRobot,
    AddingToGenericTransitionRobot,
    LazyRowRobot,
    RowScopeWithMutableTransitionStateRobot,
    AnimateEnterExitPartialContentRobot,
    ScaledEnterExitRobot {
    fun CodeScope.shared(): Any
}

fun animatedVisibilityRobot(
    rule: ComposeContentTestRule,
    block: AnimatedVisibilityRobot.() -> Unit,
): AnimatedVisibilityRobot = AnimatedVisibilityRobotImpl(rule).apply(block)
