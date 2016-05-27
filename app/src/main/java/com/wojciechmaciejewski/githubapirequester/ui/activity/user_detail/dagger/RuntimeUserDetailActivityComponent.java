package com.wojciechmaciejewski.githubapirequester.ui.activity.user_detail.dagger;

import com.wojciechmaciejewski.githubapirequester.dagger_configuration.ApplicationComponent;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.scopes.ActivityScope;

import dagger.Component;

/**
 *
 */

@ActivityScope
@Component(modules = RuntimeUserDetailActivityModule.class, dependencies = ApplicationComponent.class)
public interface RuntimeUserDetailActivityComponent extends UserDetailActivityComponent {
}
