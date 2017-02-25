package com.etiennelawlor.moviehub.util.rxjava;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/24/17.
 */

public interface SchedulerTransformer<T> extends Observable.Transformer<T, T> {
}
