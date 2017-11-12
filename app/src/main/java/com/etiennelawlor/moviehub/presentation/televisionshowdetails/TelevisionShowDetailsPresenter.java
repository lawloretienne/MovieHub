package com.etiennelawlor.moviehub.presentation.televisionshowdetails;

import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.repositories.tv.TelevisionShowDataSourceContract;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowDetailsWrapper;
import com.etiennelawlor.moviehub.util.EspressoIdlingResource;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class TelevisionShowDetailsPresenter implements TelevisionShowDetailsUiContract.Presenter {

    // region Member Variables
    private final TelevisionShowDetailsUiContract.View televisionShowDetailsView;
    private final TelevisionShowDataSourceContract.Repository televisionShowRepository;
    private final SchedulerTransformer<TelevisionShowDetailsWrapper> schedulerTransformer;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion

    // region Constructors

    public TelevisionShowDetailsPresenter(TelevisionShowDetailsUiContract.View televisionShowDetailsView, TelevisionShowDataSourceContract.Repository televisionShowRepository, SchedulerTransformer<TelevisionShowDetailsWrapper> schedulerTransformer) {
        this.televisionShowDetailsView = televisionShowDetailsView;
        this.televisionShowRepository = televisionShowRepository;
        this.schedulerTransformer = schedulerTransformer;
    }

    // endregion

    // region TelevisionShowDetailsUiContract.Presenter Methods

    @Override
    public void onDestroyView() {
        if(compositeSubscription != null && compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    @Override
    public void onLoadTelevisionShowDetails(int televisionShowId) {
        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment(); // App is busy until further notice

        Subscription subscription = televisionShowRepository.getTelevisionShowDetails(televisionShowId)
                .compose(schedulerTransformer)
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement(); // Set app as idle.
                        }
                    }
                })
                .subscribe(new Subscriber<TelevisionShowDetailsWrapper>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

                        if(NetworkUtility.isKnownException(throwable)){
//                            moviesView.showErrorFooter();
//                            moviesView.setErrorText("Can't load data.\nCheck your network connection.");
                            televisionShowDetailsView.showErrorView();
                        }
                    }

                    @Override
                    public void onNext(TelevisionShowDetailsWrapper televisionShowDetailsWrapper) {
                        if(televisionShowDetailsWrapper != null){
                            televisionShowDetailsView.showTelevisionShowDetails(televisionShowDetailsWrapper);
                        }
                    }
                });
        compositeSubscription.add(subscription);
    }

    @Override
    public void onPersonClick(Person person) {
        televisionShowDetailsView.openPersonDetails(person);
    }

    @Override
    public void onTelevisionShowClick(TelevisionShow televisionShow) {
        televisionShowDetailsView.openTelevisionShowDetails(televisionShow);
    }

    @Override
    public void onScrollChange(boolean isScrolledPastThreshold) {
        if(isScrolledPastThreshold)
            televisionShowDetailsView.showToolbarTitle();
        else
            televisionShowDetailsView.hideToolbarTitle();
    }

    // endregion
}
