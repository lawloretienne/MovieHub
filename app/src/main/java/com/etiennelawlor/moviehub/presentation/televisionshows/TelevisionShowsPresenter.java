package com.etiennelawlor.moviehub.presentation.televisionshows;

import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.TelevisionShowsDataModel;
import com.etiennelawlor.moviehub.domain.TelevisionShowsDomainContract;
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
//                .compose(schedulerTransformer)
                .compose(new ProductionSchedulerTransformer<TelevisionShowsDataModel>())
                .subscribeWith(new DisposableSingleObserver<TelevisionShowsDataModel>() {
                    @Override
                    public void onSuccess(TelevisionShowsDataModel televisionShowsDataModel) {
                        if(televisionShowsDataModel != null){
                            List<TelevisionShowDataModel> televisionShowDataModels = televisionShowsDataModel.getTelevisionShows();
                            int currentPage = televisionShowsDataModel.getPageNumber();
                            boolean isLastPage = televisionShowsDataModel.isLastPage();
                            boolean hasTelevisionShows = televisionShowsDataModel.hasTelevisionShows();
                            if(currentPage == 1){
                                televisionShowsView.hideLoadingView();

                                if(hasTelevisionShows){
                                    televisionShowsView.addHeader();
                                    televisionShowsView.addTelevisionShowsToAdapter(televisionShowDataModels);

                                    if(!isLastPage)
                                        televisionShowsView.addFooter();
                                } else {
                                    televisionShowsView.showEmptyView();
                                }
                            } else {
                                televisionShowsView.removeFooter();

                                if(hasTelevisionShows){
                                    televisionShowsView.addTelevisionShowsToAdapter(televisionShowDataModels);

                                    if(!isLastPage)
                                        televisionShowsView.addFooter();
                                }
                            }

                            televisionShowsView.setTelevisionShowsDataModel(televisionShowsDataModel);
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
    public void onTelevisionShowClick(TelevisionShowDataModel televisionShow) {
        televisionShowsView.openTelevisionShowDetails(televisionShow);
    }

    @Override
    public void onScrollToEndOfList() {
        televisionShowsView.loadMoreItems();
    }

    // endregion
}
