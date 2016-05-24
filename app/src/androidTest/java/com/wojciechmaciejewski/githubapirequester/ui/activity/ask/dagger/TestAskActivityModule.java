package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.dagger;

import com.wojciechmaciejewski.githubapirequester.dagger_configuration.scopes.TestScope;
import com.wojciechmaciejewski.githubapirequester.presenters.ask.Ask;
import com.wojciechmaciejewski.githubapirequester.presenters.ask.AskModel;
import com.wojciechmaciejewski.githubapirequester.ui.activity.ask.AskActivityTest;
import com.wojciechmaciejewski.githubapirequester.utils.MySchedulers;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module
public class TestAskActivityModule {

    private Ask.View view;

    public TestAskActivityModule(Ask.View view) {
        this.view = view;
    }

    @NotNull
    @Provides
    @TestScope
    public Ask.Presenter provideAskPresenter(@NotNull AskModel model, @NotNull MySchedulers mySchedulers) {
        return new AskActivityTest.TestAskPresenter(view);
    }
}
