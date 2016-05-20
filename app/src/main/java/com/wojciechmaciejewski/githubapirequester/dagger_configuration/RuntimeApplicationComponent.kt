package com.wojciechmaciejewski.githubapirequester.dagger_configuration

import com.wojciechmaciejewski.githubapirequester.App
import dagger.Component
import javax.inject.Singleton

/**
 *
 */

@Singleton
@Component(modules = arrayOf(RuntimeApplicationModule::class))
interface RuntimeApplicationComponent:ApplicationComponent{
    fun inject(app: App)
}