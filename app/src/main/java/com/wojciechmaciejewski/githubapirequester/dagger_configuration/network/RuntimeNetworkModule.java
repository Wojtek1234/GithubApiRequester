package com.wojciechmaciejewski.githubapirequester.dagger_configuration.network;

import com.wojciechmaciejewski.githubapirequester.network.GithubApi;
import com.wojciechmaciejewski.githubapirequester.network.GithubApiProvider;

import org.jetbrains.annotations.NotNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module
public class RuntimeNetworkModule implements NetworkModule {
    @NotNull
    @Override
    @Provides
    @Singleton
    public GithubApi provideGithubApi() {
        return (new GithubApiProvider()).provideApi();
    }
}
