package com.wojciechmaciejewski.githubapirequester.testutils

import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement
import com.wojciechmaciejewski.githubapirequester.model.dto.GithubRepoAskElement
import com.wojciechmaciejewski.githubapirequester.model.dto.GithubUserAskElement
import com.wojciechmaciejewski.githubapirequester.model.network.GithubRepo
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser

/**
 *
 */


fun createListOfUserIn(name: String, size: Int) = (0..size - 1).map { GithubUser(it, "$name $it", "http//:$name", "url") }

fun createListOfReposIn(name: String, size: Int) = (0..size - 1).map { GithubRepo(it + 100, "$name $it", "http//:$name", createListOfUserIn("$name $it", 1)[0]) }


fun createListOfAskElementsIn(name: String, size: Int): List<AskElement> {
    return createListOfReposIn(name, size).map { GithubRepoAskElement(it) } + createListOfUserIn(name, size).map { GithubUserAskElement(it) }
}

fun createListOfAskElementsInOfUser(name: String, size: Int): List<AskElement> {
    return createListOfUserIn(name, size).map {
        GithubUserAskElement(it)
    }
}


fun createListOfAskElementsInOfRepo(name: String, size: Int): List<AskElement> {
    return createListOfReposIn(name, size).map {
        GithubRepoAskElement(it)
    }
}