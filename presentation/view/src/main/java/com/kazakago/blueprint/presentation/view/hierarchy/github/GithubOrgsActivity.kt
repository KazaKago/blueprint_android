package com.kazakago.blueprint.presentation.view.hierarchy.github

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrg
import com.kazakago.blueprint.domain.model.hierarchy.github.GithubOrgName
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.databinding.ActivityGithubOrgsBinding
import com.kazakago.blueprint.presentation.view.global.flow.collectOnStarted
import com.kazakago.blueprint.presentation.view.global.view.ErrorItem
import com.kazakago.blueprint.presentation.view.global.view.LoadingItem
import com.kazakago.blueprint.presentation.view.global.view.addOnBottomReached
import com.kazakago.blueprint.presentation.view.hierarchy.about.AboutActivity
import com.kazakago.blueprint.presentation.viewmodel.hierarchy.github.GithubOrgsViewModel
import com.xwray.groupie.Group
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.combine

@AndroidEntryPoint
class GithubOrgsActivity : AppCompatActivity() {

    class Contract : ActivityResultContract<Unit, ActivityResult>() {
        override fun createIntent(context: Context, input: Unit) = Intent(context, GithubOrgsActivity::class.java)
        override fun parseResult(resultCode: Int, intent: Intent?) = ActivityResult(resultCode, intent)
    }

    private val viewBinding by lazy { ActivityGithubOrgsBinding.inflate(layoutInflater) }
    private val githubOrgsAdapter = GroupieAdapter()
    private val githubReposActivityLauncher = registerForActivityResult(GithubReposActivity.Contract()) {}
    private val aboutActivityLauncher = registerForActivityResult(AboutActivity.Contract()) {}
    private val githubOrgsViewModel: GithubOrgsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        setSupportActionBar(viewBinding.toolbar)

        viewBinding.githubOrgsRecyclerView.adapter = githubOrgsAdapter
        viewBinding.githubOrgsRecyclerView.addOnBottomReached {
            githubOrgsViewModel.requestAddition()
        }
        viewBinding.swipeRefreshLayout.setOnRefreshListener {
            githubOrgsViewModel.refresh()
        }
        viewBinding.retryButton.setOnClickListener {
            githubOrgsViewModel.retry()
        }

        combine(githubOrgsViewModel.githubOrgs, githubOrgsViewModel.isAdditionalLoading, githubOrgsViewModel.additionalError) { a, b, c -> Triple(a, b, c) }.collectOnStarted(this) {
            val items: List<Group> = mutableListOf<Group>().apply {
                this += createGithubOrgItems(it.first)
                if (it.second) this += createLoadingItem()
                if (it.third != null) this += createErrorItem(it.third!!)
            }
            githubOrgsAdapter.updateAsync(items)
        }
        githubOrgsViewModel.isMainLoading.collectOnStarted(this) {
            viewBinding.progressBar.isVisible = it
        }
        githubOrgsViewModel.mainError.collectOnStarted(this) {
            viewBinding.errorGroup.isVisible = (it != null)
            viewBinding.errorTextView.text = it?.toString()
        }
        githubOrgsViewModel.isRefreshing.collectOnStarted(this) {
            viewBinding.swipeRefreshLayout.isRefreshing = it
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_about -> {
                goAbout()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createGithubOrgItems(githubOrgs: List<GithubOrg>): List<GithubOrgItem> {
        return githubOrgs.map { githubOrg ->
            GithubOrgItem(githubOrg).apply {
                onClick = { goGithubRepos(it.name) }
            }
        }
    }

    private fun createLoadingItem(): LoadingItem {
        return LoadingItem()
    }

    private fun createErrorItem(exception: Exception): ErrorItem {
        return ErrorItem(exception).apply {
            onRetry = { githubOrgsViewModel.retryAddition() }
        }
    }

    private fun goGithubRepos(githubOrgName: GithubOrgName) {
        githubReposActivityLauncher.launch(githubOrgName)
    }

    private fun goAbout() {
        aboutActivityLauncher.launch(Unit)
    }
}
