package com.payam1991gr.kmp.playground.view.screens.io.datastore

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.test.performClick
import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.module.robot.samplePageRobot
import com.payam1991gr.kmp.playground.view.screens.io.datastore.DatastoreScreen.State
import com.payam1991gr.kmp.playground.view.screens.io.datastore.DatastoreScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.io.datastore.robot.datastoreRobot
import com.payam1991gr.kmp.playground.view.test.util.BaseTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.slot
import io.mockk.verify
import kotlinx.collections.immutable.persistentListOf
import org.junit.Test

class DatastoreTest : BaseTest() {
    @Test
    fun test() {
        var showCode by mutableStateOf(false)
        val eventSlot = slot<Event>()
        val event = mockk<(Event) -> Unit> { every { this@mockk(capture(eventSlot)) } just runs }
        var requestClearFocus by mutableStateOf(false)
        val clearFocus = { requestClearFocus = true }
        rule.setContent {
            if (requestClearFocus) {
                LocalFocusManager.current.clearFocus()
                requestClearFocus = false
            }
            Datastore().Content(
                State(
                    showCode = showCode,
                    toolbarActions = persistentListOf(
                        Action.Back,
                        if (showCode) Action.Preview else Action.Code,
                    ),
                    int = 1,
                    float = 2f,
                    byteArray = byteArrayOf(3, 4),
                    event = event,
                ),
                Modifier,
            )
        }
        samplePageRobot(rule) {
            datastoreRobot(rule) {
                toolbar {
                    title("Datastore")
                    on("Back") { performClick() }
                    verify { event(Event.OnToolbarAction(Action.Back)) }

                    assertThatCodePageIsNotDisplayed()
                    preview {
                        intItem {
                            title("Int 1")
                            input {
                                hasText("1")
                                noError()
                                saveButton(isEnabled = false)

                                putText("3000000000")
                                hasError()
                                saveButton(isEnabled = false)

                                putText("10")
                                noError()
                                saveButton { performClick() }
                                verify { event(Event.OnItemChanged(10)) }
                            }
                        }
                        floatItem {
                            title("Float 2.0")
                            input {
                                hasText("2.0")
                                noError()
                                saveButton(isEnabled = false)

                                putText("20")
                                noError()
                                saveButton { performClick() }
                                verify { event(Event.OnItemChanged(20f)) }
                            }
                        }
                        byteArrayItem {
                            title("Byte Array [3,4]")
                            input {
                                hasText("[3,4]")
                                noError()
                                saveButton(isEnabled = false)

                                putText("200")
                                hasError()
                                saveButton(isEnabled = false)

                                putText("30,40")
                                clearFocus()
                                noError()
                                saveButton { performClick() }
                                @Suppress("UNCHECKED_CAST")
                                (eventSlot.captured as Event.OnItemChanged<ByteArray>).value.let { array ->
                                    assertThat(array.contentEquals(byteArrayOf(30, 40))).isTrue()
                                }
                                verify { event(any()) }
                                // verify { event(Event.OnItemChanged<ByteArray>(any())) } // t o d o not working
                                // verify { event(Event.OnItemChanged(any<ByteArray>())) } // t o d o not working
                            }
                        }

                        on("Code") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Code)) }
                        showCode = true
                    }

                    assertThatPreviewPageIsNotDisplayed()
                    code {
                        intItem()
                        floatItem()
                        byteArrayItem()
                        textFieldInput()
                        converters()
                        state()

                        on("Preview") { performClick() }
                        verify { event(Event.OnToolbarAction(Action.Preview)) }
                    }
                }
            }
        }
        confirmVerified()
    }
}
