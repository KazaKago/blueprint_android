package com.kazakago.blueprint.presentation.view.hierarchy.github

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgId
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.global.theme.PreviewTheme
import java.net.URL

@Composable
fun GithubRepoRowTop(
    githubOrg: GithubOrg,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(githubOrg.imageUrl.toString())
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(128.dp)
                .clip(RoundedCornerShape(12.dp)),
        )
        Spacer(modifier = Modifier.size(16.dp))
        Text(
            text = stringResource(R.string.id, githubOrg.id.value),
            style = MaterialTheme.typography.labelMedium,
        )
        Text(
            text = githubOrg.name.value,
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewGithubRepoRowTop() {
    PreviewTheme {
        Surface {
            GithubRepoRowTop(
                githubOrg = GithubOrg(id = GithubOrgId(1), name = GithubOrgName("kazakago"), imageUrl = URL("https://avatars.githubusercontent.com/u/7742104?v=4")),
            )
        }
    }
}
