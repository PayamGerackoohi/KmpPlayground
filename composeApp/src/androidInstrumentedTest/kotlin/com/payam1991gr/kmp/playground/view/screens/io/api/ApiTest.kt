package com.payam1991gr.kmp.playground.view.screens.io.api

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.data.model.RemoteData
import com.payam1991gr.kmp.playground.data.model.RemoteData.Error
import com.payam1991gr.kmp.playground.data.model.RemoteData.Data
import com.payam1991gr.kmp.playground.data.model.RemoteData.Init
import com.payam1991gr.kmp.playground.data.model.WrittenNumber
import com.payam1991gr.kmp.playground.presenter.screens.io.api.Numbers
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.view.screens.io.api.ApiScreen.State
import com.payam1991gr.kmp.playground.view.screens.io.api.ApiScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.io.api.robot.apiRobot
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test

class ApiTest : BaseTest() {
    @Test
    fun initialStateTest() {
        var showCode by mutableStateOf(false)
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        var host by mutableStateOf("http://base-server.com")
        rule.setContent {
            Api().Content(
                State(
                    showCode = showCode,
                    toolbarActions = persistentListOf(
                        Action.Back,
                        if (showCode) Action.Preview else Action.Code,
                    ),
                    true,
                    host = host,
                    home = Init,
                    writtenNumbers = Init,
                    event = event,
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            apiRobot(rule) {
                toolbar {
                    title("API")
                    on("Back") { performClick() }
                    verify { event(Event.OnToolbarAction(Action.Back)) }

                    assertThatCodePageIsNotDisplayed()
                    preview {
                        description()
                        configuration()
                        configurationDescription()
                        fakeModeSettings {
                            verify { event(Event.OnServerModeChanged(true)) }
                            realServerButton {
                                hostInput("http://base-server.com")

                                performClick()
                            }
                            fakeServerButton {
                                verify { event(Event.OnServerModeChanged(false)) }
                                host = "http://fake-server.com"
                                hostInput("http://fake-server.com")

                                performClick()
                            }
                            verify { event(Event.OnServerModeChanged(true)) }
                            host = "http://server.com"
                        }
                        hostInput("http://server.com")
                        requests {
                            header()
                            getHome {
                                button("GET")
                                url("http://server.com")
                                noStatus()
                                noContent()
                            }
                            getWrittenNumbers {
                                button("GET")
                                url("http://server.com/written-numbers?from=0&to=3")
                                range("0..3")
                                noStatus()
                                noContent()
                            }
                        }

                        on("Code") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Code)) }
                        showCode = true
                    }

                    assertThatPreviewPageIsNotDisplayed()
                    code {
                        sharedSample()
                        stateSample()
                        presenterSample()
                        uiSample()

                        on("Preview") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Preview)) }
                    }
                }
            }
            verify { event(Event.OnServerModeChanged(true)) }
            confirmVerified()
        }
    }

    @Test
    fun getHomeTest() {
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        var home by mutableStateOf<RemoteData<String>>(Init)
        rule.setContent {
            Api().Content(
                State(
                    showCode = false,
                    toolbarActions = persistentListOf(Action.Back, Action.Code),
                    true,
                    host = "http://server.com",
                    home = home,
                    writtenNumbers = Init,
                    event = event,
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            apiRobot(rule) {
                toolbar {
                    preview {
                        verify { event(Event.OnServerModeChanged(true)) }
                        requests {
                            getHome {
                                noStatus()
                                noContent()

                                button("GET") { performClick() }
                                verify { event(Event.CallHomeApi) }
                                home = Error("Error!", 123)
                                status("123")
                                content("Error!")

                                button("GET") { performClick() }
                                verify { event(Event.CallHomeApi) }
                                home = Data("Hello!", 200)
                                status("200")
                                content("Hello!")
                            }
                        }
                    }
                }
            }
            confirmVerified()
        }
    }

    @Test
    fun getWrittenNumbersTest() {
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        var writtenNumbers by mutableStateOf<RemoteData<Numbers>>(Init)
        rule.setContent {
            Api().Content(
                State(
                    showCode = false,
                    toolbarActions = persistentListOf(Action.Back, Action.Code),
                    true,
                    host = "http://server.com",
                    home = Init,
                    writtenNumbers = writtenNumbers,
                    event = event,
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            apiRobot(rule) {
                toolbar {
                    preview {
                        verify { event(Event.OnServerModeChanged(true)) }
                        requests {
                            getWrittenNumbers {
                                noStatus()
                                noContent()

                                button("GET") { performClick() }
                                verify { event(Event.CallWrittenNumbersApi(0, 3)) }
                                writtenNumbers = Error("Error!", 123)
                            }
                            getWrittenNumbers {
                                status("123")
                                content("Error!")

                                button("GET") { performClick() }
                                verify { event(Event.CallWrittenNumbersApi(0, 3)) }
                                writtenNumbers = Data(List(4) { WrittenNumber.of(it) }, 200)
                            }
                            getWrittenNumbers {
                                status("200")
                                content(
                                    "(0, Zero)",
                                    "(1, One)",
                                    "(2, Two)",
                                    "(3, Three)",
                                )
                            }
                        }
                    }
                }
            }
            confirmVerified()
        }
    }
}
