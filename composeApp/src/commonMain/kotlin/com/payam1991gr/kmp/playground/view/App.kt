package com.payam1991gr.kmp.playground.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.payam1991gr.kmp.playground.data.koin.initKoin
import com.payam1991gr.kmp.playground.view.screens.home.HomeScreen
import com.payam1991gr.kmp.playground.view.theme.KmpPlaygroundTheme
import com.slack.circuit.backstack.rememberSaveableBackStack
import com.slack.circuit.foundation.Circuit
import com.slack.circuit.foundation.CircuitCompositionLocals
import com.slack.circuit.foundation.NavigableCircuitContent
import com.slack.circuit.foundation.internal.BackHandler
import com.slack.circuit.foundation.rememberCircuitNavigator
import kotlinx.coroutines.delay
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class App : KoinComponent {
    private val circuit: Circuit by inject()

    init {
        initKoin()
    }

    @Composable
    fun Start() = KmpPlaygroundTheme {
        Surface(
            color = MaterialTheme.colorScheme.background,
            modifier = Modifier.fillMaxSize()
        ) {
            var start by remember { mutableStateOf(false) }
            LaunchedEffect(Unit) {
                delay(100)
                start = true
            }
            AnimatedVisibility(
                start,
                enter = fadeIn(tween(300)),
            ) {
                val backStack = rememberSaveableBackStack(HomeScreen)
                if (backStack.size > 1) BackHandler { backStack.pop() }
                val navigator = rememberCircuitNavigator(backStack) {}
                CircuitCompositionLocals(circuit) {
                    NavigableCircuitContent(navigator, backStack)
                }
            }
        }
    }
}
