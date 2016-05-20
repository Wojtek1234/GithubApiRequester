package com.wojciechmaciejewski.githubapirequester.dagger_configuration

import dagger.Component
import javax.inject.Singleton

/**
 *
 */
@Singleton
@Component(modules = arrayOf(RuntimeApplicationModule::class))
interface RuntimeApplicationComponent: ApplicationComponent{

}