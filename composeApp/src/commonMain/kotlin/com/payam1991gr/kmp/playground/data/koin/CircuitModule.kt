package com.payam1991gr.kmp.playground.data.koin

import com.payam1991gr.kmp.playground.ui.screens.animations.animate.x.`as`.AnimateXAs
import com.payam1991gr.kmp.playground.ui.screens.animations.animate.x.`as`.AnimateXAsPresenter
import com.payam1991gr.kmp.playground.ui.screens.animations.animate.x.`as`.AnimateXAsScreen
import com.payam1991gr.kmp.playground.ui.screens.animations.Animations
import com.payam1991gr.kmp.playground.ui.screens.animations.AnimationsPresenter
import com.payam1991gr.kmp.playground.ui.screens.animations.AnimationsScreen
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.content.AnimatedContent
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.content.AnimatedContentPresenter
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.content.AnimatedContentScreen
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.visibility.AnimatedVisibility
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.visibility.AnimatedVisibilityPresenter
import com.payam1991gr.kmp.playground.ui.screens.animations.animated.visibility.AnimatedVisibilityScreen
import com.payam1991gr.kmp.playground.ui.screens.animations.crossfade.Crossfade
import com.payam1991gr.kmp.playground.ui.screens.animations.crossfade.CrossfadePresenter
import com.payam1991gr.kmp.playground.ui.screens.animations.crossfade.CrossfadeScreen
import com.payam1991gr.kmp.playground.ui.screens.components.Components
import com.payam1991gr.kmp.playground.ui.screens.components.ComponentsPresenter
import com.payam1991gr.kmp.playground.ui.screens.components.ComponentsScreen
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.Dialog
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogPresenter
import com.payam1991gr.kmp.playground.ui.screens.components.dialog.DialogScreen
import com.payam1991gr.kmp.playground.ui.screens.components.carousel.Carousel
import com.payam1991gr.kmp.playground.ui.screens.components.carousel.CarouselPresenter
import com.payam1991gr.kmp.playground.ui.screens.components.carousel.CarouselScreen
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.DateTimePicker
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.DateTimePickerPresenter
import com.payam1991gr.kmp.playground.ui.screens.components.picker.datetime.DateTimePickerScreen
import com.payam1991gr.kmp.playground.ui.screens.components.pull.to.refresh.PullToRefresh
import com.payam1991gr.kmp.playground.ui.screens.components.pull.to.refresh.PullToRefreshPresenter
import com.payam1991gr.kmp.playground.ui.screens.components.pull.to.refresh.PullToRefreshScreen
import com.payam1991gr.kmp.playground.ui.screens.graphics.Graphics
import com.payam1991gr.kmp.playground.ui.screens.graphics.GraphicsPresenter
import com.payam1991gr.kmp.playground.ui.screens.graphics.GraphicsScreen
import com.payam1991gr.kmp.playground.ui.screens.graphics.charts.Charts
import com.payam1991gr.kmp.playground.ui.screens.graphics.charts.ChartsPresenter
import com.payam1991gr.kmp.playground.ui.screens.graphics.charts.ChartsScreen
import com.payam1991gr.kmp.playground.ui.screens.graphics.opengl.OpenGl
import com.payam1991gr.kmp.playground.ui.screens.graphics.opengl.OpenGlPresenter
import com.payam1991gr.kmp.playground.ui.screens.graphics.opengl.OpenGlScreen
import com.payam1991gr.kmp.playground.ui.screens.home.Home
import com.payam1991gr.kmp.playground.ui.screens.home.HomePresenter
import com.payam1991gr.kmp.playground.ui.screens.home.HomeScreen
import com.payam1991gr.kmp.playground.ui.screens.io.Io
import com.payam1991gr.kmp.playground.ui.screens.io.IoPresenter
import com.payam1991gr.kmp.playground.ui.screens.io.IoScreen
import com.payam1991gr.kmp.playground.ui.screens.io.api.Api
import com.payam1991gr.kmp.playground.ui.screens.io.api.ApiPresenter
import com.payam1991gr.kmp.playground.ui.screens.io.api.ApiScreen
import com.payam1991gr.kmp.playground.ui.screens.io.database.Database
import com.payam1991gr.kmp.playground.ui.screens.io.database.DatabasePresenter
import com.payam1991gr.kmp.playground.ui.screens.io.database.DatabaseScreen
import com.payam1991gr.kmp.playground.ui.screens.io.datastore.Datastore
import com.payam1991gr.kmp.playground.ui.screens.io.datastore.DatastorePresenter
import com.payam1991gr.kmp.playground.ui.screens.io.datastore.DatastoreScreen
import com.payam1991gr.kmp.playground.ui.screens.io.file.File
import com.payam1991gr.kmp.playground.ui.screens.io.file.FilePresenter
import com.payam1991gr.kmp.playground.ui.screens.io.file.FileScreen
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.Miscellaneous
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.MiscellaneousPresenter
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.MiscellaneousScreen
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.ble.Ble
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.ble.BlePresenter
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.ble.BleScreen
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.cpp.Cpp
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.cpp.CppPresenter
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.cpp.CppScreen
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.pdf.Pdf
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.pdf.PdfPresenter
import com.payam1991gr.kmp.playground.ui.screens.miscellaneous.pdf.PdfScreen
import com.slack.circuit.foundation.Circuit
import org.koin.dsl.module

