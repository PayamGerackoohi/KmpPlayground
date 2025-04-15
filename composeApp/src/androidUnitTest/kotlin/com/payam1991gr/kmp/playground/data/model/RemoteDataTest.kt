package com.payam1991gr.kmp.playground.data.model

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.model.RemoteData.Connecting
import com.payam1991gr.kmp.playground.data.model.RemoteData.Data
import com.payam1991gr.kmp.playground.data.model.RemoteData.Error
import com.payam1991gr.kmp.playground.data.model.RemoteData.Init
import io.ktor.http.HttpStatusCode
import org.junit.Test

class RemoteDataTest {
    @Test
    fun `dummy - test`() {
        assertThat(Init).isEqualTo(Init)
        assertThat(Connecting).isEqualTo(Connecting)
    }

    @Test
    fun `Data - test`() {
        Data("string").apply {
            assertThat(data).isEqualTo("string")
            assertThat(status).isNull()
        }
        Data(123, 321).apply {
            assertThat(data).isEqualTo(123)
            assertThat(status).isEqualTo(321)
        }
    }

    @Test
    fun `Error - test`() {
        Error("Error 1").apply {
            assertThat(message).isEqualTo("Error 1")
            assertThat(status).isNull()
        }
        Error("Error 2", 123).apply {
            assertThat(message).isEqualTo("Error 2")
            assertThat(status).isEqualTo(123)
        }
        Error(Exception("Error 3")).apply {
            assertThat(message).isEqualTo("Error 3")
            assertThat(status).isNull()
        }
        Error(kotlin.Error("Error 4")).apply {
            assertThat(message).isEqualTo("Error 4")
            assertThat(status).isNull()
        }
        Error(Exception()).apply {
            assertThat(message).isEqualTo("?")
            assertThat(status).isNull()
        }
        Error(kotlin.Error()).apply {
            assertThat(message).isEqualTo("?")
            assertThat(status).isNull()
        }
        Error(HttpStatusCode.BadRequest).apply {
            assertThat(message).isEqualTo("Bad Request")
            assertThat(status).isEqualTo(400)
        }
        Error(HttpStatusCode.OK).apply { // !
            assertThat(message).isEqualTo("OK")
            assertThat(status).isEqualTo(200)
        }
    }

    @Test
    fun `status - test`() {
        listOf(
            Init to null,
            Connecting to null,
            Data("string") to null,
            Data(123, 321) to 321,
            Error("Error 1") to null,
            Error("Error 2", 123) to 123,
            Error(Exception("Error 3")) to null,
            Error(kotlin.Error("Error 4")) to null,
            Error(Exception()) to null,
            Error(kotlin.Error()) to null,
            Error(HttpStatusCode.BadRequest) to 400,
            Error(HttpStatusCode.OK) to 200,
        ).forEach { (input, output) ->
            assertThat(input.status()).isEqualTo(output)
        }
    }
}
