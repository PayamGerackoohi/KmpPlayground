package com.payam1991gr.kmp.playground.presenter.screens.io

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.model.IoItem
import com.payam1991gr.kmp.playground.presenter.screens.io.robot.robot
import com.payam1991gr.kmp.playground.view.screens.io.IoScreen
import com.payam1991gr.kmp.playground.view.screens.io.IoScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.io.api.ApiScreen
import com.payam1991gr.kmp.playground.view.screens.io.database.DatabaseScreen
import com.payam1991gr.kmp.playground.view.screens.io.datastore.DatastoreScreen
import com.payam1991gr.kmp.playground.view.screens.io.file.FileScreen
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class IoPresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(IoScreen)
        IoPresenter(navigator).robot {
            onState {
                assertThat(items.size).isEqualTo(4)

                event(Event.OnClick(IoItem.Datastore))
                assertThat(navigator.awaitNextScreen()).isEqualTo(DatastoreScreen)

//                event(Event.OnClick(IoItem.Database))
//                assertThat(navigator.awaitNextScreen()).isEqualTo(DatabaseScreen)

                event(Event.OnClick(IoItem.API))
                assertThat(navigator.awaitNextScreen()).isEqualTo(ApiScreen)

//                event(Event.OnClick(IoItem.File))
//                assertThat(navigator.awaitNextScreen()).isEqualTo(FileScreen)

                event(Event.OnBackPressed)
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(FileScreen))
            }
        }
    }
}
