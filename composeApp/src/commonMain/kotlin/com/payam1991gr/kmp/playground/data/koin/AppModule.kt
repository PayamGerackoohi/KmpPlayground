package com.payam1991gr.kmp.playground.data.koin

import com.payam1991gr.kmp.playground.data.icon.Icons
import com.payam1991gr.kmp.playground.data.icon.IconsImpl
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(starterBlock: KoinApplication.() -> Unit = {}) = startKoin {
    starterBlock()
    modules(appModule, circuitModule, dataStoreModule)
}

val appModule by lazy {
    module {
        single<Icons> { IconsImpl() }
    }
}

expect val dataStoreModule: Module
