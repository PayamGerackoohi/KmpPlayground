package com.payam1991gr.kmp.playground.presenter.screens.graphics.icons

import androidx.compose.material.icons.Icons.Default
import androidx.compose.material.icons.filled.*
import com.google.common.truth.Truth.assertThat
import com.payam1991gr.kmp.playground.data.icon.Icons
import com.payam1991gr.kmp.playground.data.model.IconData
import com.payam1991gr.kmp.playground.data.model.LazyData
import com.payam1991gr.kmp.playground.presenter.screens.graphics.icons.robot.robot
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State.Event
import com.slack.circuit.test.FakeNavigator
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class IconsPresenterTest {
    private val icons = object : Icons {
        override fun core() = persistentListOf(
            IconData("AccountBox", Default.AccountBox),
            IconData("AccountCircle", Default.AccountCircle),
        )

        override suspend fun extended() = persistentListOf(
            IconData("_1k", Default._1k),
            IconData("_1kPlus", Default._1kPlus),
        )
    }

    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(IconsScreen)
        IconsPresenter(navigator, icons).robot {
            onState(false, Action.Back, Action.Code) {
                event(Event.OnToolbarAction(Action.Back))
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(null))

                event(Event.OnToolbarAction(Action.Code))
            }
            onState(true, Action.Back, Action.Preview) {
                event(Event.OnToolbarAction(Action.Preview))
            }
            onState(false, Action.Back, Action.Code) {
                coreIcons.apply {
                    assertThat(size).isEqualTo(2)
                    assertThat(this[0].title).isEqualTo("AccountBox")
                    assertThat(this[1].title).isEqualTo("AccountCircle")
                    verifyBehavior(this[0])
                    verifyBehavior(this[1])
                }
                assertThat(extendedIcons).isInstanceOf(LazyData.Loading::class.java)
            }
            onState(false, Action.Back, Action.Code) {
                (extendedIcons as LazyData.Data<PersistentList<IconData>>).data.apply {
                    assertThat(size).isEqualTo(2)
                    assertThat(this[0].title).isEqualTo("_1k")
                    assertThat(this[1].title).isEqualTo("_1kPlus")
                    verifyBehavior(this[0])
                    verifyBehavior(this[1])
                }
            }
        }
    }
}
