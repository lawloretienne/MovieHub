package com.etiennelawlor.moviehub.presentation.persondetails;

import com.etiennelawlor.moviehub.domain.usecases.PersonDetailsDomainContract;
import com.etiennelawlor.moviehub.presentation.mappers.PersonDetailsPresentationModelMapper;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;
import com.etiennelawlor.moviehub.presentation.models.PersonDetailsPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

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
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PersonDetailsPresentationModelMapper personDetailsPresentationModelMapper = new PersonDetailsPresentationModelMapper();
    // endregion

    // region Constructors

    public PersonDetailsPresenter(PersonDetailsPresentationContract.View personDetailsView, PersonDetailsDomainContract.UseCase personDetailsUseCase) {
        this.personDetailsView = personDetailsView;
        this.personDetailsUseCase = personDetailsUseCase;
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
                .map(personDetailsDomainModel -> personDetailsPresentationModelMapper.mapToPresentationModel(personDetailsDomainModel))
//                .compose(schedulerTransformer)
                .compose(new ProductionSchedulerTransformer<PersonDetailsPresentationModel>())
                .subscribeWith(new DisposableSingleObserver<PersonDetailsPresentationModel>() {
                    @Override
                    public void onSuccess(PersonDetailsPresentationModel personDetailsPresentationModel) {
                        if(personDetailsPresentationModel != null){
                            personDetailsView.showPersonDetails(personDetailsPresentationModel);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

                        if(NetworkUtility.isKnownException(throwable)){
                            personDetailsView.showErrorView();
                        }
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onMovieClick(MoviePresentationModel movie) {
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
