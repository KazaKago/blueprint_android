package com.kazakago.blueprint.presentation.global.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kazakago.blueprint.presentation.R
import com.kazakago.blueprint.presentation.global.theme.AppTheme

@Composable
fun ErrorRow(
    error: Throwable,
    onRetry: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = error.toString(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.error,
        )
        Spacer(modifier = Modifier.size(2.dp))
        Button(onClick = onRetry) {
            Text(stringResource(id = R.string.retry))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewErrorRow() {
    AppTheme {
        Surface {
            ErrorRow(
                error = IllegalAccessException("hogehogehogehogehogehogehogehogehogehoge"),
                onRetry = {},
            )
        }
    }
}
