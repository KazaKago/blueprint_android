package com.kazakago.blueprint.ui.feature.todo

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kazakago.blueprint.model.todo.ToDo
import com.kazakago.blueprint.model.todo.ToDoId
import com.kazakago.blueprint.ui.feature.destinations.AboutScreenDestination
import com.kazakago.blueprint.ui.global.theme.AppTheme
import com.kazakago.blueprint.ui.global.ui.DefaultLayout
import com.kazakago.blueprint.ui.global.utils.plus
import com.kazakago.swr.compose.state.SWRState
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ToDoListScreen(
    navigator: DestinationsNavigator,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    state: SWRState<String, List<ToDo>> = useToDo(),
    create: (title: String) -> Unit = useToDoCreate(snackbarHostState),
    edit: (toDoId: ToDoId, title: String) -> Unit = useToDoEdit(snackbarHostState),
    delete: (id: ToDoId) -> Unit = useToDoDelete(snackbarHostState),
) {
    val openToDoCreationDialog: MutableState<Boolean> = remember { mutableStateOf(false) }
    val openToDoEditingDialog: MutableState<ToDo?> = remember { mutableStateOf(null) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "ToDo List") },
                actions = {
                    IconButton(onClick = { navigator.navigate(AboutScreenDestination()) }) {
                        Icon(Icons.Outlined.Info, contentDescription = null)
                    }
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { openToDoCreationDialog.value = true }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        },
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
    ) { innerPadding ->
        DefaultLayout(
            state = state,
            snackbarHostState = snackbarHostState,
        ) { todoList ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .consumeWindowInsets(innerPadding),
                contentPadding = innerPadding + PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            ) {
                items(todoList.size) { index ->
                    ToDoRow(
                        toDo = todoList[index],
                        onClick = { openToDoEditingDialog.value = it },
                    )
                    if (index < todoList.size - 1) {
                        Spacer(Modifier.size(8.dp))
                    }
                }
            }
        }
    }
    if (openToDoCreationDialog.value) {
        ToDoCreationDialog(
            onSubmit = { text ->
                openToDoCreationDialog.value = false
                create(text)
            },
            onCancel = {
                openToDoCreationDialog.value = false
            },
        )
    }
    openToDoEditingDialog.value?.let { toDo ->
        ToDoEditingDialog(
            initialText = toDo.title,
            onSubmit = { text ->
                openToDoEditingDialog.value = null
                toDo.id?.let { id -> edit(id, text) }
            },
            onCancel = {
                openToDoEditingDialog.value = null
            },
            onDelete = {
                openToDoEditingDialog.value = null
                toDo.id?.let(delete)
            },
        )
    }
}

@Preview
@Composable
fun ToDoListScreenPreview() {
    AppTheme {
        ToDoListScreen(
            navigator = EmptyDestinationsNavigator,
            state = SWRState.empty(
                data = listOf(
                    ToDo(ToDoId(1), "Remember the milk"),
                    ToDo(ToDoId(2), "Call bob at 5pm."),
                ),
            ),
            create = {},
            edit = { _, _ -> },
            delete = {},
        )
    }
}
