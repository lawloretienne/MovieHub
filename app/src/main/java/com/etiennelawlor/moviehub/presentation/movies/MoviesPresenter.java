package com.etiennelawlor.moviehub.presentation.movies;

import com.etiennelawlor.moviehub.data.repositories.models.MovieDataModel;
import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;
import com.etiennelawlor.moviehub.domain.MoviesDomainContract;
import com.etiennelawlor.moviehub.util.NetworkUtility;
import com.etiennelawlor.moviehub.util.rxjava.ProductionSchedulerTransformer;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MoviesPresenter implements MoviesUiContract.Presenter {

    // region Member Variables
    private final MoviesUiContract.View moviesView;
    private final MoviesDomainContract.UseCase moviesUseCase;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    // endregion

    // region Constructors
    public MoviesPresenter(MoviesUiContract.View moviesView, MoviesDomainContract.UseCase moviesUseCase) {
        this.moviesView = moviesView;
        this.moviesUseCase = moviesUseCase;
    }
    // endregion

    // region MoviesUiContract.Presenter Methods
    @Override
    public void onDestroyView() {
        if (compositeDisposable != null)
            compositeDisposable.clear();
    }

    @Override
    public void onLoadPopularMovies(final int currentPage) {
        if(currentPage == 1){
            moviesView.hideEmptyView();
            moviesView.hideErrorView();
            moviesView.showLoadingView();
        } else{
            moviesView.showLoadingFooter();
        }

        Disposable disposable = moviesUseCase.getPopularMovies(currentPage)
//                .compose(schedulerTransformer)
                .compose(new ProductionSchedulerTransformer<MoviesDataModel>())
                .subscribeWith(new DisposableSingleObserver<MoviesDataModel>() {
                    @Override
                    public void onSuccess(MoviesDataModel moviesDataModel) {
                        if(moviesDataModel != null){
                            List<MovieDataModel> movieDataModels = moviesDataModel.getMovies();
                            int currentPage = moviesDataModel.getPageNumber();
                            boolean isLastPage = moviesDataModel.isLastPage();
                            boolean hasMovies = moviesDataModel.hasMovies();
                            if(currentPage == 1){
                                moviesView.hideLoadingView();

                                if(hasMovies){
                                    moviesView.addHeader();
                                    moviesView.addMoviesToAdapter(movieDataModels);

                                    if(!isLastPage)
                                        moviesView.addFooter();
                                } else {
                                    moviesView.showEmptyView();
                                }
                            } else {
                                moviesView.removeFooter();

                                if(hasMovies){
                                    moviesView.addMoviesToAdapter(movieDataModels);

                                    if(!isLastPage)
                                        moviesView.addFooter();
                                }
                            }

                            moviesView.setMoviesDataModel(moviesDataModel);
                        }
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();

                        if(currentPage == 1){
                            moviesView.hideLoadingView();

                            if (NetworkUtility.isKnownException(throwable)) {
                                moviesView.setErrorText("Can't load data.\nCheck your network connection.");
                                moviesView.showErrorView();
                            }
                        } else {
                            if(NetworkUtility.isKnownException(throwable)){
                                moviesView.showErrorFooter();
                            }
                        }
                    }
                });

        compositeDisposable.add(disposable);
    }

    @Override
    public void onMovieClick(MovieDataModel movie) {
        moviesView.openMovieDetails(movie);
    }

    @Override
    public void onScrollToEndOfList() {
        moviesView.loadMoreItems();
    }
    // endregion

}
