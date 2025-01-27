package com.payam1991gr.kmp.playground.presenter.screens.components.picker.datetime.robot

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.presenter.screens.components.picker.datetime.DateTimePickerPresenter
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen.State
import com.slack.circuit.test.CircuitReceiveTurbine
import com.slack.circuit.test.test
import kotlinx.collections.immutable.toPersistentList

interface DateTimePickerRobot {
    suspend fun onState(
        vararg toolbarActions: Action = arrayOf(),
        showCode: Boolean? = null,
        datePicker: String? = null,
        dateRangePicker: String? = null,
        timePicker: String? = null,
        timeInput: String? = null,
        block: suspend State.() -> Unit = {},
    ): Any
}

typealias Crt = CircuitReceiveTurbine<State>

class DateTimePickerRobotImpl(private val crt: Crt) : DateTimePickerRobot {
    override suspend fun onState(
        vararg toolbarActions: Action,
        showCode: Boolean?,
        datePicker: String?,
        dateRangePicker: String?,
        timePicker: String?,
        timeInput: String?,
        block: suspend State.() -> Unit
    ) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        showCode?.let { assertThat(it).isEqualTo(this.showCode) }
        if (toolbarActions.isNotEmpty())
            assertThat(toolbarActions.toPersistentList()).isEqualTo(this.toolbarActions)
        datePicker?.let { assertThat(this.datePicker.toString()).isEqualTo(it) }
        dateRangePicker?.let { assertThat(this.dateRangePicker.toString()).isEqualTo(it) }
        timePicker?.let { assertThat(this.timePicker.toString()).isEqualTo(it) }
        timeInput?.let { assertThat(this.timeInput.toString()).isEqualTo(it) }
        block()
    }
}

suspend fun DateTimePickerPresenter.robot(block: suspend DateTimePickerRobot.() -> Unit) = test {
    DateTimePickerRobotImpl(this).block()
    cancelAndIgnoreRemainingEvents()
}
