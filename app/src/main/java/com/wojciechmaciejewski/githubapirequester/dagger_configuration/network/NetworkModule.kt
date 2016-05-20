package com.wojciechmaciejewski.githubapirequester.dagger_configuration.network

import com.wojciechmaciejewski.githubapirequester.network.GithubApi

/**
 *
 */
interface NetworkModule{
    fun provideGithubApi():GithubApi
}