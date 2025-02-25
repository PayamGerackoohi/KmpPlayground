package com.payam1991gr.kmp.playground.presenter.screens.graphics.icons.robot

import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.data.model.IconData
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State.Effect
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State.Event
import com.slack.circuit.test.CircuitReceiveTurbine
import kotlinx.collections.immutable.toPersistentList

typealias Crt = CircuitReceiveTurbine<State>

class IconsRobotImpl(private val crt: Crt) : IconsRobot {
    override suspend fun onState(
        showCode: Boolean,
        vararg actions: SamplePage.Action,
        block: suspend State.() -> Unit
    ) = crt.awaitItem().apply {
        assertThat(this).isInstanceOf(State::class.java)
        assertThat(showCode).isEqualTo(this.showCode)
        assertThat(actions.toPersistentList()).isEqualTo(toolbarActions)
        block()
    }

    override fun State.verifyBehavior(icon: IconData) {
        event(Event.OnIconClicked(icon))
        effect.value.apply {
            assertThat(this).isInstanceOf(Effect.ShowMessage::class.java)
            this as Effect.ShowMessage
            assertThat(text).isEqualTo(icon.title)
        }
    }
}
