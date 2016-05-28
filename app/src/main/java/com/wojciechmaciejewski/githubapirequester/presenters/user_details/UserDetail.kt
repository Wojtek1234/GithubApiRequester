package com.wojciechmaciejewski.githubapirequester.presenters.user_details

import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUserDetail
import com.wojciechmaciejewski.githubapirequester.presenters.ClearSubscribtions
import com.wojciechmaciejewski.githubapirequester.presenters.HandleError
import rx.Observable

/**
 *
 */
interface UserDetail {
    interface Presenter : ClearSubscribtions {
        fun loadUserData(userName: String)
    }

    interface View : HandleError {
        fun fillUpHeaderView(userDetail: GithubUserDetail)
        fun fillUpFollowers(followers: List<GithubUser>)
    }

    interface Model {
        fun loadDataForUser(userName: String): Observable<GithubUserDetail>
        fun loadUserFollowers(userName: String): Observable<List<GithubUser>>
    }
}