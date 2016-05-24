package com.wojciechmaciejewski.githubapirequester.dagger_configuration;

import android.app.Activity;

import com.wojciechmaciejewski.githubapirequester.TestingApplication;
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.AskActivity;
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.dagger.AskActivityComponent;
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.dagger.DaggerTestAskActivityComponent;
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.dagger.TestAskActivityModule;

import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class TestDepedencyInjector implements DepedencyInjector {

    private ApplicationComponent getApplicationComponent(Activity activity) {
        return ((TestingApplication) activity.getApplication()).provideApplicationComponent();
    }
    @Override
    public void inject(@NotNull AskActivity askActivity) {
        AskActivityComponent askActivityComponent = DaggerTestAskActivityComponent.builder().applicationComponent(getApplicationComponent(askActivity))
                .testAskActivityModule(new TestAskActivityModule(askActivity))
                .build();
        askActivityComponent.inject(askActivity);
    }
}
