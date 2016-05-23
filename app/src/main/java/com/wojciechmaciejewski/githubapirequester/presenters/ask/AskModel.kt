package com.wojciechmaciejewski.githubapirequester.presenters.ask

import com.wojciechmaciejewski.githubapirequester.model.dto.AskElement
import com.wojciechmaciejewski.githubapirequester.model.dto.GithubRepoAskElement
import com.wojciechmaciejewski.githubapirequester.model.dto.GithubUserAskElement
import com.wojciechmaciejewski.githubapirequester.network.GithubApi
import rx.Observable

/**
 *
 */


class AskModel(val githubApi: GithubApi) : Ask.Model {

    override fun getAskResult(query: String, page: Int?): Observable<List<AskElement>> {
        return Observable.combineLatest(githubApi.askForRepos(query, page), githubApi.askForUsers(query, page),
                {
                    listRepo, listUser ->
                    listRepo.items.map { GithubRepoAskElement(it) } + listUser.items.map { GithubUserAskElement(it) }

                })
    }
}
