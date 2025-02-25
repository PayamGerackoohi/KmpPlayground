package com.payam1991gr.kmp.playground.view.screens.animations.animated.content.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobot
import com.payam1991gr.kmp.playground.view.screens.animations.robot.SampleRobotImpl

interface SimpleSampleRobot {
    fun PreviewScope.simpleSample(): Any
    fun CodeScope.simpleSample(): Any
}

private const val TAG = "Simple"

class SimpleSampleRobotImpl(private val rule: ComposeContentTestRule) : SimpleSampleRobot,
    SampleRobot by SampleRobotImpl(rule, TAG) {
    override fun PreviewScope.simpleSample() = title {
        content(withTag = true) {
            state("Foo")
            performClick()
            state("Bar")
            performClick()
            state("Baz")
            performClick()
            state("Foo")
        }
    }

    override fun CodeScope.simpleSample() {
        title(TAG) { performClick() }
        snippet("fun SimpleSample() {")
    }
}
