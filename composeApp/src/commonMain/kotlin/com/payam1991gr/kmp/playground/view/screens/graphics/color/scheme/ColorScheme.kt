package com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.editor.CodePanel
import com.payam1991gr.kmp.playground.view.module.editor.rememberCodeEditor
import com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.sample.appendItem
import com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.ColorSchemeScreen.State
import com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.ColorSchemeScreen.State.Event
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.Res
import kmpplayground.composeapp.generated.resources.graphic_color_scheme

class ColorScheme : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) {
        SamplePage(
            showCode = state.showCode,
            titleRes = Res.string.graphic_color_scheme,
            actions = state.toolbarActions,
            onClick = { state.event(Event.OnToolbarAction(it)) },
            code = { Code() },
            preview = { Preview() }
        )
    }

    @Composable
    fun Preview() = SamplePage.preview(
        { Item("Primary", MaterialTheme.colorScheme.primary) },
        { Item("On Primary", MaterialTheme.colorScheme.onPrimary) },
        { Item("Primary Container", MaterialTheme.colorScheme.primaryContainer) },
        { Item("On Primary Container", MaterialTheme.colorScheme.onPrimaryContainer) },
        { Item("Inverse Primary Container", MaterialTheme.colorScheme.inversePrimary) },
        { Item("Secondary", MaterialTheme.colorScheme.secondary) },
        { Item("On Secondary", MaterialTheme.colorScheme.onSecondary) },
        { Item("Secondary Container", MaterialTheme.colorScheme.secondaryContainer) },
        { Item("On Secondary Container", MaterialTheme.colorScheme.onSecondaryContainer) },
        { Item("Tertiary", MaterialTheme.colorScheme.tertiary) },
        { Item("On Tertiary", MaterialTheme.colorScheme.onTertiary) },
        { Item("Tertiary Container", MaterialTheme.colorScheme.tertiaryContainer) },
        { Item("On Tertiary Container", MaterialTheme.colorScheme.onTertiaryContainer) },
        { Item("Background", MaterialTheme.colorScheme.background) },
        { Item("On Background", MaterialTheme.colorScheme.onBackground) },
        { Item("Surface", MaterialTheme.colorScheme.surface) },
        { Item("On Surface", MaterialTheme.colorScheme.onSurface) },
        { Item("Surface Variant", MaterialTheme.colorScheme.surfaceVariant) },
        { Item("On Surface Variant", MaterialTheme.colorScheme.onSurfaceVariant) },
        { Item("Surface Tint", MaterialTheme.colorScheme.surfaceTint) },
        { Item("Inverse Surface", MaterialTheme.colorScheme.inverseSurface) },
        { Item("Inverse On Surface", MaterialTheme.colorScheme.inverseOnSurface) },
        { Item("Error", MaterialTheme.colorScheme.error) },
        { Item("On Error", MaterialTheme.colorScheme.onError) },
        { Item("Error Container", MaterialTheme.colorScheme.errorContainer) },
        { Item("On Error Container", MaterialTheme.colorScheme.onErrorContainer) },
        { Item("outline", MaterialTheme.colorScheme.outline) },
        { Item("Outline Variant", MaterialTheme.colorScheme.outlineVariant) },
        { Item("Scrim", MaterialTheme.colorScheme.scrim) },
        { Item("Surface Bright", MaterialTheme.colorScheme.surfaceBright) },
        { Item("Surface Dim", MaterialTheme.colorScheme.surfaceDim) },
        { Item("Surface Container", MaterialTheme.colorScheme.surfaceContainer) },
        { Item("Surface Container High", MaterialTheme.colorScheme.surfaceContainerHigh) },
        { Item("Surface Container Highest", MaterialTheme.colorScheme.surfaceContainerHighest) },
        { Item("Surface Container Low", MaterialTheme.colorScheme.surfaceContainerLow) },
        { Item("Surface Container Lowest", MaterialTheme.colorScheme.surfaceContainerLowest) },
        gridCells = GridCells.Adaptive(100.dp)
    )

    @Composable
    fun Item(label: String, color: Color) = Surface(
        shadowElevation = 4.dp,
        modifier = Modifier
            .border(1.dp, MaterialTheme.colorScheme.outline)
            .aspectRatio(1f)
    ) {
        Column {
            Surface(shadowElevation = 4.dp) {
                Text(
                    label,
                    color = MaterialTheme.colorScheme.onPrimary,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(4.dp)
                )
            }
            HorizontalDivider(color = MaterialTheme.colorScheme.outline)
            Box(
                Modifier
                    .background(color)
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }

    @Composable
    fun Code() = CodePanel(rememberCodeEditor {
        appendItem()
    })
}
