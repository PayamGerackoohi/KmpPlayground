package com.payam1991gr.kmp.playground.data.koin

import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() = startKoin { modules(appModule, circuitModule) }

val appModule by lazy {
    module {
//        single<Dummy> { DummyImpl() }
    }
}
