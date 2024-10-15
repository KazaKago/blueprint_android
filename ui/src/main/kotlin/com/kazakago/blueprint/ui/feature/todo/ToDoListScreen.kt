package com.kazakago.blueprint.ui.feature.todo

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kazakago.blueprint.model.todo.ToDo
import com.kazakago.blueprint.model.todo.ToDoId
import com.kazakago.blueprint.ui.global.theme.AppTheme
import com.kazakago.blueprint.ui.global.ui.StateLayout
import com.kazakago.blueprint.ui.global.utils.plus
import com.kazakago.swr.compose.state.SWRState
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Serializable
object ToDoList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToDoListScreen(
    onNavigateAbout: () -> Unit,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    state: SWRState<String, List<ToDo>> = useToDo(),
    create: (title: String) -> Unit = useToDoCreate(snackbarHostState),
    edit: (toDoId: ToDoId, title: String) -> Unit = useToDoEdit(snackbarHostState),
    delete: (id: ToDoId) -> Unit = useToDoDelete(snackbarHostState),
) {
    val openToDoCreationDialog = remember { mutableStateOf(false) }
    val openToDoEditingDialog = remember { mutableStateOf<ToDo?>(null) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "ToDo List") },
                actions = {
                    IconButton(onClick = onNavigateAbout) {
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
        StateLayout(
            state = state,
            snackbarHostState = snackbarHostState,
        ) { todoList ->
            val scope = rememberCoroutineScope()
            var isPullToRefreshing by remember { mutableStateOf(false) }
            val pullToRefreshState = rememberPullToRefreshState()
            PullToRefreshBox(
                isRefreshing = isPullToRefreshing || state.isValidating,
                state = pullToRefreshState,
                indicator = {
                    Indicator(
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = innerPadding.calculateTopPadding()),
                        isRefreshing = isPullToRefreshing || state.isValidating,
                        state = pullToRefreshState,
                    )
                },
                onRefresh = {
                    scope.launch {
                        isPullToRefreshing = true
                        runCatching { state.mutate() }
                        isPullToRefreshing = false
                    }
                },
            ) {
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
            onNavigateAbout = {},
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
