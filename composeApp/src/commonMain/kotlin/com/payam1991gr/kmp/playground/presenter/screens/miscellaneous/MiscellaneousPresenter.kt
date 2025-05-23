package com.payam1991gr.kmp.playground.presenter.screens.miscellaneous

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.data.model.MiscellaneousItem
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.MiscellaneousScreen.State
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.ble.BleScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.datetime.DateTimeScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.pdf.PdfScreen
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.toPersistentList

class MiscellaneousPresenter(private val navigator: Navigator) : Presenter<State> {
    private val items = MiscellaneousItem.entries.toPersistentList()

    @Composable
    override fun present(): State {
        return State(items = items) {
            when (it) {
                is State.Event.OnClick -> when (it.item) {
                    MiscellaneousItem.Cpp -> navigator.goTo(CppScreen)
//                    MiscellaneousItem.Ble -> navigator.goTo(BleScreen)
//                    MiscellaneousItem.Pdf -> navigator.goTo(PdfScreen)
                    MiscellaneousItem.DateTime -> navigator.goTo(DateTimeScreen)
                }

                State.Event.OnBackPressed -> navigator.pop()
            }
        }
    }
}
