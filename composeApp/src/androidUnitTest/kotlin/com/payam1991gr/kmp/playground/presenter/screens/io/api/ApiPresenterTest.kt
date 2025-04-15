package com.payam1991gr.kmp.playground.presenter.screens.io.api

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.data.model.RemoteData
import com.payam1991gr.kmp.playground.data.model.RemoteData.Connecting
import com.payam1991gr.kmp.playground.data.model.RemoteData.Data
import com.payam1991gr.kmp.playground.data.model.RemoteData.Init
import com.payam1991gr.kmp.playground.data.model.RemoteData.Error
import com.payam1991gr.kmp.playground.data.model.WrittenNumber
import com.payam1991gr.kmp.playground.presenter.screens.io.api.robot.robot
import com.payam1991gr.kmp.playground.util.SimpleApi
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.io.api.ApiScreen
import com.payam1991gr.kmp.playground.view.screens.io.api.ApiScreen.State.Event
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class ApiPresenterTest {
    @Test
    fun `init state - test`() = runTest {
        val navigator = FakeNavigator(ApiScreen)
        val realApi = object : SimpleApi() {
            override var host by mutableStateOf("http://real.com")
        }
        val fakeApi = object : SimpleApi() {
            override var host = "http://fake.com"
        }
        ApiPresenter(navigator, realApi, fakeApi).robot {
            onState(false, Action.Back, Action.Code) {
                event(Event.OnToolbarAction(Action.Back))
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(null))

                event(Event.OnToolbarAction(Action.Code))
            }
            onState(true, Action.Back, Action.Preview) {
                event(Event.OnToolbarAction(Action.Preview))
            }
            onState(false, Action.Back, Action.Code) {
                assertThat(shouldUseRealApi).isTrue()
                assertThat(host).isEqualTo("http://real.com")
                assertThat(home).isEqualTo(Init)
                assertThat(writtenNumbers).isEqualTo(Init)
                event(Event.OnHostChanged("http://real2.com"))
            }
            onState {
                assertThat(host).isEqualTo("http://real2.com")
                event(Event.OnServerModeChanged(false))
            }
            onState {
                assertThat(shouldUseRealApi).isFalse()
                assertThat(host).isEqualTo("http://fake.com")
            }
        }
    }

    @Test
    fun `Call - OK - test`() = runTest {
        val navigator = FakeNavigator(ApiScreen)
        val homeFlow = MutableStateFlow<RemoteData<String>>(Connecting)
        val numbersFlow = MutableStateFlow<RemoteData<Numbers>>(Connecting)
        val api = object : SimpleApi() {
            override fun home() = homeFlow
            override fun writtenNumbers(from: Int?, to: Int?) = numbersFlow
        }
        ApiPresenter(navigator, api, api).robot {
            onState { event(Event.CallHomeApi) }
            onState {
                assertThat(home).isEqualTo(Connecting)
                homeFlow.value = Data("Hello from fake host!")
            }
            onState {
                assertThat(home).isEqualTo(Data("Hello from fake host!"))
                event(Event.CallWrittenNumbersApi(2, 2))
            }
            onState {
                assertThat(writtenNumbers).isEqualTo(Connecting)
                numbersFlow.value = Data(listOf(WrittenNumber(2, "Two")), 200)
            }
            onState {
                assertThat(writtenNumbers)
                    .isEqualTo(Data(listOf(WrittenNumber(2, "Two")), 200))
            }
        }
    }

    @Test
    fun `Call - Error - test`() = runTest {
        val navigator = FakeNavigator(ApiScreen)
        val homeFlow = MutableStateFlow<RemoteData<String>>(Connecting)
        val numbersFlow = MutableStateFlow<RemoteData<Numbers>>(Connecting)
        val api = object : SimpleApi() {
            override fun home() = homeFlow
            override fun writtenNumbers(from: Int?, to: Int?) = numbersFlow
        }
        ApiPresenter(navigator, api, api).robot {
            onState { event(Event.CallHomeApi) }
            onState {
                assertThat(home).isEqualTo(Connecting)
                homeFlow.value = Error("Home Error", 123)
            }
            onState {
                assertThat(home).isEqualTo(Error("Home Error", 123))
                event(Event.CallWrittenNumbersApi(2, 2))
            }
            onState {
                assertThat(writtenNumbers).isEqualTo(Connecting)
                numbersFlow.value = Error("Written Numbers Error", 321)
            }
            onState { assertThat(writtenNumbers).isEqualTo(Error("Written Numbers Error", 321)) }
        }
    }
}
