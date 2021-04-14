package com.kazakago.blueprint.presentation.view.hierarchy.github

import android.view.View
import com.kazakago.blueprint.domain.model.github.GithubRepo
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.databinding.ItemGithubRepoBinding
import com.xwray.groupie.viewbinding.BindableItem

data class GithubRepoItem(private val githubRepo: GithubRepo) : BindableItem<ItemGithubRepoBinding>(githubRepo.id.value) {

    var onClick: ((githubRepo: GithubRepo) -> Unit) = {}

    override fun getLayout(): Int {
        return R.layout.item_github_repo
    }

    override fun initializeViewBinding(view: View): ItemGithubRepoBinding {
        return ItemGithubRepoBinding.bind(view)
    }

    override fun bind(viewBinding: ItemGithubRepoBinding, position: Int) {
        viewBinding.idTextView.text = viewBinding.root.context.getString(R.string.id, githubRepo.id.value)
        viewBinding.titleTextView.text = githubRepo.name
        viewBinding.linkTextView.text = githubRepo.url.toString()
        viewBinding.root.setOnClickListener {
            onClick(githubRepo)
        }
    }
}
