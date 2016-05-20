package com.wojciechmaciejewski.githubapirequester

import android.app.Application
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.DaggerRuntimeApplicationComponent
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.RuntimeApplicationComponent
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.RuntimeApplicationModule

/**

 */
open class App : Application(){
    private lateinit  var applicationComponent: RuntimeApplicationComponent

    override fun onCreate() {
        super.onCreate()

    }


    private fun initializeInjection() {
        applicationComponent = DaggerRuntimeApplicationComponent.builder()
                .runtimeApplicationModule(RuntimeApplicationModule(this))
                .build();
        applicationComponent.inject(this)
    }
}
