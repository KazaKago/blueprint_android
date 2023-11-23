package com.kazakago.blueprint.presentation.global.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kazakago.blueprint.presentation.global.theme.AppTheme

@Composable
fun LoadingRow() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CircularProgressIndicator()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoadingRow() {
    AppTheme {
        Surface {
            LoadingRow()
        }
    }
}
