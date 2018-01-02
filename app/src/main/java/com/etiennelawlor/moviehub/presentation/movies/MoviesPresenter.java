package com.etiennelawlor.moviehub.presentation.movies;

import com.etiennelawlor.moviehub.domain.usecases.MoviesDomainContract;
import com.etiennelawlor.moviehub.presentation.mappers.MoviesPresentationModelMapper;
import com.etiennelawlor.moviehub.presentation.models.MoviePresentationModel;
import com.etiennelawlor.moviehub.presentation.models.MoviesPresentationModel;
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
    private MoviesPresentationModelMapper moviesPresentationModelMapper = new MoviesPresentationModelMapper();
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
                .map(moviesDomainModel -> moviesPresentationModelMapper.mapToPresentationModel(moviesDomainModel))
//                .compose(schedulerTransformer)
                .compose(new ProductionSchedulerTransformer<MoviesPresentationModel>())
                .subscribeWith(new DisposableSingleObserver<MoviesPresentationModel>() {
                    @Override
                    public void onSuccess(MoviesPresentationModel moviesPresentationModel) {
                        if(moviesPresentationModel != null){
                            List<MoviePresentationModel> moviePresentationModels = moviesPresentationModel.getMovies();
                            int currentPage = moviesPresentationModel.getPageNumber();
                            boolean isLastPage = moviesPresentationModel.isLastPage();
                            boolean hasMovies = moviesPresentationModel.hasMovies();
                            if(currentPage == 1){
                                moviesView.hideLoadingView();

                                if(hasMovies){
                                    moviesView.addHeader();
                                    moviesView.addMoviesToAdapter(moviePresentationModels);

                                    if(!isLastPage)
                                        moviesView.addFooter();
                                } else {
                                    moviesView.showEmptyView();
                                }
                            } else {
                                moviesView.removeFooter();

                                if(hasMovies){
                                    moviesView.addMoviesToAdapter(moviePresentationModels);

                                    if(!isLastPage)
                                        moviesView.addFooter();
                                }
                            }

                            moviesView.setMoviesPresentationModel(moviesPresentationModel);
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
    public void onMovieClick(MoviePresentationModel movie) {
        moviesView.openMovieDetails(movie);
    }

    @Override
    public void onScrollToEndOfList() {
        moviesView.loadMoreItems();
    }
    // endregion

}
