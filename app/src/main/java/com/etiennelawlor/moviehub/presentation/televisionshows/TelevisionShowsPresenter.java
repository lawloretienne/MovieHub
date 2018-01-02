package com.etiennelawlor.moviehub.presentation.televisionshows;

import com.etiennelawlor.moviehub.domain.usecases.TelevisionShowsDomainContract;
import com.etiennelawlor.moviehub.presentation.mappers.TelevisionShowsPresentationModelMapper;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowsPresentationModel;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class TelevisionShowsPresenter implements TelevisionShowsUiContract.Presenter {

    // region Member Variables
    private final TelevisionShowsUiContract.View televisionShowsView;
    private final TelevisionShowsDomainContract.UseCase televisionShowsUseCase;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private TelevisionShowsPresentationModelMapper televisionShowsPresentationModelMapper = new TelevisionShowsPresentationModelMapper();
    // endregion

    // region Constructors
    public TelevisionShowsPresenter(TelevisionShowsUiContract.View televisionShowsView, TelevisionShowsDomainContract.UseCase televisionShowsUseCase) {
        this.televisionShowsView = televisionShowsView;
        this.televisionShowsUseCase = televisionShowsUseCase;
    }
    // endregion

    // region TelevisionShowsUiContract.Presenter Methods

    @Override
    public void onDestroyView() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

    @Override
    public void onLoadPopularTelevisionShows(final int currentPage) {
        if(currentPage == 1){
            televisionShowsView.hideEmptyView();
            televisionShowsView.hideErrorView();
            televisionShowsView.showLoadingView();
        } else{
            televisionShowsView.showLoadingFooter();
        }

        Disposable disposable = televisionShowsUseCase.getPopularTelevisionShows(currentPage)
                .map(televisionShowsDomainModel -> televisionShowsPresentationModelMapper.mapToPresentationModel(televisionShowsDomainModel))
//                .compose(schedulerTransformer)
                .compose(new ProductionSchedulerTransformer<TelevisionShowsPresentationModel>())
                .subscribeWith(new DisposableSingleObserver<TelevisionShowsPresentationModel>() {
                    @Override
                    public void onSuccess(TelevisionShowsPresentationModel televisionShowsPresentationModel) {
                        if(televisionShowsPresentationModel != null){
                            List<TelevisionShowPresentationModel> televisionShowPresentationModels = televisionShowsPresentationModel.getTelevisionShows();
                            int currentPage = televisionShowsPresentationModel.getPageNumber();
                            boolean isLastPage = televisionShowsPresentationModel.isLastPage();
                            boolean hasTelevisionShows = televisionShowsPresentationModel.hasTelevisionShows();
                            if(currentPage == 1){
                                televisionShowsView.hideLoadingView();

                                if(hasTelevisionShows){
                                    televisionShowsView.addHeader();
                                    televisionShowsView.addTelevisionShowsToAdapter(televisionShowPresentationModels);

                                    if(!isLastPage)
                                        televisionShowsView.addFooter();
                                } else {
                                    televisionShowsView.showEmptyView();
                                }
                            } else {
                                televisionShowsView.removeFooter();

                                if(hasTelevisionShows){
                                    televisionShowsView.addTelevisionShowsToAdapter(televisionShowPresentationModels);

                                    if(!isLastPage)
                                        televisionShowsView.addFooter();
                                }
                            }

                            televisionShowsView.setTelevisionShowsPresentationModel(televisionShowsPresentationModel);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

                        if(currentPage == 1){
                            televisionShowsView.hideLoadingView();

                            if (NetworkUtility.isKnownException(throwable)) {
                                televisionShowsView.setErrorText("Can't load data.\nCheck your network connection.");
                                televisionShowsView.showErrorView();
                            }
                        } else {
                            if(NetworkUtility.isKnownException(throwable)){
                                televisionShowsView.showErrorFooter();
                            }
                        }
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onTelevisionShowClick(TelevisionShowPresentationModel televisionShow) {
        televisionShowsView.openTelevisionShowDetails(televisionShow);
    }

    @Override
    public void onScrollToEndOfList() {
        televisionShowsView.loadMoreItems();
    }

    // endregion
}
