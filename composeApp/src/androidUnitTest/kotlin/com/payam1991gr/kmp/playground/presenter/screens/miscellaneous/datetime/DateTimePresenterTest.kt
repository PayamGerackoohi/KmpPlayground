package com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.datetime

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.repository.TimeZoneRepository
import com.payam1991gr.kmp.playground.data.time.FrameGen
import com.payam1991gr.kmp.playground.data.time.label
import com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.datetime.robot.robot
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.DateTimeScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.DateTimeScreen.State.Event
import com.slack.circuit.test.FakeNavigator
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import org.junit.Test

class DateTimePresenterTest {
    private val timeZoneRepository = object : TimeZoneRepository {
        override val timeZones = persistentListOf(TimeZone.UTC label "GMT")
    }
    private val clock = object : Clock {
        private var i = 1L
        override fun now() = Instant.fromEpochSeconds(i++)
    }
    private var i = 0
    private val frameGen = FrameGen { block ->
        delay(1000)
        block(i++)
    }

    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(DateTimeScreen)
        DateTimePresenter(navigator, timeZoneRepository, clock, frameGen).robot {
            onState(false, Action.Back, Action.Code) {
                event(Event.OnToolbarAction(Action.Back))
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(null))
                event(Event.OnToolbarAction(Action.Code))
            }
            onState(true, Action.Back, Action.Preview) {
                event(Event.OnToolbarAction(Action.Preview))
            }
            onState(false, Action.Back, Action.Code) {
                timeZones.apply {
                    assertThat(this.size).isEqualTo(1)
                    assertThat(this[0].value).isEqualTo(TimeZone.UTC)
                    assertThat(this[0].label).isEqualTo("GMT")
                }
                assertThat(now.epochSeconds).isEqualTo(1)
            }
            onState { assertThat(now.epochSeconds).isEqualTo(2) }
            onState { assertThat(now.epochSeconds).isEqualTo(3) }
        }
    }
}
