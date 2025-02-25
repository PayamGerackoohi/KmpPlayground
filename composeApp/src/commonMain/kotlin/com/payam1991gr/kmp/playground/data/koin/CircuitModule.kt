package com.payam1991gr.kmp.playground.data.koin

import com.payam1991gr.kmp.playground.presenter.screens.animations.AnimationsPresenter
import com.payam1991gr.kmp.playground.presenter.screens.animations.animate.x.`as`.AnimateXAsPresenter
import com.payam1991gr.kmp.playground.presenter.screens.animations.animated.content.AnimatedContentPresenter
import com.payam1991gr.kmp.playground.presenter.screens.animations.animated.visibility.AnimatedVisibilityPresenter
import com.payam1991gr.kmp.playground.presenter.screens.animations.crossfade.CrossfadePresenter
import com.payam1991gr.kmp.playground.presenter.screens.components.ComponentsPresenter
import com.payam1991gr.kmp.playground.presenter.screens.components.dialog.DialogPresenter
import com.payam1991gr.kmp.playground.presenter.screens.components.carousel.CarouselPresenter
import com.payam1991gr.kmp.playground.presenter.screens.components.picker.datetime.DateTimePickerPresenter
import com.payam1991gr.kmp.playground.presenter.screens.components.pull.to.refresh.PullToRefreshPresenter
import com.payam1991gr.kmp.playground.presenter.screens.graphics.GraphicsPresenter
import com.payam1991gr.kmp.playground.presenter.screens.graphics.charts.ChartsPresenter
import com.payam1991gr.kmp.playground.presenter.screens.graphics.color.scheme.ColorSchemePresenter
import com.payam1991gr.kmp.playground.presenter.screens.graphics.icons.IconsPresenter
import com.payam1991gr.kmp.playground.presenter.screens.graphics.opengl.OpenGlPresenter
import com.payam1991gr.kmp.playground.presenter.screens.home.HomePresenter
import com.payam1991gr.kmp.playground.presenter.screens.io.IoPresenter
import com.payam1991gr.kmp.playground.presenter.screens.io.api.ApiPresenter
import com.payam1991gr.kmp.playground.presenter.screens.io.database.DatabasePresenter
import com.payam1991gr.kmp.playground.presenter.screens.io.datastore.DatastorePresenter
import com.payam1991gr.kmp.playground.presenter.screens.io.file.FilePresenter
import com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.MiscellaneousPresenter
import com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.ble.BlePresenter
import com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.cpp.CppPresenter
import com.payam1991gr.kmp.playground.presenter.screens.miscellaneous.pdf.PdfPresenter
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.AnimateXAs
import com.payam1991gr.kmp.playground.view.screens.animations.animate.x.`as`.AnimateXAsScreen
import com.payam1991gr.kmp.playground.view.screens.animations.Animations
import com.payam1991gr.kmp.playground.view.screens.animations.AnimationsScreen
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContent
import com.payam1991gr.kmp.playground.view.screens.animations.animated.content.AnimatedContentScreen
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibility
import com.payam1991gr.kmp.playground.view.screens.animations.animated.visibility.AnimatedVisibilityScreen
import com.payam1991gr.kmp.playground.view.screens.animations.crossfade.Crossfade
import com.payam1991gr.kmp.playground.view.screens.animations.crossfade.CrossfadeScreen
import com.payam1991gr.kmp.playground.view.screens.components.Components
import com.payam1991gr.kmp.playground.view.screens.components.ComponentsScreen
import com.payam1991gr.kmp.playground.view.screens.components.dialog.Dialog
import com.payam1991gr.kmp.playground.view.screens.components.dialog.DialogScreen
import com.payam1991gr.kmp.playground.view.screens.components.carousel.Carousel
import com.payam1991gr.kmp.playground.view.screens.components.carousel.CarouselScreen
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePicker
import com.payam1991gr.kmp.playground.view.screens.components.picker.datetime.DateTimePickerScreen
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefresh
import com.payam1991gr.kmp.playground.view.screens.components.pull.to.refresh.PullToRefreshScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.Graphics
import com.payam1991gr.kmp.playground.view.screens.graphics.GraphicsScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.charts.Charts
import com.payam1991gr.kmp.playground.view.screens.graphics.charts.ChartsScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.ColorScheme
import com.payam1991gr.kmp.playground.view.screens.graphics.color.scheme.ColorSchemeScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.Icons
import com.payam1991gr.kmp.playground.view.screens.graphics.icons.IconsScreen
import com.payam1991gr.kmp.playground.view.screens.graphics.opengl.OpenGl
import com.payam1991gr.kmp.playground.view.screens.graphics.opengl.OpenGlScreen
import com.payam1991gr.kmp.playground.view.screens.home.Home
import com.payam1991gr.kmp.playground.view.screens.home.HomeScreen
import com.payam1991gr.kmp.playground.view.screens.io.Io
import com.payam1991gr.kmp.playground.view.screens.io.IoScreen
import com.payam1991gr.kmp.playground.view.screens.io.api.Api
import com.payam1991gr.kmp.playground.view.screens.io.api.ApiScreen
import com.payam1991gr.kmp.playground.view.screens.io.database.Database
import com.payam1991gr.kmp.playground.view.screens.io.database.DatabaseScreen
import com.payam1991gr.kmp.playground.view.screens.io.datastore.Datastore
import com.payam1991gr.kmp.playground.view.screens.io.datastore.DatastoreScreen
import com.payam1991gr.kmp.playground.view.screens.io.file.File
import com.payam1991gr.kmp.playground.view.screens.io.file.FileScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.Miscellaneous
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.MiscellaneousScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.ble.Ble
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.ble.BleScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.Cpp
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.cpp.CppScreen
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.pdf.Pdf
import com.payam1991gr.kmp.playground.view.screens.miscellaneous.pdf.PdfScreen
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
                        is ColorSchemeScreen -> ColorScheme()
                        is IconsScreen -> Icons()
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
                        is ColorSchemeScreen -> ColorSchemePresenter(navigator)
                        is IconsScreen -> IconsPresenter(navigator, get())
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
