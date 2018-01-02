package com.etiennelawlor.moviehub.presentation.televisionshowdetails;

import com.etiennelawlor.moviehub.domain.models.PersonDomainModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDetailsDomainModel;
import com.etiennelawlor.moviehub.domain.models.TelevisionShowDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.TelevisionShowDetailsDomainContract;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class TelevisionShowDetailsPresenter implements TelevisionShowDetailsUiContract.Presenter {

    // region Member Variables
    private final TelevisionShowDetailsUiContract.View televisionShowDetailsView;
    private final TelevisionShowDetailsDomainContract.UseCase televisionShowDetailsUseCase;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors

    public TelevisionShowDetailsPresenter(TelevisionShowDetailsUiContract.View televisionShowDetailsView, TelevisionShowDetailsDomainContract.UseCase televisionShowDetailsUseCase) {
        this.televisionShowDetailsView = televisionShowDetailsView;
        this.televisionShowDetailsUseCase = televisionShowDetailsUseCase;
    }

    // endregion

    // region TelevisionShowDetailsUiContract.Presenter Methods

    @Override
    public void onDestroyView() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

    @Override
    public void onLoadTelevisionShowDetails(int televisionShowId) {
        Disposable disposable = televisionShowDetailsUseCase.getTelevisionShowDetails(televisionShowId)
//                .compose(schedulerTransformer)
                .compose(new ProductionSchedulerTransformer<TelevisionShowDetailsDomainModel>())
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

                        if(NetworkUtility.isKnownException(throwable)){
//                            moviesView.showErrorFooter();
//                            moviesView.setErrorText("Can't load data.\nCheck your network connection.");
                            televisionShowDetailsView.showErrorView();
                        }
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onPersonClick(PersonDomainModel person) {
        televisionShowDetailsView.openPersonDetails(person);
    }

    @Override
    public void onTelevisionShowClick(TelevisionShowDomainModel televisionShow) {
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
