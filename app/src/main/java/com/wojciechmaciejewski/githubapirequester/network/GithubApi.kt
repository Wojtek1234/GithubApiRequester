

package com.wojciechmaciejewski.githubapirequester.network

import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

/**
 *
 */


interface GithubApi{


    @GET("search/users")
    fun askForUsers(@Query("q") serchFor:String,@Query("page") pageNumber: Int?):Observable<Unit>

    @GET("search/repositories")
    fun askForRepos(@Query("q") serchFor:String,@Query("page") pageNumber: Int?):Observable<Unit>
}