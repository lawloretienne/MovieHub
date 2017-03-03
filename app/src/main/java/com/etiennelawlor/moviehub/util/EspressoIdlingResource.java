package com.etiennelawlor.moviehub.util;

import android.support.test.espresso.IdlingResource;

/**
 * Created by etiennelawlor on 2/23/17.
 */

public class EspressoIdlingResource {

    private static final String RESOURCE = "GLOBAL";

    private static SimpleCountingIdlingResource countingIdlingResource =
            new SimpleCountingIdlingResource(RESOURCE);

    public static void increment() {
        countingIdlingResource.increment();
    }

    public static void decrement() {
        countingIdlingResource.decrement();
    }

    public static IdlingResource getIdlingResource() {
        return countingIdlingResource;
    }
}
