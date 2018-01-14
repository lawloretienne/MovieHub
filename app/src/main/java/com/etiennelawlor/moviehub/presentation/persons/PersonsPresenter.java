package com.etiennelawlor.moviehub.presentation.persons;

import com.etiennelawlor.moviehub.domain.models.PersonDomainModel;
import com.etiennelawlor.moviehub.domain.models.PersonsDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.PersonsDomainContract;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class PersonsPresenter implements PersonsPresentationContract.Presenter {

    // region Member Variables
    private final PersonsPresentationContract.View personsView;
    private final PersonsDomainContract.UseCase personsUseCase;
    private final SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors
    public PersonsPresenter(PersonsPresentationContract.View personsView, PersonsDomainContract.UseCase personsUseCase, SchedulerProvider schedulerProvider) {
        this.personsView = personsView;
        this.personsUseCase = personsUseCase;
        this.schedulerProvider = schedulerProvider;
    }
    // endregion

    // region PersonsPresentationContract.Presenter Methods
    @Override
    public void onDestroyView() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

    @Override
    public void onLoadPopularPersons(final int currentPage) {
        if(currentPage == 1){
            personsView.hideEmptyView();
            personsView.hideErrorView();
            personsView.showLoadingView();
        } else{
            personsView.showLoadingFooterView();
        }

        Disposable disposable = personsUseCase.getPopularPersons(currentPage)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableSingleObserver<PersonsDomainModel>() {
                    @Override
                    public void onSuccess(PersonsDomainModel personsDomainModel) {
                        if(personsDomainModel != null){
                            List<PersonDomainModel> persons = personsDomainModel.getPersons();
                            int currentPage = personsDomainModel.getPageNumber();
                            boolean isLastPage = personsDomainModel.isLastPage();
                            boolean hasMovies = personsDomainModel.hasPersons();

                            if(currentPage == 1){
                                personsView.hideLoadingView();

                                if(hasMovies){
                                    personsView.addHeaderView();
                                    personsView.showPersons(persons);

                                    if(!isLastPage)
                                        personsView.addFooterView();
                                } else {
                                    personsView.showEmptyView();
                                }
                            } else {
                                personsView.removeFooterView();

                                if(hasMovies){
                                    personsView.showPersons(persons);

                                    if(!isLastPage)
                                        personsView.addFooterView();
                                }
                            }

                            personsView.setPersonsDomainModel(personsDomainModel);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

                        if(currentPage == 1){
                            personsView.hideLoadingView();

                            personsView.showErrorView();
                        } else {
                            personsView.showErrorFooterView();
                        }
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onPersonClick(PersonPresentationModel person) {
        personsView.openPersonDetails(person);
    }

    @Override
    public void onScrollToEndOfList() {
        personsView.loadMorePersons();
    }
    // endregion
}
