package com.etiennelawlor.moviehub.presentation.persons;

import com.etiennelawlor.moviehub.data.repositories.person.models.PersonsPage;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.repositories.person.PersonDataSourceContract;
import com.etiennelawlor.moviehub.domain.MoviesDomainContract;
import com.etiennelawlor.moviehub.domain.PersonsDomainContract;
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
    private final PersonsDomainContract.UseCase personsUseCase;
    // endregion

    // region Constructors
    public PersonsPresenter(PersonsUiContract.View personsView, PersonsDomainContract.UseCase personsUseCase) {
        this.personsView = personsView;
        this.personsUseCase = personsUseCase;
    }
    // endregion

    // region PersonsUiContract.Presenter Methods
    @Override
    public void onDestroyView() {
        personsUseCase.clearSubscriptions();
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

        personsUseCase.getPopularPersons(currentPage, new Subscriber<PersonsPage>() {
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
