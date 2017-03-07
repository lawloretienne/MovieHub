package com.etiennelawlor.moviehub.ui.televisionshows;

import com.etiennelawlor.moviehub.data.model.TelevisionShowsModel;
import com.etiennelawlor.moviehub.data.remote.response.TelevisionShow;
import com.etiennelawlor.moviehub.data.source.televisionshows.TelevisionShowsDataSourceContract;
import com.etiennelawlor.moviehub.util.EspressoIdlingResource;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.SchedulerTransformer;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class TelevisionShowsPresenter implements TelevisionShowsUiContract.Presenter {

    // region Member Variables
    private final TelevisionShowsUiContract.View televisionShowsView;
    private final TelevisionShowsDataSourceContract.Repository televisionShowsRepository;
    private final SchedulerTransformer<TelevisionShowsModel> schedulerTransformer;
    private CompositeSubscription compositeSubscription = new CompositeSubscription();
    // endregion

    // region Constructors
    public TelevisionShowsPresenter(TelevisionShowsUiContract.View televisionShowsView, TelevisionShowsDataSourceContract.Repository televisionShowsRepository, SchedulerTransformer<TelevisionShowsModel> schedulerTransformer) {
        this.televisionShowsView = televisionShowsView;
        this.televisionShowsRepository = televisionShowsRepository;
        this.schedulerTransformer = schedulerTransformer;
    }
    // endregion

    // region TelevisionShowsUiContract.Presenter Methods

    @Override
    public void onDestroyView() {
        if(compositeSubscription != null && compositeSubscription.hasSubscriptions())
            compositeSubscription.clear();
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

        // The network request might be handled in a different thread so make sure Espresso knows
        // that the app is busy until the response is handled.
        EspressoIdlingResource.increment(); // App is busy until further notice

        Subscription subscription = televisionShowsRepository.getPopularTelevisionShows(currentPage)
                .compose(schedulerTransformer)
                .doOnTerminate(new Action0() {
                    @Override
                    public void call() {
                        if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                            EspressoIdlingResource.decrement(); // Set app as idle.
                        }
                    }
                })
                .subscribe(new Subscriber<TelevisionShowsModel>() {
                    @Override
                    public void onCompleted() {

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

                    @Override
                    public void onNext(TelevisionShowsModel televisionShowsModel) {
                        if(televisionShowsModel != null){
                            int currentPage = televisionShowsModel.getCurrentPage();
                            List<TelevisionShow> televisionShows = televisionShowsModel.getTelevisionShows();
                            boolean isLastPage = televisionShowsModel.isLastPage();
                            boolean hasTelevisionShows = televisionShowsModel.hasTelevisionShows();
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

                        }

                        televisionShowsView.setModel(televisionShowsModel);
                    }
                });
        compositeSubscription.add(subscription);
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
