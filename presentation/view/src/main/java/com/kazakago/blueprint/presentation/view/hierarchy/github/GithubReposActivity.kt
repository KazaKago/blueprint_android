package com.kazakago.blueprint.presentation.view.hierarchy.github

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubRepo
import com.kazakago.blueprint.presentation.view.databinding.ActivityGithubReposBinding
import com.kazakago.blueprint.presentation.view.global.flow.collectOnStarted
import com.kazakago.blueprint.presentation.view.global.view.ErrorItem
import com.kazakago.blueprint.presentation.view.global.view.LoadingItem
import com.kazakago.blueprint.presentation.view.global.view.addOnBottomReached
import com.kazakago.blueprint.presentation.viewmodel.hierarchy.github.GithubReposViewModel
import com.xwray.groupie.Group
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

@AndroidEntryPoint
class GithubReposActivity : AppCompatActivity() {

    class Contract : ActivityResultContract<GithubOrgName, ActivityResult>() {
        override fun createIntent(context: Context, input: GithubOrgName) = Intent(context, GithubReposActivity::class.java).apply {
            putExtra(ParameterKey.GITHUB_ORG_ID.name, input)
        }

        override fun parseResult(resultCode: Int, intent: Intent?) = ActivityResult(resultCode, intent)
    }

    private enum class ParameterKey {
        GITHUB_ORG_ID,
    }

    private val viewBinding by lazy { ActivityGithubReposBinding.inflate(layoutInflater) }
    private val githubReposAdapter = GroupieAdapter()
    private val githubReposViewModel: GithubReposViewModel by viewModels {
        val githubOrgName = intent.getSerializableExtra(ParameterKey.GITHUB_ORG_ID.name) as GithubOrgName
        GithubReposViewModel.provideFactory(githubReposViewModelFactory, githubOrgName)
    }

    @Inject
    lateinit var githubReposViewModelFactory: GithubReposViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        viewBinding.githubReposRecyclerView.adapter = githubReposAdapter
        viewBinding.githubReposRecyclerView.addOnBottomReached {
            githubReposViewModel.requestAdditional()
        }
        viewBinding.swipeRefreshLayout.setOnRefreshListener {
            githubReposViewModel.refresh()
        }
        viewBinding.retryButton.setOnClickListener {
            githubReposViewModel.retry()
        }

        githubReposViewModel.githubOrgName.collectOnStarted(this) {
            supportActionBar?.title = it.value
        }
        combine(githubReposViewModel.githubRepos, githubReposViewModel.isAdditionalLoading, githubReposViewModel.additionalError) { a, b, c -> Triple(a, b, c) }.collectOnStarted(this) {
            val items: List<Group> = mutableListOf<Group>().apply {
                this += createGithubRepoItems(it.first)
                if (it.second) this += createLoadingItem()
                if (it.third != null) this += createErrorItem(it.third!!)
            }
            githubReposAdapter.updateAsync(items)
        }
        githubReposViewModel.isMainLoading.collectOnStarted(this) {
            viewBinding.progressBar.isVisible = it
        }
        githubReposViewModel.mainError.collectOnStarted(this) {
            viewBinding.errorGroup.isVisible = (it != null)
            viewBinding.errorTextView.text = it?.toString()
        }
        githubReposViewModel.isRefreshing.collectOnStarted(this) {
            viewBinding.swipeRefreshLayout.isRefreshing = it
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createGithubRepoItems(githubRepos: List<GithubRepo>): List<GithubRepoItem> {
        return githubRepos.map { githubRepo ->
            GithubRepoItem(githubRepo).apply {
                onClick = { githubRepo -> launch(githubRepo.url.toString()) }
            }
        }
    }

    private fun createLoadingItem(): LoadingItem {
        return LoadingItem()
    }

    private fun createErrorItem(exception: Exception): ErrorItem {
        return ErrorItem(exception).apply {
            onRetry = { githubReposViewModel.retryAdditional() }
        }
    }

    private fun launch(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
