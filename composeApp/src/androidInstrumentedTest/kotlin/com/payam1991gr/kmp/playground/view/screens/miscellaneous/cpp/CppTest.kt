package com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.data.math.Factor
import com.payam1991gr.kmp.playground.data.string.decimalFormat
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen.State
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen.State.Number
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.robot.cppRobot
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test

class CppTest : BaseTest() {
    @Test
    fun initStateTest() {
        var showCode by mutableStateOf(false)
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            Cpp().Content(
                State(
                    showCode = showCode,
                    toolbarActions = persistentListOf(
                        Action.Back,
                        if (showCode) Action.Preview else Action.Code,
                    ),
                    canEdit = true,
                    canCalculate = true,
                    inputNumber = "",
                    inputCalculation = Number.Data(),
                    calculations = persistentListOf(),
                    event = event,
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            cppRobot(rule) {
                toolbar {
                    title("C++")
                    on("Back") { performClick() }
                    verify { event(Event.OnToolbarAction(Action.Back)) }

                    assertThatCodePageIsNotDisplayed()
                    preview {
                        description()
                        number {
                            title()
                            input { hasError() }
                            confirmButton(false, "Calculate")
                        }
                        randomCalculations {
                            button(true) {
                                performClick()
                                verify { event(Event.PerformRandomCalculations) }
                            }
                            items()
                        }

                        on("Code") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Code)) }
                        showCode = true
                    }
                    assertThatPreviewPageIsNotDisplayed()
                    code {
                        ui()
                        state()
                        presenter()
                        cpp()

                        on("Preview") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Preview)) }
                    }
                }
            }
        }
        confirmVerified()
    }

    @Test
    fun casualDataTest() {
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        val n = "12345654321"
        rule.setContent {
            Cpp().Content(
                State(
                    showCode = false,
                    toolbarActions = persistentListOf(Action.Back, Action.Code),
                    canEdit = true,
                    canCalculate = false,
                    inputNumber = n,
                    inputCalculation = remember {
                        Number.Data(
                            n, persistentListOf(
                                Factor("3", "2"),
                                Factor("7", "2"),
                                Factor("11", "2"),
                                Factor("13", "2"),
                                Factor("37", "2"),
                            )
                        )
                    },
                    calculations = remember {
                        persistentListOf(
                            Number.Data(
                                "1000".decimalFormat(),
                                persistentListOf(Factor("2", "3"), Factor("5", "3")),
                            ),
                            Number.Data(
                                "277945762500".decimalFormat(),
                                persistentListOf(
                                    Factor("2", "2"),
                                    Factor("3", "3"),
                                    Factor("5", "5"),
                                    Factor("7", "7"),
                                ),
                            ),
                            Number.Calculating("6210722251338751269".decimalFormat()),
                        )
                    },
                    event = event,
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            cppRobot(rule) {
                preview {
                    description()
                    number {
                        title("32 ⨉ 72 ⨉ 112 ⨉ 132 ⨉ 372") // t*do use proper content description for accessibility use cases
                        input {
                            noError()
                            hasText("12,345,654,321", "Number", "≈1.2e10")
                            confirmButton(isEnabled = false, "Calculate")

                            putText("2357")
                            hasText("2,357", "Number", "≈2.4e3")
                            confirmButton(true, "Calculate") {
                                performClick()
                                verify { event(Event.OnNumberChanged("2357")) }
                            }
                        }
                    }
                    randomCalculations {
                        button(false)
                        items {
                            calculated("1,000 = 23 ⨉ 53")
                            calculated("277,945,762,500 = 22 ⨉ 33 ⨉ 55 ⨉ 77")
                            calculating("6,210,722,251,338,751,269")
                        }
                    }
                }
            }
        }
        confirmVerified()
    }
}
