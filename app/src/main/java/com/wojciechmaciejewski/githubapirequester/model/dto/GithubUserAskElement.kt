package com.wojciechmaciejewski.githubapirequester.model.dto

import com.squareup.picasso.Picasso
import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.recyclerclasses.AskElementVH

/**
 *
 */


class GithubUserAskElement(val githubUser: GithubUser) : AskElement() {

    override fun handleViewHolder(baseViewHolder: AskElementVH, picasso: Picasso, click: (Int, String) -> Unit) {
        baseViewHolder.idTextView.text = "id: ${this.githubUser.id}"
        baseViewHolder.titleTextView.text = this.githubUser.login
        baseViewHolder.urlTextView.text = this.githubUser.homepage
        picasso.load(githubUser.imageUrl)
                .placeholder(R.drawable.user_icon)
                .into(baseViewHolder.imageView)
        baseViewHolder.itemView.setOnClickListener {
            click(USER_TYPE, githubUser.login)
        }
    }


    init {
        this.elementType = USER_TYPE
    }


    override val id = githubUser.id.toLong()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as GithubUserAskElement

        if (githubUser != other.githubUser) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = githubUser.hashCode()
        result = 31 * result + id.hashCode()
        return result
    }


}