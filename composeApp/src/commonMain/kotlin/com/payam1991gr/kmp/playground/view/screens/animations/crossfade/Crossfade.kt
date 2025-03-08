package com.payam1991gr.kmp.playground.view.screens.animations.crossfade

import androidx.compose.animation.Crossfade
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import com.payam1991gr.kmp.playground.view.module.RandomImage
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.ContentList
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.Description
import com.payam1991gr.kmp.playground.view.module.SamplePage.preview
import com.payam1991gr.kmp.playground.view.module.editor.CodePanel
import com.payam1991gr.kmp.playground.view.module.editor.rememberCodeEditor
import com.payam1991gr.kmp.playground.view.rememberBoolean
import com.payam1991gr.kmp.playground.view.screens.animations.crossfade.CrossfadeScreen.State
import com.payam1991gr.kmp.playground.view.screens.animations.crossfade.CrossfadeScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.animations.crossfade.sample.appendCrossfadeSample
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

class Crossfade : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) = SamplePage(
        showCode = state.showCode,
        titleRes = Res.string.animations_crossfade,
        actions = state.toolbarActions,
        onClick = { state.event(Event.OnToolbarAction(it)) },
        preview = { Preview() },
        code = { Code() },
    )

    @Composable
    fun Preview() = preview(
        { Description(stringResource(Res.string.animations_crossfade_description)) },
        { CrossfadeSample() },
    )

    @Composable
    fun CrossfadeSample() = ContentList {
        var state by rememberBoolean()
        Button({ state = !state }) { Text(stringResource(Res.string.toggle)) }
        Crossfade(state, Modifier.semantics { stateDescription = string(state) }) {
            if (it) RandomImage() else RandomImage()
        }
    }

    private fun string(state: Boolean) = if (state) "State 1" else "State 2"

    @Composable
    fun Code() = CodePanel(rememberCodeEditor {
        appendCrossfadeSample()
    })
}
