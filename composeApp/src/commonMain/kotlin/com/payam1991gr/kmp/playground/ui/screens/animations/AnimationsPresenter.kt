package com.payam1991gr.kmp.playground.ui.screens.animations

import androidx.compose.runtime.Composable
import com.payam1991gr.kmp.playground.data.model.AnimationItem
import com.payam1991gr.kmp.playground.ui.screens.animations.animate.x.`as`.AnimateXAsScreen
import com.payam1991gr.kmp.playground.ui.screens.animations.AnimationsScreen.State
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.content.AnimatedContentScreen
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.visibility.AnimatedVisibilityScreen
import com.payam1991gr.kmp.playground.ui.screens.animations.crossfade.CrossfadeScreen
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.presenter.Presenter
import kotlinx.collections.immutable.toPersistentList

class AnimationsPresenter(private val navigator: Navigator) : Presenter<State> {
    private val items = AnimationItem.entries.toPersistentList()

    @Composable
    override fun present(): State {
        return State(items = items) {
            when (it) {
                is State.Event.OnClick -> when (it.item) {
                    AnimationItem.AnimatedContent -> navigator.goTo(AnimatedContentScreen)
                    AnimationItem.AnimatedVisibility -> navigator.goTo(AnimatedVisibilityScreen)
                    AnimationItem.Crossfade -> navigator.goTo(CrossfadeScreen)
                    AnimationItem.AnimateXAs -> navigator.goTo(AnimateXAsScreen)
                }

                State.Event.OnBackPressed -> navigator.pop()
            }
        }
    }
}
