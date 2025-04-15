package com.payam1991gr.kmp.playground.data.koin

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.payam1991gr.kmp.playground.data.store.createDataStore
import com.payam1991gr.kmp.playground.data.store.dataStoreFileName
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

@OptIn(ExperimentalForeignApi::class)
actual val dataStoreModule by lazy {
    module {
        single<DataStore<Preferences>> {
            createDataStore {
                val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                    directory = NSDocumentDirectory,
                    inDomain = NSUserDomainMask,
                    appropriateForURL = null,
                    create = false,
                    error = null,
                )
                requireNotNull(documentDirectory).path + "/$dataStoreFileName"
            }
        }
    }
}

actual val httpClientEngine: HttpClientEngine by lazy { Darwin.create() }
