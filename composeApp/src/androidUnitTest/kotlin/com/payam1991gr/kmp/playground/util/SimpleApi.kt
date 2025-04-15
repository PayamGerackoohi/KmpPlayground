package com.payam1991gr.kmp.playground.util

import com.payam1991gr.kmp.playground.data.model.RemoteData
import com.payam1991gr.kmp.playground.data.remote.Api
import com.payam1991gr.kmp.playground.presenter.screens.io.api.Numbers
import kotlinx.coroutines.flow.flowOf

open class SimpleApi : Api {
    override var host: String = ""
    override fun home() = flowOf<RemoteData<String>>()
    override fun writtenNumbers(from: Int?, to: Int?) = flowOf<RemoteData<Numbers>>()
}
