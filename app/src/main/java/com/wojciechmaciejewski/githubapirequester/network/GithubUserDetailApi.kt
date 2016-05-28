package com.wojciechmaciejewski.githubapirequester.network

import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUserDetail
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

/**
 *
 */
interface GithubUserDetailApi {

    @GET("/users/{userName}")
    fun getUserDetails(@Path("userName") userName: String): Observable<GithubUserDetail>


    @GET("/users/{userName}/followers")
    fun getUserFollowers(@Path("userName") userName: String): Observable<List<GithubUser>>
}