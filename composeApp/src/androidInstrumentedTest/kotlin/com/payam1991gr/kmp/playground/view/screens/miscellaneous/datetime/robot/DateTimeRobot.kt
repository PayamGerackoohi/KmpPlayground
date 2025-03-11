package com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope

interface DateTimeRobot {
    fun PreviewScope.card(tag: String, block: CardScope.() -> Unit): Any
    fun CodeScope.analogClockCard()
    fun CodeScope.analogClock()
    fun CodeScope.timeZoneData()

    interface CardScope {
        fun title(): Any
        fun date(value: String): Any
        fun time(value: String): Any
        fun utc(value: String): Any
    }
}

fun dateTimeRobot(
    rule: ComposeContentTestRule,
    block: DateTimeRobot.() -> Unit,
) = DateTimeRobotImpl(rule).block()
