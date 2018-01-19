package com.etiennelawlor.moviehub.presentation.persondetails;

import com.etiennelawlor.moviehub.domain.models.MovieDomainModel;
import com.etiennelawlor.moviehub.domain.models.PersonDetailsDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.PersonDetailsDomainContract;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class PersonDetailsPresenter implements PersonDetailsPresentationContract.Presenter {

    // region Member Variables
    private final PersonDetailsPresentationContract.View personDetailsView;
    private final PersonDetailsDomainContract.UseCase personDetailsUseCase;
    private final SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors

    public PersonDetailsPresenter(PersonDetailsPresentationContract.View personDetailsView, PersonDetailsDomainContract.UseCase personDetailsUseCase, SchedulerProvider schedulerProvider) {
        this.personDetailsView = personDetailsView;
        this.personDetailsUseCase = personDetailsUseCase;
        this.schedulerProvider = schedulerProvider;
    }

    // endregion

    // region PersonDetailsPresentationContract.Presenter Methods

    @Override
    public void onDestroyView() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

    @Override
    public void onLoadPersonDetails(int personId) {
        Disposable disposable = personDetailsUseCase.getPersonDetails(personId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableSingleObserver<PersonDetailsDomainModel>() {
                    @Override
                    public void onSuccess(PersonDetailsDomainModel personDetailsDomainModel) {
                        if(personDetailsDomainModel != null){
                            personDetailsView.setPersonDetailsDomainModel(personDetailsDomainModel);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

                        personDetailsView.showErrorView();
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onMovieClick(MovieDomainModel movie) {
        personDetailsView.openMovieDetails(movie);
    }

    @Override
    public void onTelevisionShowClick(TelevisionShowPresentationModel televisionShow) {
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
