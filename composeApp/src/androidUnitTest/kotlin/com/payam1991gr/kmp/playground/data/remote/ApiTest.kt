package com.payam1991gr.kmp.playground.data.remote

import app.cash.turbine.test
import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.model.RemoteData.Connecting
import com.payam1991gr.kmp.playground.data.model.RemoteData.Data
import com.payam1991gr.kmp.playground.data.model.RemoteData.Error
import com.payam1991gr.kmp.playground.data.model.WrittenNumber
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.engine.mock.respondOk
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import kotlinx.coroutines.test.runTest
import org.junit.Test
import java.net.ConnectException

class ApiTest {
    @Test
    fun `init state - test`() = runTest {
        ApiImpl(MockEngine { respondOk() }, coroutineContext).apply {
            assertThat(host).isEqualTo("http://192.168.1.101:8080")
            host = "server.com"
            assertThat(host).isEqualTo("server.com")
        }
    }

    @Test
    fun `home - OK - test`() = runTest {
        ApiImpl(MockEngine { respond(content = "Hello from server!") }).apply {
            home().test {
                assertThat(awaitItem()).isEqualTo(Connecting)
                assertThat(awaitItem()).isEqualTo(Data("Hello from server!", 200))
                awaitComplete()
            }
        }
    }

    @Test
    fun `home - Internal Server Error - test`() = runTest {
        ApiImpl(MockEngine { respondError(HttpStatusCode.InternalServerError) }).apply {
            home().test {
                assertThat(awaitItem()).isEqualTo(Connecting)
                assertThat(awaitItem()).isEqualTo(Error("Internal Server Error", 500))
                awaitComplete()
            }
        }
    }

    @Test
    fun `home - Connection Error - test`() = runTest {
        ApiImpl(MockEngine { throw ConnectException("Failed to connect to /server") }).apply {
            home().test {
                assertThat(awaitItem()).isEqualTo(Connecting)
                assertThat(awaitItem()).isEqualTo(Error("Failed to connect to /server"))
                awaitComplete()
            }
        }
    }

    @Test
    fun `writtenNumbers - OK - test`() = runTest {
        ApiImpl(MockEngine {
            respond(
                """[{value:2,string:"Two"}]""",
                headers = headersOf(
                    HttpHeaders.ContentType,
                    ContentType.Application.Json.toString(),
                )
            )
        }).apply {
            writtenNumbers(2, 2).test {
                assertThat(awaitItem()).isEqualTo(Connecting)
                assertThat(awaitItem()).isEqualTo(Data(listOf(WrittenNumber(2, "Two")), 200))
                awaitComplete()
            }
        }
    }

    @Test
    fun `writtenNumbers - negative 'from' - Bad Request Error - test`() = runTest {
        ApiImpl(MockEngine {
            respondError(HttpStatusCode.BadRequest, "0 ≤ form ≤ to ≤ 100")
        }).apply {
            writtenNumbers(-1, null).test {
                assertThat(awaitItem()).isEqualTo(Connecting)
                assertThat(awaitItem()).isEqualTo(Error("0 ≤ form ≤ to ≤ 100", 400))
                awaitComplete()
            }
        }
    }

    @Test
    fun `writtenNumbers - over 100 'to' - Bad Request Error - test`() = runTest {
        ApiImpl(MockEngine {
            respondError(HttpStatusCode.BadRequest, "0 ≤ form ≤ to ≤ 100")
        }).apply {
            writtenNumbers(null, 101).test {
                assertThat(awaitItem()).isEqualTo(Connecting)
                assertThat(awaitItem()).isEqualTo(Error("0 ≤ form ≤ to ≤ 100", 400))
                awaitComplete()
            }
        }
    }

    @Test
    fun `writtenNumbers - 'from' being greater than 'to' - Bad Request Error - test`() = runTest {
        ApiImpl(MockEngine {
            respondError(HttpStatusCode.BadRequest, "0 ≤ form ≤ to ≤ 100")
        }).apply {
            writtenNumbers(100, 0).test {
                assertThat(awaitItem()).isEqualTo(Connecting)
                assertThat(awaitItem()).isEqualTo(Error("0 ≤ form ≤ to ≤ 100", 400))
                awaitComplete()
            }
        }
    }

    @Test
    fun `writtenNumbers - Internal Server Error - test`() = runTest {
        ApiImpl(MockEngine {
            respondError(HttpStatusCode.InternalServerError)
        }).apply {
            writtenNumbers(null, null).test {
                assertThat(awaitItem()).isEqualTo(Connecting)
                assertThat(awaitItem()).isEqualTo(Error("Internal Server Error", 500))
                awaitComplete()
            }
        }
    }

    @Test
    fun `writtenNumbers - Connection Error - test`() = runTest {
        ApiImpl(MockEngine { throw ConnectException("Failed to connect to /server") }).apply {
            writtenNumbers(null, null).test {
                assertThat(awaitItem()).isEqualTo(Connecting)
                assertThat(awaitItem()).isEqualTo(Error("Failed to connect to /server"))
                awaitComplete()
            }
        }
    }
}
