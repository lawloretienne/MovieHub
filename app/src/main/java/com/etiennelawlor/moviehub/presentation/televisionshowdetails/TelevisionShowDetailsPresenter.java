package com.etiennelawlor.moviehub.presentation.televisionshowdetails;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowDetailsDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.TelevisionShowDetailsDomainContract;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class TelevisionShowDetailsPresenter implements TelevisionShowDetailsPresentationContract.Presenter {

    // region Member Variables
    private final TelevisionShowDetailsPresentationContract.View televisionShowDetailsView;
    private final TelevisionShowDetailsDomainContract.UseCase televisionShowDetailsUseCase;
    private final SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors

    public TelevisionShowDetailsPresenter(TelevisionShowDetailsPresentationContract.View televisionShowDetailsView, TelevisionShowDetailsDomainContract.UseCase televisionShowDetailsUseCase, SchedulerProvider schedulerProvider) {
        this.televisionShowDetailsView = televisionShowDetailsView;
        this.televisionShowDetailsUseCase = televisionShowDetailsUseCase;
        this.schedulerProvider = schedulerProvider;
    }

    // endregion

    // region TelevisionShowDetailsPresentationContract.Presenter Methods

    @Override
    public void onDestroyView() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

    @Override
    public void onLoadTelevisionShowDetails(int televisionShowId) {
        Disposable disposable = televisionShowDetailsUseCase.getTelevisionShowDetails(televisionShowId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableSingleObserver<TelevisionShowDetailsDomainModel>() {
                    @Override
                    public void onSuccess(TelevisionShowDetailsDomainModel televisionShowDetailsDomainModel) {
                        if(televisionShowDetailsDomainModel != null){
                            televisionShowDetailsView.showTelevisionShowDetails(televisionShowDetailsDomainModel);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

                        televisionShowDetailsView.showErrorView();
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onPersonClick(PersonPresentationModel person) {
        televisionShowDetailsView.openPersonDetails(person);
    }

    @Override
    public void onTelevisionShowClick(TelevisionShowPresentationModel televisionShow) {
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
