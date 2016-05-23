package com.wojciechmaciejewski.githubapirequester;

import com.wojciechmaciejewski.githubapirequester.dagger_configuration.ApplicationComponent;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.DaggerTestApplicationComponent;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.DepedencyInjector;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.TestApplicationComponent;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.TestApplicationModule;

import javax.inject.Inject;

/**
 *
 */
public class TestingApplication extends App{

    @Inject
    DepedencyInjector depedencyInjector;

    @Override
    public void onCreate() {

        super.onCreate();

        initializeInjection();
    }


    private TestApplicationComponent testApplicationComponent;


    private void initializeInjection() {
        testApplicationComponent = DaggerTestApplicationComponent.builder().testApplicationModule(new TestApplicationModule(this)).build();
        testApplicationComponent.inject(this);
    }

    @Override
    public ApplicationComponent provideApplicationComponent() {
        return testApplicationComponent;
    }

    @Override
    public DepedencyInjector provideDepedencyInjector() {
        return depedencyInjector;
    }
//
//    public TestDepedencyInjector provideTestInjector(){
//        return testInjector;
//    }
}
