package com.kazakago.blueprint.presentation.view.hierarchy.github

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.presentation.view.global.theme.AppTheme
import com.kazakago.blueprint.presentation.viewmodel.hierarchy.github.GithubReposViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GithubReposActivity : ComponentActivity() {

    class Contract : ActivityResultContract<GithubOrgName, ActivityResult>() {
        override fun createIntent(context: Context, input: GithubOrgName) = Intent(context, GithubReposActivity::class.java).apply {
            putExtra(ParameterKey.GITHUB_ORG_ID.name, input)
        }

        override fun parseResult(resultCode: Int, intent: Intent?) = ActivityResult(resultCode, intent)
    }

    private enum class ParameterKey {
        GITHUB_ORG_ID,
    }

    @Inject
    lateinit var githubReposViewModelFactory: GithubReposViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val githubOrgName = intent.getSerializableExtra(ParameterKey.GITHUB_ORG_ID.name) as GithubOrgName
        val factory = GithubReposViewModel.provideFactory(githubReposViewModelFactory, githubOrgName)
        setContent {
            val viewModel: GithubReposViewModel = viewModel(factory = factory)
            val uiState by viewModel.uiState.collectAsState()
            val isRefreshing by viewModel.isRefreshing.collectAsState()
            AppTheme {
                GithubReposScreen(
                    uiState = uiState,
                    isRefreshing = isRefreshing,
                    onClickBack = {
                        finish()
                    },
                    onClickItem = { githubRepo ->
                        launch(githubRepo.url.toString())
                    },
                    onBottomReached = {
                        viewModel.requestAddition()
                    },
                    onRefresh = {
                        viewModel.refresh()
                    },
                    onRetry = {
                        viewModel.retry()
                    },
                    onRetryAdditional = {
                        viewModel.retryAddition()
                    },
                )
            }
        }
    }

    private fun launch(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
