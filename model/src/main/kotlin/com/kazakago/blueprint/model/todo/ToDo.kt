package com.kazakago.blueprint.model.todo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ToDo(
    @SerialName("id")
    val id: ToDoId?,
    @SerialName("title")
    val title: String,
)
