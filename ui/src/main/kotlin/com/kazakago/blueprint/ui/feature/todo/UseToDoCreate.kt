package com.kazakago.blueprint.ui.feature.todo

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.kazakago.blueprint.model.todo.ToDo
import com.kazakago.blueprint.model.todo.ToDoRegistration
import com.kazakago.blueprint.ui.global.di.LocalToDoApi
import com.kazakago.blueprint.ui.global.hooks.useErrorMessage
import kotlinx.coroutines.launch

@Composable
fun useToDoCreate(
    snackbarHostState: SnackbarHostState,
): (title: String) -> Unit {
    val scope = rememberCoroutineScope()
    val useErrorMessage = useErrorMessage()
    val toDoApi = LocalToDoApi.current
    val (data, _, _, _, mutate) = useToDo()
    return remember(snackbarHostState, scope, useErrorMessage, toDoApi, data, mutate) {
        { title ->
            scope.launch {
                runCatching {
                    mutate(data = {
                        val addedToDo = toDoApi.addToDo(ToDoRegistration(title))
                        data.orEmpty() + addedToDo
                    }) {
                        revalidate = false
                        optimisticData = data.orEmpty() + ToDo(id = null, title = title)
                    }
                }.onFailure {
                    val message = useErrorMessage(it)
                    if (message != null) snackbarHostState.showSnackbar(message)
                }
            }
        }
    }
}
