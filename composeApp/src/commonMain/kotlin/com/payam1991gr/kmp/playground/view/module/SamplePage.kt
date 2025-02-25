package com.payam1991gr.kmp.playground.view.module

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.data.model.sample.Setting
import com.payam1991gr.kmp.playground.view.mirrorRtl
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.module.SamplePage.Preview.moduleSize
import kmpplayground.composeapp.generated.resources.*
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

/**
 * A screen to show sample usage of kmp features, components, ...
 * @param showCode whether the screen should show the code or the preview of the content
 * @param titleRes the string resource of the screen title
 * @param actions the action-bar icons
 * @param onClick events, triggered by clicking on the action-bar icons
 * @param preview a simple preview of the content
 * @sample com.payam1991gr.kmp.playground.view.screens.components.carousel.Carousel.Preview
 * @param code a simple and sufficient code to produce the content
 * @sample com.payam1991gr.kmp.playground.view.screens.components.carousel.Carousel.Code
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SamplePage(
    showCode: Boolean,
    titleRes: StringResource,
    actions: PersistentList<Action> = persistentListOf(),
    onClick: (Action) -> Unit = {},
    preview: @Composable BoxScope.() -> Unit,
    code: @Composable BoxScope.() -> Unit,
) {
    val (showBack, localActions) = remember(actions) {
        actions.run { contains(Action.Back) to remove(Action.Back) }
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(titleRes),
                        color = MaterialTheme.colorScheme.onPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                navigationIcon = { if (showBack) SamplePage.ToolbarButton(Action.Back, onClick) },
                actions = {
                    localActions.forEach { action -> SamplePage.ToolbarButton(action, onClick) }
                },
                modifier = Modifier.testTag("SamplePage.Toolbar")
            )
        },
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
                .padding(it)
                .testTag("SamplePage.${if (showCode) "Code" else "Preview"}"),
        ) {
            Crossfade(showCode) { showCode -> if (showCode) code() else preview() }
        }
    }
}

object SamplePage {
    enum class Action(val iconRes: DrawableResource, val contentDescriptionRes: StringResource) {
        Back(Res.drawable.ic_back, Res.string.toolbar_back),
        Code(Res.drawable.ic_code, Res.string.toolbar_code),
        Preview(Res.drawable.ic_preview, Res.string.toolbar_preview),
    }

    @Composable
    fun ToolbarButton(
        action: Action,
        onClick: (Action) -> Unit,
    ) {
        IconButton(onClick = { onClick(action) }) {
            Icon(
                vectorResource(action.iconRes),
                stringResource(action.contentDescriptionRes),
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.mirrorRtl()
            )
        }
    }

    /**
     * A helper composable method to make preview of the content in a sample screen
     * It can hold any content, framed and separated by a margin of `16.dp`. It also provides some
     * helper functions:
     * - [Preview.Header] The header of the sample section
     * - [Preview.Description] A concise description for the sample
     * - [Preview.Settings] Some settings to make the sample a little adjustable
     * - [Preview.ContentList] A simple content list with proper margins
     * - [Preview.Module] A simple module list
     * - [Preview.EmptySample] An empty simple as a placeholder
     * @param contents a collection of contents to be placed in a `LazyColumn` host
     * @param modifier the composable host modifier
     * @sample com.payam1991gr.kmp.playground.view.screens.components.carousel.Carousel.Preview
     * @sample com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePicker.TimeSample
     */
    @Composable
    fun preview(
        vararg contents: @Composable () -> Unit,
        gridCells: GridCells = GridCells.Fixed(1),
        spacing: Dp = 16.dp,
        contentPadding: Dp = 16.dp,
        modifier: Modifier = Modifier.fillMaxSize(),
    ) = LazyVerticalGrid(
        gridCells,
        horizontalArrangement = Arrangement.spacedBy(spacing),
        verticalArrangement = Arrangement.spacedBy(spacing),
        contentPadding = PaddingValues(contentPadding),
        modifier = modifier
    ) {
        items(contents) { it() }
    }

    object Preview {
        @Composable
        fun Header(
            title: String,
            modifier: Modifier = Modifier,
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                modifier = modifier,
            )
        }

        @Composable
        fun Description(
            description: String,
            modifier: Modifier = Modifier,
        ) {
            Text(description, modifier)
        }

        @OptIn(ExperimentalLayoutApi::class)
        @Composable
        fun Settings(
            vararg items: Setting,
            modifier: Modifier = Modifier.fillMaxWidth(),
        ) {
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = modifier,
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxRowHeight()
                ) {
                    Text(
                        stringResource(Res.string.settings),
                        style = MaterialTheme.typography.bodySmall,
                    )
                }
                items.forEach { item ->
                    AssistChip(
                        label = {
                            Text(
                                stringResource(item.labelRes),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                            )
                        },
                        onClick = item::toggle,
                    )
                }
            }
        }

        @Composable
        fun ContentList(
            modifier: Modifier = Modifier,
            content: @Composable ColumnScope.() -> Unit,
        ) = Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = modifier.fillMaxWidth(),
            content = content,
        )

        @Composable
        fun Module(
            label: String,
            onToggle: () -> Unit,
            colors: Module.Colors = Module.Defaults.colors(),
            animator: @Composable () -> Unit,
        ) = Column(
            Modifier
                .fillMaxWidth()
                .testTag("Module.$label")
        ) {
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

        fun Modifier.moduleSize(ratio: Float = 2f) = this
            .fillMaxWidth()
            .aspectRatio(ratio)

        object Module {
            val cells by lazy { GridCells.Adaptive(minSize = 300.dp) }

            @Immutable
            class Colors(val header: Color)

            object Defaults {
                @Composable
                fun colors(header: Color = MaterialTheme.colorScheme.secondary): Colors =
                    Colors(header)
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

            fun Modifier.content(tag: String, state: String? = null): Modifier =
                testTag("$tag.Content").run {
                    state?.let { semantics { stateDescription = it } } ?: this
                }
        }

        @Composable
        fun EmptySample() = Box(Modifier.moduleSize(1.5f))
    }
}
