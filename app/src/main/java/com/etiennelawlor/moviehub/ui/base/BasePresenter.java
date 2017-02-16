package com.etiennelawlor.moviehub.ui.base;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by etiennelawlor on 2/14/17.
 */

public interface BasePresenter {

    void setUpCompositeSubscription();
    void tearDownCompositeSubscription();
    void addSubscription(Observable observable, Subscriber subscriber);
}
