package com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.payam1991gr.kmp.playground.view.module.SamplePage
import com.payam1991gr.kmp.playground.view.module.SamplePage.preview
import com.payam1991gr.kmp.playground.view.module.editor.CodePanel
import com.payam1991gr.kmp.playground.view.module.editor.rememberCodeEditor
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefreshScreen.State
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefreshScreen.State.Event
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.sample.*
import com.slack.circuit.runtime.ui.Ui
import kmpplayground.composeapp.generated.resources.*
import org.jetbrains.compose.resources.stringResource

class PullToRefresh : Ui<State> {
    @Composable
    override fun Content(state: State, modifier: Modifier) {
        SamplePage(
            showCode = state.showCode,
            titleRes = Res.string.components_pull_to_refresh,
            actions = state.toolbarActions,
            onClick = { state.event(Event.OnToolbarAction(it)) },
            preview = { Preview(state) },
            code = { Code() },
        )
    }

    @Composable
    private fun Preview(state: State) = preview(
        { state.linearProgressIndicator.LinearProgressIndicatorSample() },
        { state.pullToRefreshBox.PullToRefreshBoxSample() },
        { state.scaling.ScalingSample() },
        { state.customBehavior.CustomBehaviorSample() },
    )

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun State.PtrState.PullToRefresh(
        title: String,
        modifier: Modifier = Modifier,
        content: @Composable (PaddingValues, @Composable () -> Unit) -> Unit,
    ) = Scaffold(
        modifier = modifier.module(title),
        topBar = {
            TopAppBar(
                title = { Text(title) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                ),
                actions = { // Provide an accessible alternative to trigger refresh.
                    IconButton(onClick = ::onRefresh) {
                        Icon(
                            Icons.Filled.Refresh,
                            stringResource(Res.string.trigger_refresh),
                        )
                    }
                },
                modifier = Modifier.clip(RoundedCornerShape(topStart = 16.dp))
            )
        },
    ) {
        content(it) {
            LazyColumn(Modifier.moduleList(title)) {
                items(itemCount) {
                    ListItem({
                        Text(
                            stringResource(
                                Res.string.components_pull_to_refresh_item_template,
                                itemCount - it
                            )
                        )
                    })
                    HorizontalDivider()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun State.PtrState.LinearProgressIndicatorSample() = PullToRefresh(
        title = stringResource(Res.string.components_pull_to_refresh_linear_progress_indicator),
        modifier = Modifier.pullToRefresh(
            state = state,
            isRefreshing = isRefreshing,
            onRefresh = ::onRefresh
        )
    ) { padding, content ->
        Box(Modifier.padding(padding)) {
            content()
            val modifier = Modifier.fillMaxWidth()
            if (isRefreshing) LinearProgressIndicator(modifier)
            else LinearProgressIndicator({ state.distanceFraction }, modifier)
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun State.PtrState.PullToRefreshBoxSample() = PullToRefresh(
        title = stringResource(Res.string.components_pull_to_refresh_box),
    ) { padding, content ->
        PullToRefreshBox(
            state = state,
            isRefreshing = isRefreshing,
            onRefresh = ::onRefresh,
            modifier = Modifier.padding(padding),
        ) { content() }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun State.PtrState.ScalingSample() {
        val scaleFraction = {
            if (isRefreshing) 1f
            else LinearOutSlowInEasing.transform(state.distanceFraction).coerceIn(0f, 1f)
        }
        PullToRefresh(
            title = stringResource(Res.string.components_pull_to_refresh_scaling),
            modifier = Modifier.pullToRefresh(
                state = state,
                isRefreshing = isRefreshing,
                onRefresh = ::onRefresh
            ),
        ) { padding, content ->
            Box(Modifier.padding(padding)) {
                content()
                Box(
                    Modifier
                        .align(Alignment.TopCenter)
                        .graphicsLayer {
                            scaleX = scaleFraction()
                            scaleY = scaleFraction()
                        }
                ) {
                    PullToRefreshDefaults.Indicator(
                        state = state,
                        isRefreshing = isRefreshing,
                    )
                }
            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    private fun State.PtrState.CustomBehaviorSample() {
        val state = remember {
            object : PullToRefreshState {
                private val anim = Animatable(0f, Float.VectorConverter)
                override val distanceFraction get() = anim.value
                override suspend fun snapTo(targetValue: Float) = anim.snapTo(targetValue)

                override suspend fun animateToThreshold() {
                    anim.animateTo(1f, spring(dampingRatio = Spring.DampingRatioHighBouncy))
                }

                override suspend fun animateToHidden() {
                    anim.animateTo(0f)
                }
            }
        }
        PullToRefresh(
            title = stringResource(Res.string.components_pull_to_refresh_custom_behavior),
        ) { padding, content ->
            PullToRefreshBox(
                modifier = Modifier.padding(padding),
                isRefreshing = isRefreshing,
                onRefresh = ::onRefresh,
                state = state
            ) { content() }
        }
    }

    private fun Modifier.module(tag: String) = this
        .height(250.dp)
        .border(2.dp, Color.Red, RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp))
        .testTag("Module.$tag")

    private fun Modifier.moduleList(tag: String) = this
        .fillMaxSize()
        .testTag("Module.List.$tag")

    @Composable
    private fun Code() = CodePanel(
        rememberCodeEditor {
            appendState_PtrState_PullToRefresh()
            appendState_PtrState_LinearProgressIndicatorSample()
            appendState_PtrState_PullToRefreshBoxSample()
            appendState_PtrState_ScalingSample()
            appendState_PtrState_CustomBehaviorSample()
            appendRememberPtrState()
            appendModifier_module()
            appendModifier_moduleList()
        }
    )
}
