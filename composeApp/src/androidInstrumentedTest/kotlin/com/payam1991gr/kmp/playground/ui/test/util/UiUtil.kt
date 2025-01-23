package com.payam1991gr.kmp.playground.ui.test.util

import android.support.test.uiautomator.UiDevice
import androidx.test.platform.app.InstrumentationRegistry

private val instrumentation get() = InstrumentationRegistry.getInstrumentation()

val uiDevice: UiDevice get() = UiDevice.getInstance(instrumentation)
