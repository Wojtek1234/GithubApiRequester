package com.wojciechmaciejewski.githubapirequester.network

/**
 *
 */
class GithubApiProvider:ApiProvider<GithubApi>{

    override fun getClassOfT()=GithubApi::class.java
}