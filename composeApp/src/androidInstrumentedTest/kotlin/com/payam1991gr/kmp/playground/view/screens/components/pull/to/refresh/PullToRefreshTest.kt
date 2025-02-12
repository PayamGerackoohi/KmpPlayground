package com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.performClick
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefreshScreen.State
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefreshScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.robot.pullToRefreshRobot
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.state.rememberPtrState
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runTest
import org.junit.Test

@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoroutinesApi::class)
class PullToRefreshTest : BaseTest() {
    @Test
    fun screenTest() {
        var showCode by mutableStateOf(false)
        val event = mockk<(Event) -> Unit> { every { this@mockk(any()) } just runs }
        rule.setContent {
            val state = rememberPtrState()
            PullToRefresh().Content(
                State(
                    showCode = showCode,
                    toolbarActions = persistentListOf(
                        Action.Back,
                        if (showCode) Action.Preview else Action.Code,
                    ),
                    linearProgressIndicator = state,
                    pullToRefreshBox = state,
                    scaling = state,
                    customBehavior = state,
                    event = event,
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            pullToRefreshRobot(rule) {
                toolbar {
                    title("Pull to Refresh")
                    on("Back") { performClick() }
                    verify { event(Event.OnToolbarAction(Action.Back)) }

                    assertThatCodePageIsNotDisplayed()
                    preview {
                        linearProgressIndicatorSample()
                        pullToRefreshBoxSample()
                        scalingSample()
                        customBehaviorSample()

                        on("Code") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Code)) }
                        showCode = true
                    }

                    assertThatPreviewPageIsNotDisplayed()
                    code {
                        pullToRefresh()
                        linearProgressIndicatorSample()
                        pullToRefreshBoxSample()
                        scalingSample()
                        customBehaviorSample()
                        rememberPtrState()
                        module()
                        moduleList()

                        on("Preview") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Preview)) }
                    }
                }
            }
        }
        confirmVerified()
    }

    @Test
    fun linearProgressIndicatorSampleTest() = runTest {
        rule.setContent {
            val state = rememberPtrState(coroutineScope = backgroundScope)
            PullToRefresh().Content(
                State(
                    showCode = false,
                    toolbarActions = persistentListOf(Action.Back, Action.Code),
                    linearProgressIndicator = state,
                    pullToRefreshBox = state,
                    scaling = state,
                    customBehavior = state,
                    event = mockk(),
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            pullToRefreshRobot(rule) {
                preview {
                    linearProgressIndicatorSample {
                        item("5")
                        refresh()
                        advanceTimeBy(501)
                        item("6")
                        pull()
                        advanceTimeBy(501)
                        item("7")
                    }
                }
            }
        }
        confirmVerified()
    }

    @Test
    fun pullToRefreshBoxSampleTest() = runTest {
        rule.setContent {
            val state = rememberPtrState(coroutineScope = backgroundScope)
            PullToRefresh().Content(
                State(
                    showCode = false,
                    toolbarActions = persistentListOf(Action.Back, Action.Code),
                    linearProgressIndicator = state,
                    pullToRefreshBox = state,
                    scaling = state,
                    customBehavior = state,
                    event = mockk(),
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            pullToRefreshRobot(rule) {
                preview {
                    pullToRefreshBoxSample {
                        item("5")
                        refresh()
                        advanceTimeBy(501)
                        item("6")
                        pull()
                        advanceTimeBy(501)
                        item("7")
                    }
                }
            }
        }
        confirmVerified()
    }

    @Test
    fun scalingSampleTest() = runTest {
        rule.setContent {
            val state = rememberPtrState(coroutineScope = backgroundScope)
            PullToRefresh().Content(
                State(
                    showCode = false,
                    toolbarActions = persistentListOf(Action.Back, Action.Code),
                    linearProgressIndicator = state,
                    pullToRefreshBox = state,
                    scaling = state,
                    customBehavior = state,
                    event = mockk(),
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            pullToRefreshRobot(rule) {
                preview {
                    scalingSample {
                        item("5")
                        refresh()
                        advanceTimeBy(501)
                        item("6")
                        pull()
                        advanceTimeBy(501)
                        item("7")
                    }
                }
            }
        }
        confirmVerified()
    }

    @Test
    fun customBehaviorSampleTest() = runTest {
        rule.setContent {
            val state = rememberPtrState(coroutineScope = backgroundScope)
            PullToRefresh().Content(
                State(
                    showCode = false,
                    toolbarActions = persistentListOf(Action.Back, Action.Code),
                    linearProgressIndicator = state,
                    pullToRefreshBox = state,
                    scaling = state,
                    customBehavior = state,
                    event = mockk(),
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            pullToRefreshRobot(rule) {
                preview {
                    customBehaviorSample {
                        item("5")
                        refresh()
                        advanceTimeBy(501)
                        item("6")
                        pull()
                        advanceTimeBy(501)
                        item("7")
                    }
                }
            }
        }
        confirmVerified()
    }
}
