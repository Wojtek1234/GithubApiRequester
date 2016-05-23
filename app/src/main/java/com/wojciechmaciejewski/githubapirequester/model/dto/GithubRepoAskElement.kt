package com.wojciechmaciejewski.githubapirequester.model.dto

import com.wojciechmaciejewski.githubapirequester.model.network.GithubRepo

/**
 *
 */


class GithubRepoAskElement(val githubRepo: GithubRepo): AskElement() {

    init {
        this.elementType = REPO_TYPE
    }
    override val id=githubRepo.id.toLong()

    override fun returnTitle()=githubRepo.name

    override fun returnHomepage()=githubRepo.homepage
}