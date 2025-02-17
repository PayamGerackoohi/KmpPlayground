package com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.RandomImage
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.SamplePage.preview
import com.payam1991gr.kmp.playground.view.module.editor.codes
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
        gridCells = GridCells.Adaptive(minSize = 300.dp),
    )

    @Composable
    fun rememberBoolean(init: Boolean = true) = remember { mutableStateOf(init) }

    @Composable
    fun EmptySample() = Box(Modifier.moduleSize(1.5f))

    @Composable
    fun Module(
        label: String,
        onToggle: () -> Unit,
        colors: Module.Colors = Module.Defaults.colors(),
        animator: @Composable () -> Unit,
    ) = Column(Modifier.fillMaxWidth()) {
        TextButton(
            onClick = onToggle,
            colors = ButtonDefaults.textButtonColors(
                containerColor = colors.header,
                contentColor = MaterialTheme.colorScheme.onSecondary,
            ),
            shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text(
                label,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }
        animator()
    }

    object Module {
        @Immutable
        class Colors(val header: Color)

        object Defaults {
            @Composable
            fun colors(header: Color = MaterialTheme.colorScheme.secondary): Colors = Colors(header)
        }

        @Composable
        fun Content(tag: String, cornerRadius: Dp = 0.dp) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .clip(
                        RoundedCornerShape(
                            bottomStart = cornerRadius,
                            bottomEnd = cornerRadius,
                        )
                    )
            ) {
                RandomImage(
                    contentDescription = "$tag.Content",
                    modifier = Modifier.moduleSize()
                )
            }
        }
    }

    @Composable
    fun Code() = codes(
        Res.string.shared to {
            appendRememberVisible()
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

fun Modifier.moduleSize(ratio: Float = 2f) = this
    .fillMaxWidth()
    .aspectRatio(ratio)
