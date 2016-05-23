package com.wojciechmaciejewski.githubapirequester.dagger_configuration

import com.squareup.picasso.Picasso
import com.wojciechmaciejewski.githubapirequester.App
import com.wojciechmaciejewski.githubapirequester.network.GithubApi

/**
 *
 */


interface ApplicationComponent{
    fun inject(app: App)

    fun getGithubApi(): GithubApi
    fun getPicasso(): Picasso
}