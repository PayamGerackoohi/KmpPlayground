package com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.datetime

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import com.payam1991gr.kmp.playground.data.repository.TimeZoneRepository
import com.payam1991gr.kmp.playground.data.time.FrameGen
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.module.toolbar.rememberToolbarState
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.DateTimeScreen.State
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.DateTimeScreen.State.Event
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.datetime.Clock

class DateTimePresenter(
    private val navigator: Navigator,
    timeZoneRepository: TimeZoneRepository,
    private val clock: Clock,
    private val frameGen: FrameGen,
) : Presenter<State> {
    private val timeZones by lazy { timeZoneRepository.timeZones }

    @Composable
    override fun present(): State {
        val toolbarState = rememberToolbarState()
        val tick by tick()
        return State(
            showCode = toolbarState.showCode,
            toolbarActions = toolbarState.actions,
            now = remember(tick) { clock.now() },
            timeZones = timeZones,
        ) {
            when (it) {
                is Event.OnToolbarAction -> when (it.action) {
                    Action.Back -> navigator.pop()
                    Action.Code -> toolbarState.code()
                    Action.Preview -> toolbarState.preview()
                }
            }
        }
    }

    @Composable
    private fun tick() = produceState(0) {
        while (true) frameGen.frame { value = it }
    }
}
