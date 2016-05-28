package com.wojciechmaciejewski.githubapirequester.dagger_configuration.network;

import com.wojciechmaciejewski.githubapirequester.network.GithubApi;
import com.wojciechmaciejewski.githubapirequester.network.GithubApiProvider;
import com.wojciechmaciejewski.githubapirequester.network.GithubDetailApiProvider;
import com.wojciechmaciejewski.githubapirequester.network.GithubUserDetailApi;

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

    @NotNull
    @Override
    @Provides
    @Singleton
    public GithubUserDetailApi provideGithubDetailUserApi() {
        return (new GithubDetailApiProvider()).provideApi();
    }
}
