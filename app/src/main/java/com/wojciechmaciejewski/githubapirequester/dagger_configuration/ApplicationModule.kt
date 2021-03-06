package com.wojciechmaciejewski.githubapirequester.dagger_configuration

import android.app.Application
import android.content.SharedPreferences
import com.squareup.picasso.Picasso
import com.wojciechmaciejewski.githubapirequester.utils.MySchedulers

/**
 *
 */
interface ApplicationModule {
    fun provideApplication(): Application
    fun provideSharedPreferences(): SharedPreferences
    fun provideDependenciesInjector(): DepedencyInjector
    fun providePicassoImageLoader(): Picasso
    fun provideSchedulers(): MySchedulers
}