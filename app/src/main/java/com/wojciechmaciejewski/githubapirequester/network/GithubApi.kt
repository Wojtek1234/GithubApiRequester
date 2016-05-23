

package com.wojciechmaciejewski.githubapirequester.network

import com.wojciechmaciejewski.githubapirequester.model.network.GithubRepoResponse
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUserResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 *
 */


interface GithubApi{


    @GET("search/users")
    fun askForUsers(@Query("q") serchFor: String, @Query("page") pageNumber: Int?): Observable<GithubUserResponse>

    @GET("search/repositories")
    fun askForRepos(@Query("q") serchFor: String, @Query("page") pageNumber: Int?): Observable<GithubRepoResponse>

}