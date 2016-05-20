package com.wojciechmaciejewski.githubapirequester.dagger_configuration;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.squareup.picasso.Picasso;
import com.wojciechmaciejewski.githubapirequester.App;
import com.wojciechmaciejewski.githubapirequester.BuildConfig;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.ApplicationModule;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.DepedencyInjector;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.RuntimeDepedencyInjector;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module
public class RuntimeApplicationModule implements ApplicationModule {

    private App app;

    public RuntimeApplicationModule(App app) {
        this.app = app;
    }

    @NotNull
    @Override
    @Provides
    @Singleton
    public Application provideApplication() {
        return app;
    }
    private static String APP_PREFS = "GITHUB_REQUESTER_PREFS";
    @NotNull
    @Override
    @Provides
    @Singleton
    public SharedPreferences provideSharedPreferences() {
        return app.getSharedPreferences(APP_PREFS, Context.MODE_PRIVATE);    }


    @NotNull
    @Override
    @Provides
    @Singleton
    public DepedencyInjector provideDependenciesInjector() {
        return new RuntimeDepedencyInjector();
    }

    @NotNull
    @Override
    @Provides
    @Singleton
    public Picasso providePicassoImageLoader() {
        Picasso picasso=Picasso.with(app);
        if(BuildConfig.DEBUG) picasso.setLoggingEnabled(true);
        return picasso;
    }
}
