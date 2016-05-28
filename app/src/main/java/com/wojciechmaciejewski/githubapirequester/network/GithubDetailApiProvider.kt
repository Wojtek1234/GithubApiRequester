package com.wojciechmaciejewski.githubapirequester.network

/**
 *
 */
class GithubDetailApiProvider : ApiProvider<GithubUserDetailApi> {
    override fun getClassOfT() = GithubUserDetailApi::class.java
}