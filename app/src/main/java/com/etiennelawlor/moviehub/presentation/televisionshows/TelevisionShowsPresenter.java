package com.etiennelawlor.moviehub.presentation.televisionshows;

import com.etiennelawlor.moviehub.domain.models.TelevisionShowsDomainModel;
import com.etiennelawlor.moviehub.domain.usecases.TelevisionShowsDomainContract;
import com.etiennelawlor.moviehub.presentation.mappers.TelevisionShowsPresentationModelMapper;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowPresentationModel;
import com.etiennelawlor.moviehub.presentation.models.TelevisionShowsPresentationModel;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerProvider;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class TelevisionShowsPresenter implements TelevisionShowsPresentationContract.Presenter {

    // region Member Variables
    private final TelevisionShowsPresentationContract.View televisionShowsView;
    private final TelevisionShowsDomainContract.UseCase televisionShowsUseCase;
    private final SchedulerProvider schedulerProvider;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private TelevisionShowsPresentationModelMapper televisionShowsPresentationModelMapper = new TelevisionShowsPresentationModelMapper();
    // endregion

    // region Constructors
    public TelevisionShowsPresenter(TelevisionShowsPresentationContract.View televisionShowsView, TelevisionShowsDomainContract.UseCase televisionShowsUseCase, SchedulerProvider schedulerProvider) {
        this.televisionShowsView = televisionShowsView;
        this.televisionShowsUseCase = televisionShowsUseCase;
        this.schedulerProvider = schedulerProvider;
    }
    // endregion

    // region TelevisionShowsPresentationContract.Presenter Methods

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
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableSingleObserver<TelevisionShowsDomainModel>() {
                    @Override
                    public void onSuccess(TelevisionShowsDomainModel televisionShowsDomainModel) {
                        if(televisionShowsDomainModel != null){
                            TelevisionShowsPresentationModel televisionShowsPresentationModel = televisionShowsPresentationModelMapper.mapToPresentationModel(televisionShowsDomainModel);

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
