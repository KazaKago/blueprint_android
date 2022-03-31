package com.kazakago.blueprint.presentation.view.hierarchy.github

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgId
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.global.theme.PreviewTheme
import com.kazakago.blueprint.presentation.view.global.util.clickableWithRipple

@Composable
fun GithubOrgRow(
    githubOrg: GithubOrg,
    onClickItem: (githubOrg: GithubOrg) -> Unit,
) {
    Column(
        modifier = Modifier
            .clickableWithRipple(onClick = { onClickItem(githubOrg) })
            .fillMaxWidth()
            .padding(12.dp),
    ) {
        Text(
            text = stringResource(R.string.id, githubOrg.id.value),
            style = MaterialTheme.typography.labelMedium,
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = githubOrg.name.value,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGithubOrgRow() {
    PreviewTheme {
        GithubOrgRow(
            githubOrg = GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago")),
            onClickItem = {},
        )
    }
}
