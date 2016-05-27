package com.wojciechmaciejewski.githubapirequester.presenters.user_details

import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUserDetail
import rx.Observable

/**
 *
 */
interface UserDetail {
    interface Presenter {
        fun loadUserData(userName: String)
    }

    interface View {
        fun fillUpHeaderView()
        fun fillUpFollowers(followers: List<GithubUser>)
    }

    interface Model {
        fun loadDataForUser(userName: String): Observable<GithubUserDetail>
        fun loadUserFollowers(userName: String): Observable<List<GithubUser>>
    }
}