package com.wojciechmaciejewski.githubapirequester.model.dto

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.View
import com.squareup.picasso.Picasso
import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.model.network.GithubRepo
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses.AskElementVH


/**
 *
 */


class GithubRepoAskElement(val githubRepo: GithubRepo): AskElement() {
    override fun handleViewHolder(baseViewHolder: AskElementVH, picasso: Picasso, click: (String, String?, android.util.Pair<View, String>) -> Unit) {
        picasso.load(R.drawable.repo_icon).into(baseViewHolder.imageView)
        baseViewHolder.idTextView.text = "id: ${githubRepo.id}"
        baseViewHolder.titleTextView.text = githubRepo.name
        baseViewHolder.urlTextView.text = githubRepo.homepage
        baseViewHolder.itemView.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(githubRepo.homepage));
            (baseViewHolder.itemView.context as Activity).startActivity(browserIntent);
        }
    }


    init {
        this.elementType = REPO_TYPE
    }
    override val id=githubRepo.id.toLong()




    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as GithubRepoAskElement

        if (githubRepo != other.githubRepo) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = githubRepo.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }


}