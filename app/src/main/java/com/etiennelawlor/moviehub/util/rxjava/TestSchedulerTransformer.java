package com.etiennelawlor.moviehub.util.rxjava;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by etiennelawlor on 2/24/17.
 */

public class TestSchedulerTransformer<T> implements SchedulerTransformer<T> {
    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.trampoline())
                .observeOn(Schedulers.trampoline());
    }
}
