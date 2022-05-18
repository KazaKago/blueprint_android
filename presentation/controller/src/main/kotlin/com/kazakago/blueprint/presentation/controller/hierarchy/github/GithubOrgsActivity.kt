package com.kazakago.blueprint.presentation.controller.hierarchy.github

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.presentation.ui.global.theme.AppTheme
import com.kazakago.blueprint.presentation.controller.hierarchy.about.AboutActivity
import com.kazakago.blueprint.presentation.ui.hierarchy.github.GithubOrgsScreen
import com.kazakago.blueprint.presentation.viewmodel.hierarchy.github.GithubOrgsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubOrgsActivity : ComponentActivity() {

    class Contract : ActivityResultContract<Unit, ActivityResult>() {
        override fun createIntent(context: Context, input: Unit) = Intent(context, GithubOrgsActivity::class.java)
        override fun parseResult(resultCode: Int, intent: Intent?) = ActivityResult(resultCode, intent)
    }

    private val githubReposActivityLauncher = registerForActivityResult(GithubReposActivity.Contract()) {}
    private val aboutActivityLauncher = registerForActivityResult(AboutActivity.Contract()) {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: GithubOrgsViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsState()
            val isRefreshing by viewModel.isRefreshing.collectAsState()
            AppTheme {
                GithubOrgsScreen(
                    uiState = uiState,
                    isRefreshing = isRefreshing,
                    onClickItem = { goGithubRepos(it.name) },
                    onClickAbout = ::goAbout,
                    onBottomReached = viewModel::requestAddition,
                    onRefresh = viewModel::refresh,
                    onRetry = viewModel::retry,
                    onRetryAdditional = viewModel::retryAddition,
                )
            }
        }
    }

    private fun goGithubRepos(githubOrgName: GithubOrgName) {
        githubReposActivityLauncher.launch(githubOrgName)
    }

    private fun goAbout() {
        aboutActivityLauncher.launch(Unit)
    }
}
