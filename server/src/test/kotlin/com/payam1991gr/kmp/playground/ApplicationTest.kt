package com.payam1991gr.kmp.playground

import com.google.common.truth.Truth.*
import com.payam1991gr.kmp.playground.data.model.WrittenNumber
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.server.testing.testApplication
import org.junit.Test

class ApplicationTest {
    @Test
    fun `get home - test`() = testApplication {
        application {
            module()
        }
        client.get("/").apply {
            assertThat(status).isEqualTo(HttpStatusCode.OK)
            assertThat(bodyAsText()).isEqualTo("Ktor - Java 17.0.14")
        }
    }

    @Test
    fun `written-numbers - 1 to 3 - OK - test`() = testApplication {
        application {
            module()
        }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        client.get("/written-numbers?from=1&to=3").apply {
            assertThat(status).isEqualTo(HttpStatusCode.OK)
            assertThat(body<List<WrittenNumber>>()).isEqualTo(
                listOf(
                    WrittenNumber(1, "One"),
                    WrittenNumber(2, "Two"),
                    WrittenNumber(3, "Three"),
                )
            )
        }
    }

    @Test
    fun `written-numbers - missing 'from' - OK - 'from' is set to 0 - test`() = testApplication {
        application {
            module()
        }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        client.get("/written-numbers?to=3").apply {
            assertThat(status).isEqualTo(HttpStatusCode.OK)
            assertThat(body<List<WrittenNumber>>()).isEqualTo(
                listOf(
                    WrittenNumber(0, "Zero"),
                    WrittenNumber(1, "One"),
                    WrittenNumber(2, "Two"),
                    WrittenNumber(3, "Three"),
                )
            )
        }
    }

    @Test
    fun `written-numbers - missing 'to' - OK - 'to' is set to 100 - test`() = testApplication {
        application {
            module()
        }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        client.get("/written-numbers?from=98").apply {
            assertThat(status).isEqualTo(HttpStatusCode.OK)
            assertThat(body<List<WrittenNumber>>()).isEqualTo(
                listOf(
                    WrittenNumber(98, "Ninety Eight"),
                    WrittenNumber(99, "Ninety Nine"),
                    WrittenNumber(100, "One Hundred"),
                )
            )
        }
    }

    @Test
    fun `written-numbers - missing 'from' and 'to' - OK - set to 0-5 - test`() = testApplication {
        application {
            module()
        }
        val client = createClient {
            install(ContentNegotiation) {
                json()
            }
        }

        client.get("/written-numbers").apply {
            assertThat(status).isEqualTo(HttpStatusCode.OK)
            assertThat(body<List<WrittenNumber>>()).isEqualTo(
                listOf(
                    WrittenNumber(0, "Zero"),
                    WrittenNumber(1, "One"),
                    WrittenNumber(2, "Two"),
                    WrittenNumber(3, "Three"),
                    WrittenNumber(4, "Four"),
                    WrittenNumber(5, "Five"),
                )
            )
        }
    }

    @Test
    fun `written-numbers - invalid 'from' - BadRequest - test`() = testApplication {
        application {
            module()
        }
        client.get("/written-numbers?from=-1&to=3").apply {
            assertThat(status).isEqualTo(HttpStatusCode.BadRequest)
            assertThat(bodyAsText()).isEqualTo("0 ≤ form ≤ to ≤ 100")
        }
    }

    @Test
    fun `written-numbers - invalid 'to' - BadRequest - test`() = testApplication {
        application {
            module()
        }
        client.get("/written-numbers?from=1&to=103").apply {
            assertThat(status).isEqualTo(HttpStatusCode.BadRequest)
            assertThat(bodyAsText()).isEqualTo("0 ≤ form ≤ to ≤ 100")
        }
    }

    @Test
    fun `written-numbers - 'from' greater than 'to' - BadRequest - test`() = testApplication {
        application {
            module()
        }
        client.get("/written-numbers?from=3&to=1").apply {
            assertThat(status).isEqualTo(HttpStatusCode.BadRequest)
            assertThat(bodyAsText()).isEqualTo("0 ≤ form ≤ to ≤ 100")
        }
    }
}
//accept(ContentType.Application.Json)
//client.get("/json/123/456").apply {
