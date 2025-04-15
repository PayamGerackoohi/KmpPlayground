package com.payam1991gr.kmp.playground

import com.payam1991gr.kmp.playground.data.model.WrittenNumber
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.delay
import kotlin.random.Random

fun main() {
    embeddedServer(
        Netty,
        host = "0.0.0.0",
        port = SERVER_PORT,
        module = { module { delay(Random.nextLong(1000)) } }
    ).start(wait = true)
}

fun Application.module(prelude: ResponsePrelude = ResponsePrelude {}) {
    install(ContentNegotiation) { json() }
    routing {
        get("/") {
            prelude()
            call.respondText("Ktor - ${Greeting().greet()}")
        }
        get("/written-numbers") {
            prelude()
            val from = call.queryParameters["from"]?.toInt() ?: 0
            val to = call.queryParameters.run {
                this["to"]?.toInt() ?: if (contains("from")) 100 else 5
            }
            if (from < 0 || to > 100 || from > to)
                call.respond(HttpStatusCode.BadRequest, "0 ≤ form ≤ to ≤ 100")
            else
                call.respond((from..to).map { WrittenNumber.of(it) })
        }
        // t*do get-post multipart
//        route("/file") {
//            get {}
//            post {}
//        }
    }
}

fun interface ResponsePrelude {
    suspend operator fun invoke()
}

/* t*do cleanup
//        json(Json {
//            prettyPrint = true
//            isLenient = true
//        })

call.respondText(contentType = ContentType.parse("text/html"),...)

get("/tasks/byPriority/{priority}") {
        val priorityAsText = call.parameters["priority"]
        if (priorityAsText == null) {
            call.respond(HttpStatusCode.BadRequest)
            return@get
        }

        try {
            val priority = Priority.valueOf(priorityAsText)
            val tasks = TaskRepository.tasksByPriority(priority)

            if (tasks.isEmpty()) {
                call.respond(HttpStatusCode.NotFound)
                return@get
            }

            call.respondText(
                contentType = ContentType.parse("text/html"),
                text = tasks.tasksAsTable()
            )
        } catch(ex: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest)
        }
    }

val response1 = client.post("/tasks") {
        header(
            HttpHeaders.ContentType,
            ContentType.Application.FormUrlEncoded.toString()
        )
        setBody(
            listOf(
                "name" to "swimming",
                "description" to "Go to the beach",
                "priority" to "Low"
            ).formUrlEncode()
        )
    }
    assertEquals(HttpStatusCode.NoContent, response1.status)

fun Application.configureRouting() {
    routing {
        staticResources("/task-ui", "task-ui")

        route("/tasks") {
            get {
                //Code remains the same
            }

            get("/byPriority/{priority}") {
                //Code remains the same
            }

            post {
                //Code remains the same
            }
        }
}

fun Application.configureRouting() {
    routing {
        staticResources("/task-ui", "task-ui")

        route("/tasks") {
            get {
                //Code remains the same
            }

            get("/byPriority/{priority}") {
                //Code remains the same
            }

            post {
                //Code remains the same
            }
        }
}

post("/customer") {
    val customer = call.receive<Customer>()
    customerStorage.add(customer)
    call.respondText("Customer stored correctly", status = HttpStatusCode.Created)

get("/customer/{id}") {
        val id: Int by call.parameters
        val customer: Customer = customerStorage.find { it.id == id }!!
        call.respond(customer)

val jsonResponse = """{
    "id": 1,
    "task": "Pay waterbill",
    "description": "Pay water bill today",
}"""

embeddedServer(Netty, 8080) {
  install(Routing) {
      get("/t*do") {
          call.respondText(jsonResponse, ContentType.Application.Json)
      }
  }
}.start(wait = true)

routing() {
    route("/t*do") {
        post {
            var t*Do = call.receive<T*Do>();
            t*Do.id = toDoList.size;
            toDoList.add(t*Do);
            call.respond("Added")

        }
        delete("/{id}") {
            call.respond(toDoList.removeAt(call.parameters["id"]!!.toInt()));
        }
        get("/{id}") {
            call.respond(toDoList[call.parameters["id"]!!.toInt()]);
        }
        get {
            call.respond(toDoList);
        }
    }
}

  val task = Task("swimming", "Go to the beach", Priority.Low)
        val response1 = client.post("/tasks") {
            header(
                HttpHeaders.ContentType,
                ContentType.Application.Json
            )

            setBody(task)
        }

*/
