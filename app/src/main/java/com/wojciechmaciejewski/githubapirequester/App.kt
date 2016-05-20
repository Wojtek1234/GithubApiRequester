package com.wojciechmaciejewski.githubapirequester

import android.app.Application
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.DaggerRuntimeApplicationComponent
//import com.wojciechmaciejewski.githubapirequester.dagger_configuration.DaggerRuntimeApplicationComponent
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.DepedencyInjector
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.RuntimeApplicationComponent
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.RuntimeApplicationModule


import javax.inject.Inject

/**

 */
open class App : Application(){
    private lateinit  var applicationComponent: RuntimeApplicationComponent

    @Inject
    lateinit var depedencyInjector:DepedencyInjector

    override fun onCreate() {
        super.onCreate()
        initializeInjection()
    }


    private fun initializeInjection() {
            applicationComponent = DaggerRuntimeApplicationComponent.builder()
        .runtimeApplicationModule(RuntimeApplicationModule(this))
        .build()
        applicationComponent.inject(this)
    }

    fun provideDepedencyInjector() = depedencyInjector;
    fun provideApplicationComponent() = applicationComponent
}
