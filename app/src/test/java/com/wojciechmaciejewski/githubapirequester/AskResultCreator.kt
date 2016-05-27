package com.wojciechmaciejewski.githubapirequester

import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement
import com.wojciechmaciejewski.githubapirequester.model.dto.GithubRepoAskElement
import com.wojciechmaciejewski.githubapirequester.model.dto.GithubUserAskElement
import com.wojciechmaciejewski.githubapirequester.model.network.GithubRepo
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser

/**
 *
 */


fun createListOfUser(name: String, size: Int) = (0..size - 1).map { GithubUser(it, "$name $it", "http//:$name", "url") }

fun createListOfRepos(name: String, size: Int) = (0..size - 1).map { GithubRepo(it + 100, "$name $it", "http//:$name", createListOfUser("$name $it", 1)[0]) }


fun createListOfAskElements(name: String, size: Int): List<AskElement> {
    return createListOfRepos(name, size).map { GithubRepoAskElement(it) } + createListOfUser(name, size).map { GithubUserAskElement(it) }
}
