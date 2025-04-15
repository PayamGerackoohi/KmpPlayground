package com.payam1991gr.kmp.playground.data.model

import io.ktor.http.HttpStatusCode

sealed interface RemoteData<out T> {
    data object Init : RemoteData<Nothing>

    data object Connecting : RemoteData<Nothing>

    data class Data<T>(val data: T, val status: Int? = null) : RemoteData<T>

    data class Error(
        val message: String = "Error!",
        val status: Int? = null,
    ) : RemoteData<Nothing> {
        constructor(t: Throwable) : this(t.message ?: "?")
        constructor(code: HttpStatusCode) : this(code.description, code.value)
    }

    fun status() = when (this) {
        is Data -> status
        is Error -> status
        else -> null
    }
}
