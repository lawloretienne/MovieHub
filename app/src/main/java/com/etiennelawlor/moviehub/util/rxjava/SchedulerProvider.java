package com.etiennelawlor.moviehub.util.rxjava;

import io.reactivex.Scheduler;

/**
 * Created by etiennelawlor on 12/22/17.
 */

public interface SchedulerProvider {
    Scheduler io();
    Scheduler ui();
    Scheduler computation();
    Scheduler newThread();
}
