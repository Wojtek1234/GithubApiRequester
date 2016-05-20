package com.wojciechmaciejewski.githubapirequester.dagger_configuration

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.squareup.picasso.Picasso
import com.wojciechmaciejewski.githubapirequester.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 *
 */


@Module
class RuntimeApplicationModule(val application:Application):ApplicationModule{
    companion object{
        private val APP_PREFS = "GITHUB_REQUESTER_PREFS"
    }
    @Provides
    @Singleton
    override fun provideApplication(): Application {
        return application;
    }
    @Provides
    @Singleton
    override fun provideSharedPreferences(): SharedPreferences {
        return application.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE)
    }
    @Provides
    @Singleton
    override fun provideDependenciesInjector(): DepedencyInjector {
        return RuntimeDepedencyInjector()
    }
    @Provides
    @Singleton
    override fun providePicassoImageLoader(): Picasso {
        val picasso = Picasso.with(application)
        if (BuildConfig.DEBUG) picasso.isLoggingEnabled = true
        return picasso
    }
}