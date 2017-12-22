package com.etiennelawlor.moviehub.util.rxjava;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by etiennelawlor on 2/24/17.
 */

public class ProductionSchedulerTransformer2<T> implements SchedulerTransformer2<T> {
    @Override
    public SingleSource<T> apply(Single<T> upstream) {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
