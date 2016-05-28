package com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail.dagger;

import com.wojciechmaciejewski.githubapirequester.dagger_configuration.scopes.ActivityScope;
import com.wojciechmaciejewski.githubapirequester.presenters.user_details.UserDetail;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

/**
 *
 */

@Module
public class RuntimeUserDetailActivityModule implements UserDetailModule {
    private UserDetail.View userDetailView;

    public RuntimeUserDetailActivityModule(UserDetail.View userDetailView) {
        this.userDetailView = userDetailView;
    }

    @NotNull
    @Override
    @Provides
    @ActivityScope
    public UserDetail.Presenter provideUserDetailPresenter() {
        return new UserDetail.Presenter() {

            @Override
            public void clearSubscriptions() {

            }

            @Override
            public void loadUserData(@NotNull String userName) {

            }
        };
    }
}
