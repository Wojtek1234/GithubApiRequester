package com.wojciechmaciejewski.githubapirequester.presenters.ask

import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement
import com.wojciechmaciejewski.githubapirequester.model.dto.GithubRepoAskElement
import com.wojciechmaciejewski.githubapirequester.model.dto.GithubUserAskElement
import com.wojciechmaciejewski.githubapirequester.network.GithubApi
import rx.Observable
import javax.inject.Inject

/**
 *
 */


class AskModel : Ask.Model {
    private val githubApi: GithubApi
    private val numberOfItemsOnPage = 30;
    private var totalCount: TotalCountHolder? = null
    private val mapOfCondition: Map<CountCondition, (Int, String, GithubApi) -> Observable<List<AskElement>>>;

    @Inject
    constructor(githubApi: GithubApi) {
        this.githubApi = githubApi;
        this.mapOfCondition = mapOf(CountCondition(true, true) to returnBoth, CountCondition(true, false) to returnRepo, CountCondition(false, true) to returnUser, CountCondition(false, false) to returnError)
    }

    override fun getAskResult(query: String, page: Int): Observable<List<AskElement>> {
        if (totalCount == null || totalCount?.query != query) {
            return returnBoth(page, query, githubApi)
        }
        return mapOfCondition[CountCondition(isInRepoBounds(page), isInUserBounds(page))]!!(page, query, githubApi)
    }

    private fun isInRepoBounds(page: Int) = page * numberOfItemsOnPage - totalCount!!.repoCount < numberOfItemsOnPage
    private fun isInUserBounds(page: Int) = page * numberOfItemsOnPage - totalCount!!.userCount < numberOfItemsOnPage


    private val returnBoth: (page: Int, query: String, githubApi: GithubApi) -> Observable<List<AskElement>> = {
        page, query, githubApi ->
        Observable.combineLatest(githubApi.askForRepos(query, page), githubApi.askForUsers(query, page),
                {
                    listRepo, listUser ->
                    totalCount = TotalCountHolder(query, listRepo.totalCount, listUser.totalCount)
                    listRepo.items.map { GithubRepoAskElement(it) } + listUser.items.map { GithubUserAskElement(it) }

                })
    }
    private val returnRepo: (page: Int, query: String, githubApi: GithubApi) -> Observable<List<AskElement>> = {
        page, query, githubApi ->
        githubApi.askForRepos(query, page)
                .map {
                    listRepo ->
                    listRepo.items.map { GithubRepoAskElement(it) }

                }
    }
    private val returnUser: (page: Int, query: String, githubApi: GithubApi) -> Observable<List<AskElement>> = {
        page, query, githubApi ->
        githubApi.askForUsers(query, page)
                .map {
                    listUser ->
                    listUser.items.map { GithubUserAskElement(it) }

                }
    }

    private val returnError: (page: Int, query: String, githubApi: GithubApi) -> Observable<List<AskElement>> = {
        page, query, githubApi ->
        Observable.error<List<AskElement>>(NoMoreItemsException())
    }
}


data class TotalCountHolder(val query: String, val repoCount: Int, val userCount: Int)
data class CountCondition(val repoCond: Boolean, val userCond: Boolean)