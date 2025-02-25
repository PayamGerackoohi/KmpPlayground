package com.payam1991gr.kmp.playground.presenter.screens.graphics.icons

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.payam1991gr.kmp.playground.data.icon.Icons
import com.payam1991gr.kmp.playground.data.model.IconData
import com.payam1991gr.kmp.playground.data.model.LazyData
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.module.toolbar.rememberToolbarState
import com.payam1991gr.kmp.playground.view.rememberEffectOf
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State.Effect
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen.State.Event
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.delay

class IconsPresenter(
    private val navigator: Navigator,
    private val icons: Icons,
) : Presenter<State> {
    private val coreIcons by lazy { icons.core() }

    @Composable
    override fun present(): State {
        val toolbarState = rememberToolbarState()
        val effect = rememberEffectOf<Effect>()
        var extendedIcons: LazyData<PersistentList<IconData>> by remember {
            mutableStateOf(LazyData.Loading)
        }
        LaunchedEffect(Unit) {
            delay(500)
            extendedIcons = LazyData.Data(icons.extended())
        }
        return State(
            toolbarState.showCode,
            toolbarState.actions,
            coreIcons = coreIcons,
            extendedIcons = extendedIcons,
            effect = effect,
        ) {
            when (it) {
                is Event.OnIconClicked -> effect.value = Effect.ShowMessage(it.icon.title)
                is Event.OnToolbarAction -> when (it.action) {
                    Action.Back -> navigator.pop()
                    Action.Code -> toolbarState.code()
                    Action.Preview -> toolbarState.preview()
                }
            }
        }
    }
}
