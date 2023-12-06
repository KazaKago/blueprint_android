package com.kazakago.blueprint.ui.feature.todo

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kazakago.blueprint.ui.global.theme.AppTheme

@Composable
fun ToDoCreationDialog(
    onSubmit: (title: String) -> Unit,
    onCancel: () -> Unit,
) {
    ToDoEditingDialog(
        initialText = null,
        onSubmit = onSubmit,
        onDelete = {},
        onCancel = onCancel,
    )
}


@Preview
@Composable
fun ToDoCreationDialogPreview() {
    AppTheme {
        Surface {
            ToDoCreationDialog(
                onSubmit = {},
                onCancel = {},
            )
        }
    }
}
