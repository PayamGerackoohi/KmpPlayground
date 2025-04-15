package com.payam1991gr.kmp.playground.presenter.screens.miscellaneous

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.model.MiscellaneousItem
import com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.robot.robot
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.MiscellaneousScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.MiscellaneousScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.ble.BleScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.DateTimeScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.pdf.PdfScreen
import com.slack.circuit.test.FakeNavigator
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MiscellaneousPresenterTest {
    @Test
    fun test() = runTest {
        val navigator = FakeNavigator(MiscellaneousScreen)
        MiscellaneousPresenter(navigator).robot {
            onState {
                assertThat(items.size).isEqualTo(4)

                event(Event.OnClick(MiscellaneousItem.DateTime))
                assertThat(navigator.awaitNextScreen()).isEqualTo(DateTimeScreen)

                event(Event.OnClick(MiscellaneousItem.Cpp))
                assertThat(navigator.awaitNextScreen()).isEqualTo(CppScreen)

//                event(Event.OnClick(MiscellaneousItem.Ble))
//                assertThat(navigator.awaitNextScreen()).isEqualTo(BleScreen)

//                event(Event.OnClick(MiscellaneousItem.Pdf))
//                assertThat(navigator.awaitNextScreen()).isEqualTo(PdfScreen)

                event(Event.OnBackPressed)
                assertThat(navigator.awaitPop()).isEqualTo(FakeNavigator.PopEvent(PdfScreen))
            }
        }
    }
}
