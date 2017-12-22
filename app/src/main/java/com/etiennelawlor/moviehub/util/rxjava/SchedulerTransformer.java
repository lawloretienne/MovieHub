package com.etiennelawlor.moviehub.util.rxjava;

import io.reactivex.SingleTransformer;

/**
 * Created by etiennelawlor on 2/24/17.
 */

public interface SchedulerTransformer<T> extends SingleTransformer<T, T> {
}
