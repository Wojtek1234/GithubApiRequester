

package com.wojciechmaciejewski.githubapirequester.network

import com.wojciechmaciejewski.githubapirequester.model.network.GithubRepo
import com.wojciechmaciejewski.githubapirequester.model.network.GithubUser
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 *
 */


interface GithubApi{


    @GET("search/users")
    fun askForUsers(@Query("q") serchFor:String,@Query("page") pageNumber: Int?):Observable<GithubUser>

    @GET("search/repositories")
    fun askForRepos(@Query("q") serchFor:String,@Query("page") pageNumber: Int?):Observable<GithubRepo>

}