package com.payam1991gr.kmp.playground

import android.app.Application
import android.os.Build
import com.payam1991gr.kmp.playground.data.koin.initKoin
import org.koin.android.ext.koin.androidContext

class AndroidApplication : Application() {
    private val isNotRobolectric = Build.FINGERPRINT != "robolectric"

    override fun onCreate() {
        super.onCreate()
        if (isNotRobolectric) initKoin {
            androidContext(this@AndroidApplication)
        }
    }
}
