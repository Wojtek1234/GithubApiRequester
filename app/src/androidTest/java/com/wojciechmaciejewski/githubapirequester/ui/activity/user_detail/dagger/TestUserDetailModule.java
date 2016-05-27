package com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail.dagger;

import com.wojciechmaciejewski.githubapirequester.dagger_configuration.scopes.TestScope;
import com.wojciechmaciejewski.githubapirequester.presenters.user_details.UserDetail;
import com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail.UserDetailActivityTest;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module
public class TestUserDetailModule implements UserDetailModule {

    private UserDetail.View view;

    public TestUserDetailModule(UserDetail.View view) {
        this.view = view;
    }

    @NotNull
    @Override
    @Provides
    @TestScope
    public UserDetail.Presenter provideUserDetailPresenter() {
        return new UserDetailActivityTest.TestUserDetailPresenter(this.view);
    }
}
