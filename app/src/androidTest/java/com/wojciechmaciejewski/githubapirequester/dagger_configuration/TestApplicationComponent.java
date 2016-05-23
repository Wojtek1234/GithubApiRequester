package com.wojciechmaciejewski.githubapirequester.dagger_configuration;

import javax.inject.Singleton;

import dagger.Component;

/**
 *
 */
@Singleton
@Component(modules = TestApplicationModule.class)

public interface TestApplicationComponent extends ApplicationComponent {


}
