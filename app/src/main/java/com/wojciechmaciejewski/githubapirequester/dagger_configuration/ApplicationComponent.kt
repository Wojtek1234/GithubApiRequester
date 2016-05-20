package com.wojciechmaciejewski.githubapirequester.dagger_configuration

import com.wojciechmaciejewski.githubapirequester.App

/**
 *
 */


interface ApplicationComponent{
    fun inject(app: App)
}