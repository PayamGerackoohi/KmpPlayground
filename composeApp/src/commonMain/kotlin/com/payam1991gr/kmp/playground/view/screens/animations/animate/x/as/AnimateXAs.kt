package com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`

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
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.AnimateXAsScreen.State
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.AnimateXAsScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample.appendColorSample
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample.appendDpSample
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample.appendFloatSample
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample.appendIntSample
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample.appendOffsetSample
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample.appendValueSample
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample.ColorSample
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample.DpSample
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample.FloatSample
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample.IntSample
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample.OffsetSample
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.sample.ValueSample
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*

class AnimateXAs : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) {
        SamplePage(
            showCode = state.showCode,
            titleRes = Res.string.animations_animate_x_as,
            actions = state.toolbarActions,
            onClick = { state.event(Event.OnToolbarAction(it)) },
            preview = { Preview() },
            code = { Code() },
        )
    }

    @Composable
    fun Preview() = preview(
        { FloatSample() },
        { OffsetSample() },
        { DpSample() },
        { ColorSample() },
        { IntSample() },
        { ValueSample() },
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
        Res.string.animations_animate_float to { appendFloatSample() },
        Res.string.animations_animate_offset to { appendOffsetSample() },
        Res.string.animations_animate_dp to { appendDpSample() },
        Res.string.animations_animate_color to { appendColorSample() },
        Res.string.animations_animate_int to { appendIntSample() },
        Res.string.animations_animate_value to { appendValueSample() },
    )
}
