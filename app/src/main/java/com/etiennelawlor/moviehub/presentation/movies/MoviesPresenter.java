package com.etiennelawlor.moviehub.presentation.movies;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesPage;
import com.etiennelawlor.moviehub.domain.MoviesDomainContract;
import com.etiennelawlor.moviehub.util.NetworkUtility;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MoviesPresenter implements MoviesUiContract.Presenter {

    // region Member Variables
    private final MoviesUiContract.View moviesView;
    private final MoviesDomainContract.UseCase moviesUseCase;
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
        moviesUseCase.clearDisposables();
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

        moviesUseCase.getPopularMovies(currentPage, new DisposableSingleObserver<MoviesPage>() {
            @Override
            public void onSuccess(MoviesPage moviesPage) {
                if(moviesPage != null){
                    List<Movie> movies = moviesPage.getMovies();
                    int currentPage = moviesPage.getPageNumber();
                    boolean isLastPage = moviesPage.isLastPage();
                    boolean hasMovies = moviesPage.hasMovies();
                    if(currentPage == 1){
                        moviesView.hideLoadingView();

                        if(hasMovies){
                            moviesView.addHeader();
                            moviesView.addMoviesToAdapter(movies);

                            if(!isLastPage)
                                moviesView.addFooter();
                        } else {
                            moviesView.showEmptyView();
                        }
                    } else {
                        moviesView.removeFooter();

                        if(hasMovies){
                            moviesView.addMoviesToAdapter(movies);

                            if(!isLastPage)
                                moviesView.addFooter();
                        }
                    }

                    moviesView.setMoviesPage(moviesPage);
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
    }

    @Override
    public void onMovieClick(Movie movie) {
        moviesView.openMovieDetails(movie);
    }

    @Override
    public void onScrollToEndOfList() {
        moviesView.loadMoreItems();
    }
    // endregion

}
