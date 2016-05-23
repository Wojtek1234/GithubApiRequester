package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.dagger;

import com.wojciechmaciejewski.githubapirequester.dagger_configuration.scopes.ActivityScope;
import com.wojciechmaciejewski.githubapirequester.presenters.ask.Ask;
import com.wojciechmaciejewski.githubapirequester.presenters.ask.AskModel;
import com.wojciechmaciejewski.githubapirequester.presenters.ask.AskPresenter;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;
import pl.stsg.e_learning.helpers.rxSchedulers.MySchedulers;

/**
 *
 */
@Module
public class RuntimeAskActivityModule implements AskActivityModule {
    private Ask.View askView;

    public RuntimeAskActivityModule(Ask.View askView) {
        this.askView = askView;
    }

    @NotNull
    @Override
    @Provides
    @ActivityScope
    public Ask.Presenter provideAskPresenter(AskModel askModel, MySchedulers mySchedulers) {
        return new AskPresenter(askModel, askView, mySchedulers);
    }


}
