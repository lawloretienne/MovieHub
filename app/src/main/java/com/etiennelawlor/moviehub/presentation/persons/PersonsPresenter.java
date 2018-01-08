package com.etiennelawlor.moviehub.presentation.persons;

import com.etiennelawlor.moviehub.domain.usecases.PersonsDomainContract;
import com.etiennelawlor.moviehub.presentation.mappers.PersonsPresentationModelMapper;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.PersonsPresentationModel;
import com.etiennelawlor.moviehub.util.NetworkUtility;
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
    private PersonsPresentationModelMapper personsPresentationModelMapper = new PersonsPresentationModelMapper();
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
            personsView.showLoadingFooter();
        }

        Disposable disposable = personsUseCase.getPopularPersons(currentPage)
                .map(personsDomainModel -> personsPresentationModelMapper.mapToPresentationModel(personsDomainModel))
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableSingleObserver<PersonsPresentationModel>() {
                    @Override
                    public void onSuccess(PersonsPresentationModel personsPresentationModel) {
                        if(personsPresentationModel != null){
                            List<PersonPresentationModel> persons = personsPresentationModel.getPersons();
                            int currentPage = personsPresentationModel.getPageNumber();
                            boolean isLastPage = personsPresentationModel.isLastPage();
                            boolean hasMovies = personsPresentationModel.hasPersons();

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

                            personsView.setPersonsPresentationModel(personsPresentationModel);
                        }
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
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onPersonClick(PersonPresentationModel person) {
        personsView.openPersonDetails(person);
    }

    @Override
    public void onScrollToEndOfList() {
        personsView.loadMoreItems();
    }
    // endregion
}
