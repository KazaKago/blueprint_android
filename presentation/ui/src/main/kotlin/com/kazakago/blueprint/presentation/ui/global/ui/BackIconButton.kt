package com.kazakago.blueprint.presentation.ui.global.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.kazakago.blueprint.presentation.ui.global.theme.PreviewTheme

@Composable
fun BackIconButton(onClick: () -> Unit) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBackIconButton() {
    PreviewTheme {
        Surface {
            BackIconButton {}
        }
    }
}
