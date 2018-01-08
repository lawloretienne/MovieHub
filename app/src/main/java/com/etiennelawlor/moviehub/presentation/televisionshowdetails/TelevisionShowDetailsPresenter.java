package com.etiennelawlor.moviehub.presentation.televisionshowdetails;

import com.etiennelawlor.moviehub.domain.usecases.TelevisionShowDetailsDomainContract;
import com.etiennelawlor.moviehub.presentation.mappers.TelevisionShowDetailsPresentationModelMapper;
import com.etiennelawlor.moviehub.presentation.models.PersonPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowDetailsPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;
import com.etiennelawlor.moviehub.util.NetworkUtility;
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
    private TelevisionShowDetailsPresentationModelMapper televisionShowDetailsPresentationModelMapper = new TelevisionShowDetailsPresentationModelMapper();
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
                .map(televisionShowDetailsDomainModel -> televisionShowDetailsPresentationModelMapper.mapToPresentationModel(televisionShowDetailsDomainModel))
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableSingleObserver<TelevisionShowDetailsPresentationModel>() {
                    @Override
                    public void onSuccess(TelevisionShowDetailsPresentationModel televisionShowDetailsPresentationModel) {
                        if(televisionShowDetailsPresentationModel != null){
                            televisionShowDetailsView.showTelevisionShowDetails(televisionShowDetailsPresentationModel);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

                        if(NetworkUtility.isKnownException(throwable)){
                            televisionShowDetailsView.showErrorView();
                        }
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
