package com.etiennelawlor.moviehub.util.rxjava;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by etiennelawlor on 12/22/17.
 */

public class ProductionSchedulerProvider implements SchedulerProvider {
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @Override
    public Scheduler newThread() {
        return Schedulers.newThread();
    }
}
