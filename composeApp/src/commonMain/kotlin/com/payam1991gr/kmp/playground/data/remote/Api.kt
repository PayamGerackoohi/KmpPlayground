package com.payam1991gr.kmp.playground.data.remote

import com.payam1991gr.kmp.playground.data.model.RemoteData
import com.payam1991gr.kmp.playground.data.model.WrittenNumber
import kotlinx.coroutines.flow.Flow

interface Api {
    /**
     * Server Address
     * http://192.168.1.101:8080
     */
    var host: String

    fun home(): Flow<RemoteData<String>>

    /**
     * It converts integers into their written form
     * ### Errors
     * - 400    Bad Request
     * @param from start of the range (0 ≤ `from` ≤ `to` ≤ 100)
     * @param to end of the range (0 ≤ `from` ≤ `to` ≤ 100)
     */
    fun writtenNumbers(from: Int?, to: Int?): Flow<RemoteData<List<WrittenNumber>>>
}
