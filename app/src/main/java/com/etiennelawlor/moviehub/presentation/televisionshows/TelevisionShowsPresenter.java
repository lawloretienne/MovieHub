package com.etiennelawlor.moviehub.presentation.televisionshows;

import com.etiennelawlor.moviehub.data.network.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.repositories.tv.models.TelevisionShowsPage;
import com.etiennelawlor.moviehub.domain.TelevisionShowsDomainContract;
import com.etiennelawlor.moviehub.util.NetworkUtility;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;
import rx.Subscriber;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class TelevisionShowsPresenter implements TelevisionShowsUiContract.Presenter {

    // region Member Variables
    private final TelevisionShowsUiContract.View televisionShowsView;
    private final TelevisionShowsDomainContract.UseCase televisionShowsUseCase;
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
        televisionShowsUseCase.clearSubscriptions();
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

        televisionShowsUseCase.getPopularTelevisionShows(currentPage, new DisposableSingleObserver<TelevisionShowsPage>() {
            @Override
            public void onSuccess(TelevisionShowsPage televisionShowsPage) {
                if(televisionShowsPage != null){
                    List<TelevisionShow> televisionShows = televisionShowsPage.getTelevisionShows();
                    int currentPage = televisionShowsPage.getPageNumber();
                    boolean isLastPage = televisionShowsPage.isLastPage();
                    boolean hasTelevisionShows = televisionShowsPage.hasTelevisionShows();
                    if(currentPage == 1){
                        televisionShowsView.hideLoadingView();

                        if(hasTelevisionShows){
                            televisionShowsView.addHeader();
                            televisionShowsView.addTelevisionShowsToAdapter(televisionShows);

                            if(!isLastPage)
                                televisionShowsView.addFooter();
                        } else {
                            televisionShowsView.showEmptyView();
                        }
                    } else {
                        televisionShowsView.removeFooter();

                        if(hasTelevisionShows){
                            televisionShowsView.addTelevisionShowsToAdapter(televisionShows);

                            if(!isLastPage)
                                televisionShowsView.addFooter();
                        }
                    }

                    televisionShowsView.setTelevisionShowsPage(televisionShowsPage);
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
    }

    @Override
    public void onTelevisionShowClick(TelevisionShow televisionShow) {
        televisionShowsView.openTelevisionShowDetails(televisionShow);
    }

    @Override
    public void onScrollToEndOfList() {
        televisionShowsView.loadMoreItems();
    }

    // endregion
}
