package com.wojciechmaciejewski.githubapirequester;

import android.support.annotation.NonNull;

import com.wojciechmaciejewski.githubapirequester.dagger_configuration.ApplicationComponent;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.DaggerTestApplicationComponent;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.DepedencyInjector;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.TestApplicationComponent;
import com.wojciechmaciejewski.githubapirequester.dagger_configuration.TestApplicationModule;

/**
 *
 */
public class TestingApplication extends App{


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

    @NonNull
    @Override
    public ApplicationComponent provideApplicationComponent() {
        return testApplicationComponent;
    }

    @NonNull
    @Override
    public DepedencyInjector provideDepedencyInjector() {
        return depedencyInjector;
    }
//
//    public TestDepedencyInjector provideTestInjector(){
//        return testInjector;
//    }
}
