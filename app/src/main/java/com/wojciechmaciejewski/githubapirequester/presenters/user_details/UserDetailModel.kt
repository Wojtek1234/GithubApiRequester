package com.wojciechmaciejewski.githubapirequester.presenters.user_details

import com.wojciechmaciejewski.githubapirequester.network.GithubUserDetailApi
import javax.inject.Inject

/**
 *
 */

class UserDetailModel : UserDetail.Model {
    val gihubApi: GithubUserDetailApi

    @Inject
    constructor(githubUserDetailApi: GithubUserDetailApi) {
        this.gihubApi = githubUserDetailApi;
    }

    override fun loadDataForUser(userName: String) = gihubApi.getUserDetails(userName)

    override fun loadUserFollowers(userName: String) = gihubApi.getUserFollowers(userName)
}