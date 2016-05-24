package com.wojciechmaciejewski.githubapirequester.model.dto

import com.wojciechmaciejewski.githubapirequester.R
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser

/**
 *
 */


class GithubUserAskElement(val githubUser: GithubUser): AskElement() {
    init{
        this.elementType = USER_TYPE
    }

    override fun returnImageId() = R.drawable.user_icon

    override val id = githubUser.id.toLong()

    override fun returnTitle()=githubUser.login


    override fun returnHomepage()=githubUser.homepage


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