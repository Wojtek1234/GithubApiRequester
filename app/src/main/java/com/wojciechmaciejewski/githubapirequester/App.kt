package com.wojciechmaciejewski.githubapirequester

import android.app.Application
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.*


import javax.inject.Inject

/**

 */
open class App : Application() {
    private lateinit var applicationComponent: ApplicationComponent

    @Inject
    lateinit var depedencyInjector: DepedencyInjector

    override fun onCreate() {
        super.onCreate()
        initializeInjection()
    }


    private fun initializeInjection() {
        applicationComponent = AppInjector.getApplicationComponent(this)
        applicationComponent.inject(this)
    }

    open fun provideDepedencyInjector(): DepedencyInjector = depedencyInjector;
    open fun provideApplicationComponent(): ApplicationComponent = applicationComponent
}
