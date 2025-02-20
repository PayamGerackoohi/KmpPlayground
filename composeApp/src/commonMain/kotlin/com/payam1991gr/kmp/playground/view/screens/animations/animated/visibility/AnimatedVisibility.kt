package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.view.appendRememberBoolean
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.EmptySample
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Module
import com.payam1991gr.kmp.playground.view.module.SamplePage.preview
import com.payam1991gr.kmp.playground.view.module.editor.codes
import com.payam1991gr.kmp.playground.view.module.sample.appendModule
import com.payam1991gr.kmp.playground.view.module.sample.appendModuleSize
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibilityScreen.State
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibilityScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.sample.*
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*

class AnimatedVisibility : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) {
        SamplePage(
            showCode = state.showCode,
            titleRes = Res.string.animations_animated_visibility,
            actions = state.toolbarActions,
            onClick = { state.event(Event.OnToolbarAction(it)) },
            preview = { Preview() },
            code = { Code() },
        )
    }

    @Composable
    fun Preview() = preview(
        { HorizontalTransitionSample() },
        { SlideTransitionSample() },
        { FadeTransitionSample() },
        { FullyLoadedTransitionSample() },
        { BooleanVisibleParamNoReceiverSample() },
        { FloatingActionButtonSample() },
        { SlideInOutSample() },
        { ExpandShrinkVerticallySample() },
        { ExpandInShrinkOutSample() },
        { RowSample() },
        { ScopeAnimateEnterExitSample() },
        { AddingToGenericTransitionSample() },
        { LazyRowSample() },
        { RowScopeWithMutableTransitionStateSample() },
        { AnimateEnterExitPartialContentSample() },
        { ScaledEnterExitSample() },
        { EmptySample() },
        gridCells = Module.cells,
    )

    @Composable
    fun Code() = codes(
        Res.string.shared to {
            appendRememberBoolean()
            appendModule()
            appendModuleSize()
        },
        Res.string.animations_animated_visibility_horizontal_transition to { appendHorizontalTransitionSample() },
        Res.string.animations_animated_visibility_slide_transition to { appendSlideTransitionSample() },
        Res.string.animations_animated_visibility_fade_transition to { appendFadeTransitionSample() },
        Res.string.animations_animated_visibility_fully_loaded_transition to { appendFullyLoadedTransitionSample() },
        Res.string.animations_animated_visibility_boolean_visible_param_no_receiver to { appendBooleanVisibleParamNoReceiverSample() },
        Res.string.animations_animated_visibility_floating_action_button to { appendFloatingActionButtonSample() },
        Res.string.animations_animated_visibility_slide_in_out to { appendSlideInOutSample() },
        Res.string.animations_animated_visibility_expand_shrink_vertically to { appendExpandShrinkVerticallySample() },
        Res.string.animations_animated_visibility_expand_in_shrink_out to { appendExpandInShrinkOutSample() },
        Res.string.animations_animated_visibility_row to { appendRowSample() },
        Res.string.animations_animated_visibility_scope_animate_enter_exit to { appendScopeAnimateEnterExitSample() },
        Res.string.animations_animated_visibility_adding_to_generic_transition to { appendAddingToGenericTransitionSample() },
        Res.string.animations_animated_visibility_lazy_row to { appendLazyRowSample() },
        Res.string.animations_animated_visibility_row_scope_with_mutable_transition_state to { appendRowScopeWithMutableTransitionStateSample() },
        Res.string.animations_animated_visibility_animate_enter_exit_partial_content to { appendAnimateEnterExitPartialContentSample() },
        Res.string.animations_animated_visibility_scaled_enter_exit to { appendScaledEnterExitSample() },
    )
}
