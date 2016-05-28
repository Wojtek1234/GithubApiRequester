package com.wojciechmaciejewski.githubapirequester.presenters.user_details

import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUserDetail
import com.wojciechmaciejewski.githubapirequester.network.GithubUserDetailApi
import rx.Observable
import javax.inject.Inject

/**
 *
 */

class UserDetailModel : UserDetail.Model {
    override fun loadDataForUser(userName: String): Observable<GithubUserDetail> {
        throw UnsupportedOperationException()
    }

    override fun loadUserFollowers(userName: String): Observable<List<GithubUser>> {
        throw UnsupportedOperationException()
    }

    val gihubApi: GithubUserDetailApi

    @Inject
    constructor(githubUserDetailApi: GithubUserDetailApi) {
        this.gihubApi = githubUserDetailApi;
    }
}