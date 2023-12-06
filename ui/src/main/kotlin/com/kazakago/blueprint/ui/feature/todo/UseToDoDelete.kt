package com.kazakago.blueprint.ui.feature.todo

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.kazakago.blueprint.model.todo.ToDoId
import com.kazakago.blueprint.ui.global.di.LocalToDoApi
import com.kazakago.blueprint.ui.global.hooks.useErrorMessage
import kotlinx.coroutines.launch

@Composable
fun useToDoDelete(
    snackbarHostState: SnackbarHostState,
): (id: ToDoId) -> Unit {
    val scope = rememberCoroutineScope()
    val useErrorMessage = useErrorMessage()
    val toDoApi = LocalToDoApi.current
    val (data, _, _, _, mutate) = useToDo()
    return remember(snackbarHostState, scope, useErrorMessage, toDoApi, data, mutate) {
        { id ->
            scope.launch {
                runCatching {
                    mutate(data = {
                        toDoApi.removeToDo(id)
                        data.orEmpty().filter { it.id != id }
                    }) {
                        revalidate = false
                        optimisticData = data.orEmpty().filter { it.id != id }
                    }
                }.onFailure {
                    val message = useErrorMessage(it)
                    if (message != null) snackbarHostState.showSnackbar(message)
                }
            }
        }
    }
}
