package com.payam1991gr.kmp.playground.data.remote

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.payam1991gr.kmp.playground.data.model.RemoteData.Connecting
import com.payam1991gr.kmp.playground.data.model.RemoteData.Data
import com.payam1991gr.kmp.playground.data.model.RemoteData.Error
import com.payam1991gr.kmp.playground.data.model.WrittenNumber
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext

class ApiImpl(
    httpClientEngine: HttpClientEngine,
    private val io: CoroutineContext = Dispatchers.IO,
) : Api {
    override var host by mutableStateOf("http://192.168.1.101:8080")
    private val client = HttpClient(httpClientEngine) {
        install(ContentNegotiation) { json() }
    }

    override fun home() = flow {
        emit(Connecting)
        try {
            client.get("$host/").let {
                val text = it.bodyAsText()
                val (status, isSuccess) = it.status()
                if (isSuccess) Data(text, status)
                else Error(text, status)
            }
        } catch (t: Throwable) {
            Error(t)
        }.let { emit(it) }
    }.flowOn(io)

    override fun writtenNumbers(from: Int?, to: Int?) = flow {
        emit(Connecting)
        try {
            client.get("$host/written-numbers") {
                parameter("from", from)
                parameter("to", to)
            }.let {
                val (status, isSuccess) = it.status()
                if (isSuccess) Data(it.body<List<WrittenNumber>>(), status)
                else Error(it.bodyAsText(), status)
            }
        } catch (t: Throwable) {
            Error(t)
        }.let { emit(it) }
    }.flowOn(io)

    private fun HttpResponse.status() = status.run { value to isSuccess() }
}
