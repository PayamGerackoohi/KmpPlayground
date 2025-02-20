package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl
import com.payam1991gr.kmp.playground.view.test.util.hasState

private const val TAG = "Row"

class RowRobotImpl(private val rule: ComposeContentTestRule) : RowRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    override fun PreviewScope.rowSample() = (title() to content(true)).let { (title, content) ->
        content.hasState("State 1")
        title.performClick()
        content.hasState("State 2")
        title.performClick()
        content.hasState("State 3")
        title.performClick()
        content.hasState("State 1")
        content.performClick()
        content.hasState("State 2")
        content.performClick()
        content.hasState("State 3")
        content.performClick()
        content.hasState("State 1")
    }

    override fun CodeScope.rowSample() {
        title(TAG) { performClick() }
        snippet("fun RowSample() {")
    }
}
