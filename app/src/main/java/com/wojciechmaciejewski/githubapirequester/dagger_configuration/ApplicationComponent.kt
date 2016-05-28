package com.wojciechmaciejewski.githubapirequester.dagger_configuration

import com.squareup.picasso.Picasso
import com.wojciechmaciejewski.githubapirequester.App
import com.wojciechmaciejewski.githubapirequester.network.GithubApi
import com.wojciechmaciejewski.githubapirequester.network.GithubUserDetailApi
import com.wojciechmaciejewski.githubapirequester.utils.MySchedulers

/**
 *
 */


interface ApplicationComponent{
    fun inject(app: App)

    fun getGithubApi(): GithubApi
    fun getGithubDetailUserApi(): GithubUserDetailApi
    fun getMySchedulers(): MySchedulers
    fun getPicasso(): Picasso
}