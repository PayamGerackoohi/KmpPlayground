package com.payam1991gr.kmp.playground.data.koin

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.payam1991gr.kmp.playground.data.store.createDataStore
import com.payam1991gr.kmp.playground.data.store.dataStoreFileName
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

actual val dataStoreModule by lazy {
    module {
        single<DataStore<Preferences>> {
            createDataStore {
                get<Context>().filesDir.resolve(dataStoreFileName).absolutePath
            }
        }
    }
}

actual val httpClientEngine: HttpClientEngine by lazy { OkHttp.create() }
