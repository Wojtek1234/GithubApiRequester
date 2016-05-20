package com.wojciechmaciejewski.githubapirequester.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.wojciechmaciejewski.githubapirequester.App
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.DepedencyInjector



abstract class AbstractActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInitializeInjection()

    }


    protected val dependenciesInjector: DepedencyInjector
        get() = (application as App).provideDepedencyInjector()



    internal abstract fun onInitializeInjection()


}
