package com.kazakago.blueprint.presentation.view.hierarchy.github

import android.view.View
import com.kazakago.blueprint.domain.model.github.GithubOrg
import com.kazakago.blueprint.presentation.view.R
import com.kazakago.blueprint.presentation.view.databinding.ItemGithubOrgBinding
import com.xwray.groupie.viewbinding.BindableItem

data class GithubOrgItem(private val githubOrg: GithubOrg) : BindableItem<ItemGithubOrgBinding>(githubOrg.id.value) {

    var onClick: ((githubOrg: GithubOrg) -> Unit) = {}

    override fun getLayout(): Int {
        return R.layout.item_github_org
    }

    override fun initializeViewBinding(view: View): ItemGithubOrgBinding {
        return ItemGithubOrgBinding.bind(view)
    }

    override fun bind(viewBinding: ItemGithubOrgBinding, position: Int) {
        viewBinding.idTextView.text = viewBinding.root.context.getString(R.string.id, githubOrg.id.value)
        viewBinding.titleTextView.text = githubOrg.name.value
        viewBinding.root.setOnClickListener {
            onClick(githubOrg)
        }
    }
}
