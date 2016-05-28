package com.wojciechmaciejewski.githubapirequester.dagger_configuration.network

import com.wojciechmaciejewski.githubapirequester.network.GithubApi
import com.wojciechmaciejewski.githubapirequester.network.GithubUserDetailApi

/**
 *
 */
interface NetworkModule{
    fun provideGithubApi():GithubApi
    fun provideGithubDetailUserApi(): GithubUserDetailApi
}