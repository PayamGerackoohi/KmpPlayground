package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope

class AnimatedVisibilityRobotImpl(
    private val rule: ComposeContentTestRule,
) : AnimatedVisibilityRobot,
    HorizontalTransitionRobot by HorizontalTransitionRobotImpl(rule),
    SlideTransitionRobot by SlideTransitionRobotImpl(rule),
    FadeTransitionRobot by FadeTransitionRobotImpl(rule),
    FullyLoadedTransitionRobot by FullyLoadedTransitionRobotImpl(rule),
    BooleanVisibleParamNoReceiverRobot by BooleanVisibleParamNoReceiverRobotImpl(rule),
    FloatingActionButtonRobot by FloatingActionButtonRobotImpl(rule),
    SlideInOutRobot by SlideInOutRobotImpl(rule),
    ExpandShrinkVerticallyRobot by ExpandShrinkVerticallyRobotImpl(rule),
    ExpandInShrinkOutRobot by ExpandInShrinkOutRobotImpl(rule),
    RowRobot by RowRobotImpl(rule),
    ScopeAnimateEnterExitRobot by ScopeAnimateEnterExitRobotImpl(rule),
    AddingToGenericTransitionRobot by AddingToGenericTransitionRobotImpl(rule),
    LazyRowRobot by LazyRowRobotImpl(rule),
    RowScopeWithMutableTransitionStateRobot by RowScopeWithMutableTransitionStateRobotImpl(rule),
    AnimateEnterExitPartialContentRobot by AnimateEnterExitPartialContentRobotImpl(rule),
    ScaledEnterExitRobot by ScaledEnterExitRobotImpl(rule) {
    override fun CodeScope.shared() {
        title("Shared") { performClick() }
        snippet("fun rememberBoolean(init: Boolean = true) = remember { mutableStateOf(init)")
        snippet("object Module {")
        snippet("fun Modifier.moduleSize(ratio: Float = 2f) = this")
    }
}
