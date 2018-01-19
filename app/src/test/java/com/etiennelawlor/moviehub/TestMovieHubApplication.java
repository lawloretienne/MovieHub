package com.etiennelawlor.moviehub;

import com.etiennelawlor.moviehub.di.component.ApplicationComponent;
import com.etiennelawlor.moviehub.di.component.DaggerApplicationComponent;
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

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        currentApplication = this;

        applicationComponent = createApplicationComponent();
    }

    public ApplicationComponent createApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .networkModule(new TestNetworkModule())
                .build();
    }

//    @Override
//    public ApplicationComponent getApplicationComponent() {
//        return testApplicationComponent;
//    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void setTestComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    public static TestMovieHubApplication getInstance() {
        return currentApplication;
    }

}
