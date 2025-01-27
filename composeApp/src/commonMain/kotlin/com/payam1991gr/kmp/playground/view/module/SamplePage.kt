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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.data.model.sample.Setting
import com.payam1991gr.kmp.playground.view.mirrorRtl
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import kmpplayground.composeapp.generated.resources.*
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource

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
                navigationIcon = {
                    if (showBack) SamplePage.ToolbarButton(Action.Back, onClick)
                },
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

    @Composable
    fun preview(
        vararg contents: @Composable () -> Unit,
        modifier: Modifier = Modifier.fillMaxSize(),
    ) = LazyColumn(
        verticalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(vertical = 16.dp),
        modifier = modifier
    ) {
        items(contents) { content -> content() }
    }

    object Preview {
        @Composable
        fun Header(
            title: String,
            modifier: Modifier = Modifier.padding(horizontal = 16.dp),
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
            modifier: Modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Text(description, modifier)
        }

        @OptIn(ExperimentalLayoutApi::class)
        @Composable
        fun Settings(
            vararg items: Setting,
            modifier: Modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
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
    }
}
