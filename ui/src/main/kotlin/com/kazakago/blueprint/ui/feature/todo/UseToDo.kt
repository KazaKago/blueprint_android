package com.kazakago.blueprint.ui.feature.todo

import androidx.compose.runtime.Composable
import com.kazakago.blueprint.model.todo.ToDo
import com.kazakago.blueprint.ui.global.di.LocalToDoApi
import com.kazakago.swr.compose.state.SWRState
import com.kazakago.swr.compose.useSWR

@Composable
fun useToDo(): SWRState<String, List<ToDo>> {
    val toDoApi = LocalToDoApi.current
    return useSWR(
        key = "/get_todos/$toDoApi",
        fetcher = { toDoApi.getToDoList() },
    )
}
