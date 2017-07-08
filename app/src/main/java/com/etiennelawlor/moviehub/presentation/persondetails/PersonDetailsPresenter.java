package com.etiennelawlor.moviehub.presentation.persondetails;

import com.etiennelawlor.moviehub.data.source.person.models.PersonDetailsWrapper;
import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.source.person.PersonDataSourceContract;
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

public class PersonDetailsPresenter implements PersonDetailsUiContract.Presenter {

    // region Member Variables
    private final PersonDetailsUiContract.View personDetailsView;
    private final PersonDataSourceContract.Repository personRepository;
    private final SchedulerTransformer<PersonDetailsWrapper> schedulerTransformer;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion

    // region Constructors

    public PersonDetailsPresenter(PersonDetailsUiContract.View personDetailsView, PersonDataSourceContract.Repository personRepository, SchedulerTransformer<PersonDetailsWrapper> schedulerTransformer) {
        this.personDetailsView = personDetailsView;
        this.personRepository = personRepository;
        this.schedulerTransformer = schedulerTransformer;
    }

    // endregion

    // region PersonDetailsUiContract.Presenter Methods

    @Override
    public void onDestroyView() {
        if(compositeSubscription != null && compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    @Override
    public void onLoadPersonDetails(int personId) {
        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment(); // App is busy until further notice

        Subscription subscription = personRepository.getPersonDetails(personId)
                .compose(schedulerTransformer)
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement(); // Set app as idle.
                        }
                    }
                })
                .subscribe(new Subscriber<PersonDetailsWrapper>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

                        if(NetworkUtility.isKnownException(throwable)){
//                            moviesView.showErrorFooter();
//                            moviesView.setErrorText("Can't load data.\nCheck your network connection.");
                            personDetailsView.showErrorView();
                        }
                    }

                    @Override
                    public void onNext(PersonDetailsWrapper personDetailsWrapper) {
                        if(personDetailsWrapper != null){
                            personDetailsView.showPersonDetails(personDetailsWrapper);
                        }
                    }
                });
        compositeSubscription.add(subscription);
    }

    @Override
    public void onMovieClick(Movie movie) {
        personDetailsView.openMovieDetails(movie);
    }

    @Override
    public void onTelevisionShowClick(TelevisionShow televisionShow) {
        personDetailsView.openTelevisionShowDetails(televisionShow);
    }

    @Override
    public void onScrollChange(boolean isScrolledPastThreshold) {
        if(isScrolledPastThreshold)
            personDetailsView.showToolbarTitle();
        else
            personDetailsView.hideToolbarTitle();
    }

    // endregion
}
