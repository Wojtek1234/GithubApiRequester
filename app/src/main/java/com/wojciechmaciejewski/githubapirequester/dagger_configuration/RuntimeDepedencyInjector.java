package com.wojciechmaciejewski.githubapirequester.dagger_configuration;

import android.app.Activity;

import com.wojciechmaciejewski.githubapirequester.App;
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.AskActivity;
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.dagger.AskActivityComponent;
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.dagger.DaggerRuntimeAskActivityComponent;
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.dagger.RuntimeAskActivityModule;
import com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail.UserDetailActivity;
import com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail.dagger.DaggerRuntimeUserDetailActivityComponent;
import com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail.dagger.RuntimeUserDetailActivityModule;
import com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail.dagger.UserDetailActivityComponent;

import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class RuntimeDepedencyInjector implements DepedencyInjector {
    private ApplicationComponent getApplicationComponent(Activity activity) {
        return ((App) activity.getApplication()).provideApplicationComponent();
    }
    @Override
    public void inject(@NotNull AskActivity askActivity) {
        AskActivityComponent askActivityComponent = DaggerRuntimeAskActivityComponent.builder()
                .applicationComponent(getApplicationComponent(askActivity))
                .runtimeAskActivityModule(new RuntimeAskActivityModule(askActivity))
                .build();
        askActivityComponent.inject(askActivity);
    }

    @Override
    public void inject(@NotNull UserDetailActivity userDetailActivity) {
        UserDetailActivityComponent userDetailActivityComponent = DaggerRuntimeUserDetailActivityComponent.builder()
                .applicationComponent(getApplicationComponent(userDetailActivity))
                .runtimeUserDetailActivityModule(new RuntimeUserDetailActivityModule(userDetailActivity))
                .build();
        userDetailActivityComponent.inject(userDetailActivity);
    }
}
