package com.payam1991gr.kmp.playground.presenter.screens.io.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.presenter.screens.io.datastore.robot.robot
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.io.datastore.DatastoreScreen
import com.payam1991gr.kmp.playground.view.screens.io.datastore.DatastoreScreen.State
import com.payam1991gr.kmp.playground.view.screens.io.datastore.DatastoreScreen.State.Event
import com.slack.circuit.test.FakeNavigator
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DatastorePresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(DatastoreScreen)
        val prefs = MutableStateFlow(preferences(1))
        val dataStore = mockk<DataStore<Preferences>> {
            every { data } returns prefs
            coEvery { updateData(any()) } returns prefs.value
        }
        DatastorePresenter(navigator, dataStore).robot {
            onState(false, Action.Back, Action.Code) {
                event(Event.OnToolbarAction(Action.Back))
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(null))
                assertThat(int).isEqualTo(0)
                assertThat(float).isEqualTo(0f)
                assertThat(byteArray).isEqualTo(byteArrayOf())
            }
            onState(false, Action.Back, Action.Code) {
                event(Event.OnToolbarAction(Action.Back))
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(null))
                assertThat(int).isEqualTo(1)
                assertThat(float).isEqualTo(2f)
                assertThat(byteArray).isEqualTo(byteArrayOf(3, 4))
                coVerify(exactly = 3) { dataStore.data }
                verify { prefs.value[State.Data.intKey] }
                verify { prefs.value[State.Data.floatKey] }
                verify { prefs.value[State.Data.byteArrayKey] }

                event(Event.OnToolbarAction(Action.Code))
            }
            onState(true, Action.Back, Action.Preview) {
                event(Event.OnItemChanged(2))
                event(Event.OnItemChanged(4f))
                event(Event.OnItemChanged(byteArrayOf(6, 8)))
                coVerify(exactly = 3) { dataStore.updateData(any()) }
                verify { prefs.value[State.Data.intKey] }
                verify { prefs.value[State.Data.floatKey] }
                verify { prefs.value[State.Data.byteArrayKey] }
                val oldPref = prefs.value
                prefs.value = preferences(2)
                @Suppress("UnusedEquals")
                verify { oldPref.equals(prefs.value) }
            }
            onState {
                assertThat(int).isEqualTo(2)
                assertThat(float).isEqualTo(4f)
                assertThat(byteArray).isEqualTo(byteArrayOf(6, 8))
                coVerify(exactly = 6) { dataStore.data }
                verify { prefs.value[State.Data.intKey] }
                verify { prefs.value[State.Data.floatKey] }
                verify { prefs.value[State.Data.byteArrayKey] }
            }
        }
        confirmVerified()
    }

    private fun preferences(coefficient: Int) = mockk<Preferences> {
        every { get(State.Data.intKey) } returns coefficient
        every { get(State.Data.floatKey) } returns 2f * coefficient
        every { get(State.Data.byteArrayKey) } returns byteArrayOf(
            (3 * coefficient).toByte(),
            (4 * coefficient).toByte(),
        )
    }
}
