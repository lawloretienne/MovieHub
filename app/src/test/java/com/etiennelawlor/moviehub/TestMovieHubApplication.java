package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.di.component.TestApplicationComponent;
import com.etiennelawlor.moviehub.di.module.TestNetworkModule;
import com.etiennelawlor.moviehub.di.module.AndroidModule;

/**
 * Created by etiennelawlor on 1/15/18.
 */

public class TestMovieHubApplication extends MovieHubApplication {

    // region Static Variables
    private static TestMovieHubApplication currentApplication = null;
    // endregion

    private TestApplicationComponent testApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        currentApplication = this;

        testApplicationComponent = createApplicationComponent();
    }

    public TestApplicationComponent createApplicationComponent() {
        return DaggerTestApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .networkModule(new TestNetworkModule())
                .build();
    }

//    @Override
//    public ApplicationComponent getApplicationComponent() {
//        return testApplicationComponent;
//    }

    public TestApplicationComponent getApplicationComponent() {
        return testApplicationComponent;
    }

    public static TestMovieHubApplication getInstance() {
        return currentApplication;
    }

}
