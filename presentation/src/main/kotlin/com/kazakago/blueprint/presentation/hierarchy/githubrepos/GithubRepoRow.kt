package com.kazakago.blueprint.presentation.hierarchy.githubrepos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kazakago.blueprint.domain.model.github.GithubRepo
import com.kazakago.blueprint.domain.model.github.GithubRepoId
import com.kazakago.blueprint.presentation.R
import com.kazakago.blueprint.presentation.global.theme.AppTheme
import java.net.URL

@Composable
fun GithubRepoRow(
    githubRepo: GithubRepo,
    onClickItem: (githubRepo: GithubRepo) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clickable(onClick = { onClickItem(githubRepo) })
            .fillMaxWidth()
            .padding(16.dp),
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
fun GithubRepoRowPreview() {
    AppTheme {
        Surface {
            GithubRepoRow(
                githubRepo = GithubRepo(id = GithubRepoId(1), name = "cueue_server", url = URL("https://github.com/KazaKago/cueue_server")),
                onClickItem = {},
            )
        }
    }
}
