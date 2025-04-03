package com.payam1991gr.kmp.playground.data.koin

import com.payam1991gr.kmp.playground.data.icon.Icons
import com.payam1991gr.kmp.playground.data.icon.IconsImpl
import com.payam1991gr.kmp.playground.data.repository.TimeZoneRepository
import com.payam1991gr.kmp.playground.data.repository.TimeZoneRepositoryImpl
import com.payam1991gr.kmp.playground.data.string.RandomULongGenerator
import com.payam1991gr.kmp.playground.data.string.RandomULongGeneratorImpl
import com.payam1991gr.kmp.playground.data.time.FrameGen
import com.payam1991gr.kmp.playground.data.time.frameGenImpl
import kotlinx.datetime.Clock
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
        single<TimeZoneRepository> { TimeZoneRepositoryImpl() }
        single<Clock> { Clock.System }
        single<RandomULongGenerator> { RandomULongGeneratorImpl() }
        factory<FrameGen> { frameGenImpl() }
    }
}

expect val dataStoreModule: Module
