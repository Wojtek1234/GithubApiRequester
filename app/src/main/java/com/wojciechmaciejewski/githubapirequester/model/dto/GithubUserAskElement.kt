package com.wojciechmaciejewski.githubapirequester.model.dto

import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser

/**
 *
 */


class GithubUserAskElement(val githubUser: GithubUser): AskElementAbstract() {
    init{
        this.elementType = USER_TYPE
    }
    override val id: Long
        get() = githubUser.id.toLong()

    override fun returnTitle()=githubUser.login


    override fun returnHomepage()=githubUser.homepage



}