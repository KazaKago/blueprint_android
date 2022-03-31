package com.kazakago.blueprint.presentation.view.hierarchy.github

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepoId
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.global.theme.PreviewTheme
import com.kazakago.blueprint.presentation.view.global.util.clickableWithRipple
import java.net.URL

@Composable
fun GithubRepoRow(
    githubRepo: GithubRepo,
    onClickItem: (githubRepo: GithubRepo) -> Unit,
) {
    Column(
        modifier = Modifier
            .clickableWithRipple(onClick = { onClickItem(githubRepo) })
            .fillMaxWidth()
            .padding(12.dp),
    ) {
        Text(
            text = stringResource(R.string.id, githubRepo.id.value),
            style = MaterialTheme.typography.labelMedium,
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = githubRepo.name,
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(modifier = Modifier.size(2.dp))
        Text(
            text = githubRepo.url.toString(),
            color = MaterialTheme.colorScheme.primary,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGithubRepoRow() {
    PreviewTheme {
        GithubRepoRow(
            githubRepo = GithubRepo(id = GithubRepoId(1), name = "cueue_server", url = URL("https://github.com/KazaKago/cueue_server")),
            onClickItem = {},
        )
    }
}
