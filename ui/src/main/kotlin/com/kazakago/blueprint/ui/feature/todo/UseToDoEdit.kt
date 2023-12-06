package com.kazakago.blueprint.ui.feature.todo

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.kazakago.blueprint.model.todo.ToDo
import com.kazakago.blueprint.ui.global.di.LocalToDoApi
import com.kazakago.blueprint.ui.global.hooks.useErrorMessage
import kotlinx.coroutines.launch

@Composable
fun useToDoEdit(
    snackbarHostState: SnackbarHostState,
): (toDo: ToDo) -> Unit {
    val scope = rememberCoroutineScope()
    val useErrorMessage = useErrorMessage()
    val toDoApi = LocalToDoApi.current
    val (data, _, _, _, mutate) = useToDo()
    return remember(snackbarHostState, scope, useErrorMessage, toDoApi, data, mutate) {
        { toDo ->
            scope.launch {
                runCatching {
                    mutate(data = {
                        val editedToDo = toDoApi.editToDo(toDo)
                        data.orEmpty().map { if (it.id == editedToDo.id) editedToDo else it }
                    }) {
                        revalidate = false
                        optimisticData = data.orEmpty().map { if (it.id == toDo.id) toDo else it }
                    }
                }.onFailure {
                    val message = useErrorMessage(it)
                    if (message != null) snackbarHostState.showSnackbar(message)
                }
            }
        }
    }
}
