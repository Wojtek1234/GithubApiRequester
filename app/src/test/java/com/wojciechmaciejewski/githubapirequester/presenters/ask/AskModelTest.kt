package com.wojciechmaciejewski.githubapirequester.presenters.ask

import com.wojciechmaciejewski.githubapirequester.UnitTest
import com.wojciechmaciejewski.githubapirequester.createListOfRepos
import com.wojciechmaciejewski.githubapirequester.createListOfUser
import com.wojciechmaciejewski.githubapirequester.model.network.GithubRepoResponse
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUserResponse
import com.wojciechmaciejewski.githubapirequester.network.GithubApi
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import rx.Observable

/**

 */
class AskModelTest : UnitTest() {


    @Mock lateinit var githubApi: GithubApi

    lateinit var model: Ask.Model
    override fun onSetup() {
        model = AskModel(githubApi)
    }

    @Test
    fun testGetAskResultCallForApi() {
        val query = "raz"
        val page = 2
        model.getAskResult(query, 2)
        Mockito.verify(githubApi).askForRepos(query, 2)
        Mockito.verify(githubApi).askForUsers(query, 2)
    }


    @Test
    fun testGetAskResultCombainObservables() {
        val query = "raz"
        val page = 2
        val repos = createListOfRepos(query, 2)
        val users = createListOfUser(query, 2)
        Mockito.`when`(githubApi.askForRepos(query, page)).thenReturn(Observable.just(GithubRepoResponse(repos, 100)))
        Mockito.`when`(githubApi.askForUsers(query, page)).thenReturn(Observable.just(GithubUserResponse(users, 100)))
        val idList = users.map { it.id } + repos.map { it.id }
        model.getAskResult(query, 2).subscribe {
            askElements ->
            idList.all { id -> askElements.any { it.id == id.toLong() } }
        }

    }
}