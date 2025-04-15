package com.payam1991gr.kmp.playground.view.screens.io.api.robot

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.PreviewScope
import com.payam1991gr.kmp.playground.view.module.robot.SamplePageRobot.CodeScope
import com.payam1991gr.kmp.playground.view.test.util.SniBlock

interface ApiRobot {
    fun PreviewScope.description(): Any
    fun PreviewScope.configuration(): Any
    fun PreviewScope.configurationDescription(): Any
    fun PreviewScope.fakeModeSettings(block: FakeModeScope.() -> Unit): Any
    fun PreviewScope.hostInput(url: String): Any
    fun PreviewScope.requests(block: RequestsScope.() -> Unit): Any
    fun CodeScope.sharedSample(): Any
    fun CodeScope.stateSample(): Any
    fun CodeScope.presenterSample(): Any
    fun CodeScope.uiSample(): Any

    interface FakeModeScope {
        fun realServerButton(block: SniBlock): Any
        fun fakeServerButton(block: SniBlock): Any
    }

    interface RequestsScope {
        fun header(): Any
        fun getHome(block: GetHomeScope.() -> Unit): Any
        fun getWrittenNumbers(block: GetWrittenNumbersScope.() -> Unit): Any

        interface CardScope {
            fun button(label: String, block: SniBlock = {}): Any
            fun url(value: String): Any
            fun noStatus(): Any
            fun noContent(): Any
            fun status(value: String): Any
            fun content(block: SniBlock): Any
        }

        interface GetHomeScope : CardScope {
            fun content(value: String): Any
        }

        interface GetWrittenNumbersScope : CardScope {
            fun range(value: String): Any
            fun content(vararg values: String): Any
        }
    }
}

fun apiRobot(
    rule: ComposeContentTestRule,
    block: ApiRobot.() -> Unit,
): ApiRobot = ApiRobotImpl(rule).apply(block)