val circuitModule by lazy {
    module {
        single<Circuit> {
            Circuit.Builder()
                .addUiFactory { screen, _ ->
                    when (screen) {
                        is AnimationsScreen -> Animations()
                        is AnimatedContentScreen -> AnimatedContent()
                        is AnimatedVisibilityScreen -> AnimatedVisibility()
                        is CrossfadeScreen -> Crossfade()
                        is AnimateXAsScreen -> AnimateXAs()
                        is ComponentsScreen -> Components()
                        is CarouselScreen -> Carousel()
                        is PullToRefreshScreen -> PullToRefresh()
                        is DialogScreen -> Dialog()
                        is DateTimePickerScreen -> DateTimePicker()
                        is GraphicsScreen -> Graphics()
                        is ChartsScreen -> Charts()
                        is OpenGlScreen -> OpenGl()
                        is HomeScreen -> Home()
                        is IoScreen -> Io()
                        is ApiScreen -> Api()
                        is DatabaseScreen -> Database()
                        is DatastoreScreen -> Datastore()
                        is FileScreen -> File()
                        is MiscellaneousScreen -> Miscellaneous()
                        is BleScreen -> Ble()
                        is CppScreen -> Cpp()
                        is PdfScreen -> Pdf()
                        else -> null
                    }
                }
                .addPresenterFactory { screen, navigator, _ ->
                    when (screen) {
                        is AnimationsScreen -> AnimationsPresenter(navigator)
                        is AnimatedContentScreen -> AnimatedContentPresenter(navigator)
                        is AnimatedVisibilityScreen -> AnimatedVisibilityPresenter(navigator)
                        is CrossfadeScreen -> CrossfadePresenter(navigator)
                        is AnimateXAsScreen -> AnimateXAsPresenter(navigator)
                        is ComponentsScreen -> ComponentsPresenter(navigator)
                        is CarouselScreen -> CarouselPresenter(navigator)
                        is PullToRefreshScreen -> PullToRefreshPresenter(navigator)
                        is DialogScreen -> DialogPresenter(navigator)
                        is DateTimePickerScreen -> DateTimePickerPresenter(navigator)
                        is GraphicsScreen -> GraphicsPresenter(navigator)
                        is ChartsScreen -> ChartsPresenter(navigator)
                        is OpenGlScreen -> OpenGlPresenter(navigator)
                        is HomeScreen -> HomePresenter(navigator)
                        is IoScreen -> IoPresenter(navigator)
                        is ApiScreen -> ApiPresenter(navigator)
                        is DatabaseScreen -> DatabasePresenter(navigator)
                        is DatastoreScreen -> DatastorePresenter(navigator)
                        is FileScreen -> FilePresenter(navigator)
                        is MiscellaneousScreen -> MiscellaneousPresenter(navigator)
                        is BleScreen -> BlePresenter(navigator)
                        is CppScreen -> CppPresenter(navigator)
                        is PdfScreen -> PdfPresenter(navigator)
                        else -> null
                    }
                }
                .build()
        }
    }
}
