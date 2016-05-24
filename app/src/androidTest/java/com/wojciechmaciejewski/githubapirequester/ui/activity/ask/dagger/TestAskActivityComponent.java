package com.wojciechmaciejewski.githubapirequester.ui.activity.ask.dagger;

import com.wojciechmaciejewski.githubapirequester.dagger_configuration.ApplicationComponent;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.scopes.TestScope;

import dagger.Component;

/**
 *
 */

@TestScope
@Component(modules = TestAskActivityModule.class, dependencies = ApplicationComponent.class)
public interface TestAskActivityComponent extends AskActivityComponent {
}
