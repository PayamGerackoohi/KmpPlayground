package com.payam1991gr.kmp.playground.view.screens.io.datastore

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.byteArrayPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import com.payam1991gr.kmp.playground.platform.KmpParcelize
import com.payam1991gr.kmp.playground.view.ComposeState
import com.payam1991gr.kmp.playground.view.module.SamplePage.Action
import com.payam1991gr.kmp.playground.view.screens.io.datastore.DatastoreScreen.State
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.screen.Screen
import kotlinx.collections.immutable.PersistentList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@KmpParcelize
data object DatastoreScreen : Screen {
    data class State(
        val showCode: Boolean,
        val toolbarActions: PersistentList<Action>,
        val int: Int,
        val float: Float,
        val byteArray: ByteArray,
        val event: (Event) -> Unit,
    ) : CircuitUiState {
        sealed interface Event : CircuitUiEvent {
            data class OnToolbarAction(val action: Action) : Event
            data class OnItemChanged<out T>(val value: T) : Event
        }

        class Data(
            private val dataStore: DataStore<Preferences>,
            int: ComposeState<Int>,
            float: ComposeState<Float>,
            byteArray: ComposeState<ByteArray>,
            private val scope: CoroutineScope,
        ) {
            companion object {
                val intKey = intPreferencesKey("int item")
                val floatKey = floatPreferencesKey("float item")
                val byteArrayKey = byteArrayPreferencesKey("byte array item")
            }

            val int by int
            val float by float
            val byteArray by byteArray

            private fun update(block: (MutablePreferences) -> Unit) = scope.launch {
                dataStore.edit(block)
            }

            fun update(value: Int) = update { it[intKey] = value }
            fun update(value: Float) = update { it[floatKey] = value }
            fun update(value: ByteArray) = update { it[byteArrayKey] = value }
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other == null || this::class != other::class) return false

            other as State
            if (showCode != other.showCode) return false
            if (toolbarActions != other.toolbarActions) return false
            if (int != other.int) return false
            if (float != other.float) return false
            if (!byteArray.contentEquals(other.byteArray)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = showCode.hashCode()
            result = 31 * result + toolbarActions.hashCode()
            result = 31 * result + int
            result = 31 * result + float.hashCode()
            result = 31 * result + byteArray.contentHashCode()
            return result
        }
    }
}

@Composable
fun rememberData(
    dataStore: DataStore<Preferences>,
    initialIntItem: Int = 0,
    initialFloatItem: Float = 0f,
    initialByteArrayItem: ByteArray = byteArrayOf(),
): State.Data {
    val intItem = remember { mutableStateOf(initialIntItem) }
    val floatItem = remember { mutableStateOf(initialFloatItem) }
    val byteArrayItem = remember { mutableStateOf(initialByteArrayItem) }
    dataStore.data.collectAsState(null).value?.let { pref ->
        pref[State.Data.intKey]?.let { intItem.value = it }
        pref[State.Data.floatKey]?.let { floatItem.value = it }
        pref[State.Data.byteArrayKey]?.let { byteArrayItem.value = it }
    }
    val scope = rememberCoroutineScope()
    return remember { State.Data(dataStore, intItem, floatItem, byteArrayItem, scope) }
}
