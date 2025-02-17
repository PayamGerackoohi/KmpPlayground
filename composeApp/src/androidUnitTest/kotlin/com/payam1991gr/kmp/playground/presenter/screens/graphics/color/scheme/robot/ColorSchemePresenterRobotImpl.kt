package com.payam1991gr.kmp.playground.presenter.screens.graphics.color.scheme.robot

import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.ColorSchemeScreen.State
import kotlinx.collections.immutable.toPersistentList

class ColorSchemePresenterRobotImpl(private val crt: Crt) : ColorSchemePresenterRobot {
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
}
