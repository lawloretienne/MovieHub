//package com.etiennelawlor.moviehub;
//
//import com.etiennelawlor.moviehub.di.component.ApplicationComponent;
//import com.etiennelawlor.moviehub.di.module.AndroidModule;
//import com.etiennelawlor.moviehub.di.module.ApplicationModule;
//import com.etiennelawlor.moviehub.di.module.RxModule;
//
//import javax.inject.Singleton;
//
//import dagger.Component;
//
///**
// * Created by etiennelawlor on 1/15/18.
// */
//
//public class TestMovieHubApplication extends MovieHubApplication {
//
//    // region Static Variables
//    private static TestMovieHubApplication currentApplication = null;
//    // endregion
//
//    private TestApplicationComponent testApplicationComponent;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        currentApplication = this;
//
//        testApplicationComponent = createApplicationComponent();
//    }
//
//    public TestApplicationComponent createApplicationComponent() {
//        return DaggerTestApplicationComponent.builder()
//                .androidModule(new AndroidModule(this))
//                .networkModule(new TestNetworkModule())
//                .build();
//    }
//
////    @Override
////    public ApplicationComponent getApplicationComponent() {
////        return testApplicationComponent;
////    }
//
//    public TestApplicationComponent getApplicationComponent() {
//        return testApplicationComponent;
//    }
//
//    public static TestMovieHubApplication getInstance() {
//        return currentApplication;
//    }
//
//}
