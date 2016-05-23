package com.wojciechmaciejewski.githubapirequester.dagger_configuration;

import android.content.Context;
import android.support.annotation.NonNull;

import com.squareup.picasso.Picasso;
import com.wojciechmaciejewski.githubapirequester.network.GithubApi;
import com.wojciechmaciejewski.githubapirequester.utils.MySchedulers;

import org.jetbrains.annotations.NotNull;
import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 */
@Module
public class TestApplicationModule {
    private final Context context;

    public TestApplicationModule(Context context) {
        this.context = context;
    }

    @NotNull
    @Provides
    @Singleton
    public DepedencyInjector provideDependenciesInjector() {
        return new TestDepedencyInjector();
    }

    @NotNull
    @Provides
    @Singleton
    public GithubApi providGithubApi() {
        return Mockito.mock(GithubApi.class);
    }

    @NonNull
    @Provides
    @Singleton
    public Picasso providePicassoImageLoader() {
        return Picasso.with(context);
    }

    @NotNull
    @Provides
    @Singleton
    public MySchedulers provideSchedulers() {
        return new MySchedulers(Schedulers.newThread(), AndroidSchedulers.mainThread());
    }
}
