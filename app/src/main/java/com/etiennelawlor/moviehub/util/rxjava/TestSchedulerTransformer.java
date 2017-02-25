package com.etiennelawlor.moviehub.util.rxjava;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by etiennelawlor on 2/24/17.
 */

public class TestSchedulerTransformer<T> implements SchedulerTransformer<T> {
    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.immediate())
                .observeOn(Schedulers.immediate());
    }
}
