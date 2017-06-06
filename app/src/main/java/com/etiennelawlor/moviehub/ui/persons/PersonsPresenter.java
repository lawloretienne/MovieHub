package com.etiennelawlor.moviehub.ui.persons;

import com.etiennelawlor.moviehub.data.model.PersonsPage;
import com.etiennelawlor.moviehub.data.remote.response.Person;
import com.etiennelawlor.moviehub.data.source.person.PersonDataSourceContract;
import com.etiennelawlor.moviehub.util.EspressoIdlingResource;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class PersonsPresenter implements PersonsUiContract.Presenter {

    // region Member Variables
    private final PersonsUiContract.View personsView;
    private final PersonDataSourceContract.Repository personRepository;
    private final SchedulerTransformer<PersonsPage> schedulerTransformer;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion

    // region Constructors
    public PersonsPresenter(PersonsUiContract.View personsView, PersonDataSourceContract.Repository personRepository, SchedulerTransformer<PersonsPage> schedulerTransformer) {
        this.personsView = personsView;
        this.personRepository = personRepository;
        this.schedulerTransformer = schedulerTransformer;
    }
    // endregion

    // region PersonsUiContract.Presenter Methods
    @Override
    public void onDestroyView() {
        if(compositeSubscription != null && compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
    }

    @Override
    public void onLoadPopularPersons(final int currentPage) {
        if(currentPage == 1){
            personsView.hideEmptyView();
            personsView.hideErrorView();
            personsView.showLoadingView();
        } else{
            personsView.showLoadingFooter();
        }

        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment(); // App is busy until further notice

        Subscription subscription = personRepository.getPopularPersons(currentPage)
                .compose(schedulerTransformer)
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement(); // Set app as idle.
                        }
                    }
                })
                .subscribe(new Subscriber<PersonsPage>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

                        if(currentPage == 1){
                            personsView.hideLoadingView();

                            if (NetworkUtility.isKnownException(throwable)) {
                                personsView.setErrorText("Can't load data.\nCheck your network connection.");
                                personsView.showErrorView();
                            }
                        } else {
                            if(NetworkUtility.isKnownException(throwable)){
                                personsView.showErrorFooter();
                            }
                        }
                    }

                    @Override
                    public void onNext(PersonsPage personsPage) {
                        if(personsPage != null){
                            List<Person> persons = personsPage.getPersons();
                            int currentPage = personsPage.getPageNumber();
                            boolean isLastPage = personsPage.isLastPage();
                            boolean hasMovies = personsPage.hasPersons();

                            if(currentPage == 1){
                                personsView.hideLoadingView();

                                if(hasMovies){
                                    personsView.addHeader();
                                    personsView.addPersonsToAdapter(persons);

                                    if(!isLastPage)
                                        personsView.addFooter();
                                } else {
                                    personsView.showEmptyView();
                                }
                            } else {
                                personsView.removeFooter();

                                if(hasMovies){
                                    personsView.addPersonsToAdapter(persons);

                                    if(!isLastPage)
                                        personsView.addFooter();
                                }
                            }

                            personsView.setPersonsPage(personsPage);
                        }
                    }
                });
        compositeSubscription.add(subscription);
    }

    @Override
    public void onPersonClick(Person person) {
        personsView.openPersonDetails(person);
    }

    @Override
    public void onScrollToEndOfList() {
        personsView.loadMoreItems();
    }
    // endregion
}
