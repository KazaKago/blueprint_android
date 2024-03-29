package com.kazakago.mockserver

import com.kazakago.blueprint.model.todo.ToDo
import com.kazakago.blueprint.model.todo.ToDoId
import com.kazakago.blueprint.model.todo.ToDoRegistration
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.call
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import kotlinx.coroutines.delay

private var idCounter: Long = 2
private var todoList: List<ToDo> = listOf(
    ToDo(id = ToDoId(0), title = "Remember the milk"),
    ToDo(id = ToDoId(1), title = "Call bob at 5pm."),
)

fun main() {
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }
        routing {
            route("/todos") {
                get {
                    delay(1000)
                    call.respond(HttpStatusCode.OK, todoList)
                }
                post {
                    delay(1000)
                    val registrationToDo = call.receive<ToDoRegistration>()
                    val toDoId = ToDoId(idCounter++)
                    val addingToDo = ToDo(id = toDoId, title = registrationToDo.title)
                    todoList = todoList + addingToDo
                    call.respond(HttpStatusCode.Created, addingToDo)
                }
                route("/{id}") {
                    put {
                        delay(1000)
                        val registrationToDo = call.receive<ToDoRegistration>()
                        val toDoId = call.parameters["id"]?.toLong()?.let { ToDoId(it) }
                        val updatingToDo = ToDo(id = toDoId, title = registrationToDo.title)
                        todoList = todoList.map { if (it.id == toDoId) updatingToDo else it }
                        call.respond(HttpStatusCode.OK, updatingToDo)
                    }
                    delete {
                        delay(1000)
                        val toDoId = call.parameters["id"]?.toLong()?.let { ToDoId(it) }
                        todoList = todoList.filter { it.id != toDoId }
                        call.respond(HttpStatusCode.NoContent)
                    }
                }
            }
        }
    }.start(wait = true)
}
