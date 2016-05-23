package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.dagger;

import com.wojciechmaciejewski.githubapirequester.dagger_configuration.ApplicationComponent;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.scopes.ActivityScope;

import dagger.Component;

/**
 *
 */
@ActivityScope
@Component(modules = RuntimeAskActivityModule.class, dependencies = ApplicationComponent.class)
interface RuntimeAskActivityComponent extends AskActivityComponent {
}
