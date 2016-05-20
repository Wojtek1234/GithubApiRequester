package com.wojciechmaciejewski.githubapirequester.dagger_configuration;



import javax.inject.Singleton;

import dagger.Component;

/**
 *
 */
@Singleton
@Component(modules = RuntimeApplicationModule.class)
public interface RuntimeApplicationComponent extends ApplicationComponent {
}
