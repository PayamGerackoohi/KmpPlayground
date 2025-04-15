package com.payam1991gr.kmp.playground.data.remote

import com.payam1991gr.kmp.playground.data.model.RemoteData
import com.payam1991gr.kmp.playground.data.model.RemoteData.Connecting
import com.payam1991gr.kmp.playground.data.model.RemoteData.Data
import com.payam1991gr.kmp.playground.data.model.RemoteData.Error
import com.payam1991gr.kmp.playground.data.model.WrittenNumber
import io.ktor.http.HttpStatusCode
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class FakeApi(
    override var host: String = "http://fake-server.com",
) : Api {
    private val randomBoolean get() = Random.nextBoolean()

    override fun home(): Flow<RemoteData<String>> = flow {
        emit(Connecting)
        wait()
        when (randomBoolean) {
            true -> Data("Hello from fake host!", 200)
            false -> Error(HttpStatusCode.InternalServerError)
        }.let { emit(it) }
    }

    override fun writtenNumbers(from: Int?, to: Int?) = flow {
        emit(Connecting)
        wait()
        if (from == null || to == null || from < 0 || to > 100 || from > to)
            emit(Error("Bad Request!\n0 ≤ form ≤ to ≤ 100", HttpStatusCode.BadRequest.value))
        else when (randomBoolean) {
            true -> Data((from..to).map { WrittenNumber.of(it) }, 200)
            false -> Error(HttpStatusCode.InternalServerError)
        }.let { emit(it) }
    }

    private suspend fun wait(durationMs: Long = 500) = delay(durationMs)
}
