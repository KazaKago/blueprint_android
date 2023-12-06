package com.kazakago.blueprint.model.todo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ToDoRegistration(
    @SerialName("title")
    val title: String,
)
