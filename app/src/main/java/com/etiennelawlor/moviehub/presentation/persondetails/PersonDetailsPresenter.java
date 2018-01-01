package com.etiennelawlor.moviehub.presentation.persondetails;

import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
import com.etiennelawlor.moviehub.domain.PersonDetailsDomainContract;
import com.etiennelawlor.moviehub.domain.models.PersonDetailsDomainModel;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class PersonDetailsPresenter implements PersonDetailsUiContract.Presenter {

    // region Member Variables
    private final PersonDetailsUiContract.View personDetailsView;
    private final PersonDetailsDomainContract.UseCase personDetailsUseCase;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors

    public PersonDetailsPresenter(PersonDetailsUiContract.View personDetailsView, PersonDetailsDomainContract.UseCase personDetailsUseCase) {
        this.personDetailsView = personDetailsView;
        this.personDetailsUseCase = personDetailsUseCase;
    }

    // endregion

    // region PersonDetailsUiContract.Presenter Methods

    @Override
    public void onDestroyView() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

    @Override
    public void onLoadPersonDetails(int personId) {
        Disposable disposable = personDetailsUseCase.getPersonDetails(personId)
//                .compose(schedulerTransformer)
                .compose(new ProductionSchedulerTransformer<PersonDetailsDomainModel>())
                .subscribeWith(new DisposableSingleObserver<PersonDetailsDomainModel>() {
                    @Override
                    public void onSuccess(PersonDetailsDomainModel personDetailsDomainModel) {
                        if(personDetailsDomainModel != null){
                            personDetailsView.showPersonDetails(personDetailsDomainModel);
                        }
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
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onMovieClick(MovieDataModel movie) {
        personDetailsView.openMovieDetails(movie);
    }

    @Override
    public void onTelevisionShowClick(TelevisionShowDataModel televisionShow) {
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
