package com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.state

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefreshScreen.State.PtrState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Stable
class PtrStateImpl(
    override val state: PullToRefreshState,
    isRefreshing: MutableState<Boolean>,
    itemCount: MutableIntState,
    private val coroutineScope: CoroutineScope,
) : PtrState {
    override var isRefreshing by isRefreshing
    override var itemCount by itemCount

    override fun onRefresh() {
        isRefreshing = true
        coroutineScope.launch {
            delay(500)
            itemCount++
            isRefreshing = false
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberPtrState(
    initialItemCount: Int = 5,
    state: PullToRefreshState = rememberPullToRefreshState(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
): PtrState {
    val isRefreshing = remember { mutableStateOf(false) }
    val itemCount = remember { mutableIntStateOf(initialItemCount) }
    return remember { PtrStateImpl(state, isRefreshing, itemCount, coroutineScope) }
}
