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