package com.kazakago.blueprint.data.api.todo

import com.kazakago.blueprint.model.todo.ToDo
import com.kazakago.blueprint.model.todo.ToDoId
import com.kazakago.blueprint.model.todo.ToDoRegistration
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.path
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ToDoApi @Inject constructor(
    private val httpClient: HttpClient,
) {

    suspend fun getToDoList(): List<ToDo> {
        return httpClient.get {
            url { path("todos") }
        }.body()
    }

    suspend fun addToDo(toDoRegistration: ToDoRegistration): ToDo {
        return httpClient.post {
            url { path("todos") }
            setBody(toDoRegistration)
        }.body()
    }

    suspend fun editToDo(toDoId: ToDoId, toDoRegistration: ToDoRegistration): ToDo {
        return httpClient.put {
            url { path("todos/${toDoId.value}") }
            setBody(toDoRegistration)
        }.body()
    }

    suspend fun removeToDo(toDoId: ToDoId) {
        return httpClient.delete {
            url { path("todos/${toDoId.value}") }
        }.body()
    }
}
