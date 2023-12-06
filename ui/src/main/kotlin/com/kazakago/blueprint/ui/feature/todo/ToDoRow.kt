package com.kazakago.blueprint.ui.feature.todo

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kazakago.blueprint.model.todo.ToDo
import com.kazakago.blueprint.model.todo.ToDoId
import com.kazakago.blueprint.ui.global.theme.AppTheme

@Composable
internal fun ToDoRow(
    toDo: ToDo,
    onClick: (toDo: ToDo) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = { onClick(toDo) }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val toDoId = toDo.id
            if (toDoId != null) {
                Text(text = "${toDoId.value}:", style = MaterialTheme.typography.titleLarge)
            } else {
                CircularProgressIndicator(Modifier.size(24.dp))
            }
            Spacer(Modifier.size(8.dp))
            Text(
                text = toDo.title,
                style = MaterialTheme.typography.titleLarge,
                color = if (toDoId != null) Color.Unspecified else Color.Gray,
            )
        }
    }
}

@Preview
@Composable
fun ToDoRowPreview() {
    AppTheme {
        Surface {
            ToDoRow(
                modifier = Modifier.padding(8.dp),
                toDo = ToDo(id = ToDoId(1), title = "Remember the milk"),
                onClick = {},
            )
        }
    }
}

@Preview
@Composable
fun ToDoRowLoadingPreview() {
    AppTheme {
        Surface {
            ToDoRow(
                modifier = Modifier.padding(8.dp),
                toDo = ToDo(id = null, title = "Remember the milk"),
                onClick = {},
            )
        }
    }
}
