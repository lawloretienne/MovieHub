package com.etiennelawlor.moviehub.ui.base;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 2/14/17.
 */

public class BasePresenter {

    // region Member Variables
    private CompositeSubscription compositeSubscription;
    // endregion

    public void setUpCompositeSubscription(){
        compositeSubscription = new CompositeSubscription();
    }

    public void tearDownCompositeSubscription(){
        compositeSubscription.unsubscribe();
    }

    public void addSubscription(Observable observable, Subscriber subscriber){
        Subscription subscription = observable
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
        compositeSubscription.add(subscription);
    }
}
