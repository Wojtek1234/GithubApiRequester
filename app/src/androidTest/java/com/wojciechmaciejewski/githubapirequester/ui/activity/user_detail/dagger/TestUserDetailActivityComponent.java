package com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail.dagger;

import com.wojciechmaciejewski.githubapirequester.dagger_configuration.ApplicationComponent;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.scopes.TestScope;

import dagger.Component;

/**
 *
 */
@TestScope
@Component(modules = TestUserDetailModule.class, dependencies = ApplicationComponent.class)
public interface TestUserDetailActivityComponent extends UserDetailActivityComponent {
}
