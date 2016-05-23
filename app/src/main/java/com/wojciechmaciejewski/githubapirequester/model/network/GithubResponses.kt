
package com.wojciechmaciejewski.githubapirequester.model.network

import com.google.gson.annotations.SerializedName

/**
 *
 */
data class GithubUser(val id:Int, val login:String,@SerializedName("url") val homepage:String)
data class GithubRepo(val id:Int, val name:String,@SerializedName("url") val homepage:String, val owner: GithubUser)
data class GithubUserResponse(val items: List<GithubUser>)
data class GithubRepoResponse(val items: List<GithubRepo>)