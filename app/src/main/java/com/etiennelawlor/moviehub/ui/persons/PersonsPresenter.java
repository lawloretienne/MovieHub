package com.etiennelawlor.moviehub.ui.persons;

import com.etiennelawlor.moviehub.data.model.PersonsModel;
import com.etiennelawlor.moviehub.data.remote.response.Person;
import com.etiennelawlor.moviehub.data.source.persons.PersonsDataSourceContract;
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
    private final PersonsDataSourceContract.Repository personsRepository;
    private final SchedulerTransformer<PersonsModel> schedulerTransformer;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion

    // region Constructors
    public PersonsPresenter(PersonsUiContract.View personsView, PersonsDataSourceContract.Repository personsRepository, SchedulerTransformer<PersonsModel> schedulerTransformer) {
        this.personsView = personsView;
        this.personsRepository = personsRepository;
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

        Subscription subscription = personsRepository.getPopularPersons(currentPage)
                .compose(schedulerTransformer)
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement(); // Set app as idle.
                        }
                    }
                })
                .subscribe(new Subscriber<PersonsModel>() {
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
                    public void onNext(PersonsModel personsModel) {
                        if(personsModel != null){
                            int currentPage = personsModel.getCurrentPage();
                            List<Person> persons = personsModel.getPersons();
                            boolean isLastPage = personsModel.isLastPage();
                            boolean hasPersons = personsModel.hasPersons();
                            if(currentPage == 1){
                                personsView.hideLoadingView();

                                if(hasPersons){
                                    personsView.addHeader();
                                    personsView.addPersonsToAdapter(persons);

                                    if(!isLastPage)
                                        personsView.addFooter();
                                } else {
                                    personsView.showEmptyView();
                                }
                            } else {
                                personsView.removeFooter();

                                if(hasPersons){
                                    personsView.addPersonsToAdapter(persons);

                                    if(!isLastPage)
                                        personsView.addFooter();
                                }
                            }

                        }

                        personsView.setModel(personsModel);
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
