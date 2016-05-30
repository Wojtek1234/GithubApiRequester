package com.wojciechmaciejewski.githubapirequester.dagger_configuration;

import com.wojciechmaciejewski.githubapirequester.App;

/**
 *
 */
public class AppInjector {

    public static ApplicationComponent getApplicationComponent(App app){
        return DaggerRuntimeApplicationComponent.builder()
                .runtimeApplicationModule(new RuntimeApplicationModule(app))
                .build();
    }
}
