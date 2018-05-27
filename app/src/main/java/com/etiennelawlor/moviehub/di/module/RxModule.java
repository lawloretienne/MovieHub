package com.etiennelawlor.moviehub.di.module;

import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerProvider;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by etiennelawlor on 1/7/18.
 */

@Module
public class RxModule {

    @Provides
    public SchedulerProvider provideSchedulerProvider() {
        return new ProductionSchedulerProvider();
    }
}
