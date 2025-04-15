package com.payam1991gr.kmp.playground.data.koin

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.payam1991gr.kmp.playground.data.store.createDataStore
import com.payam1991gr.kmp.playground.data.store.dataStoreFileName
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.jetbrains.skiko.OS
import org.jetbrains.skiko.hostOs
import org.koin.dsl.module
import java.io.File
import javax.swing.filechooser.FileSystemView

private val appName get() = "KmpPlayground"
private val homeDirectory get() = FileSystemView.getFileSystemView().homeDirectory

actual val dataStoreModule by lazy {
    module {
        single<DataStore<Preferences>> {
            createDataStore {
                when (hostOs) {
                    OS.Windows -> File(System.getenv("AppData")).resolve(appName)
                    OS.MacOS -> homeDirectory.resolve("Library/Application Support/$appName")
                    else -> homeDirectory.resolve(".$appName")
                }.resolve(dataStoreFileName).absolutePath
            }
        }
    }
}

actual val httpClientEngine: HttpClientEngine by lazy { OkHttp.create() }
