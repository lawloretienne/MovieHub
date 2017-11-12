package com.etiennelawlor.moviehub.presentation.moviedetails;

import com.etiennelawlor.moviehub.data.network.response.Movie;
import com.etiennelawlor.moviehub.data.network.response.Person;
import com.etiennelawlor.moviehub.data.repositories.movie.models.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.domain.MovieDetailsDomainContract;
import com.etiennelawlor.moviehub.util.NetworkUtility;

import rx.Subscriber;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MovieDetailsPresenter implements MovieDetailsUiContract.Presenter {

    // region Member Variables
    private final MovieDetailsUiContract.View movieDetailsView;
    private final MovieDetailsDomainContract.UseCase movieDetailsUseCase;
    // endregion

    // region Constructors
    public MovieDetailsPresenter(MovieDetailsUiContract.View movieDetailsView, MovieDetailsDomainContract.UseCase movieDetailsUseCase) {
        this.movieDetailsView = movieDetailsView;
        this.movieDetailsUseCase = movieDetailsUseCase;
    }
    // endregion

    // region MovieDetailsUiContract.Presenter Methods
    @Override
    public void onDestroyView() {
        movieDetailsUseCase.clearSubscriptions();
    }

    @Override
    public void onLoadMovieDetails(int movieId) {
        movieDetailsUseCase.getMovieDetails(movieId, new Subscriber<MovieDetailsWrapper>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();

                if(NetworkUtility.isKnownException(throwable)){
//                            moviesView.showErrorFooter();
//                            moviesView.setErrorText("Can't load data.\nCheck your network connection.");
                    movieDetailsView.showErrorView();
                }
            }

            @Override
            public void onNext(MovieDetailsWrapper movieDetailsWrapper) {
                if(movieDetailsWrapper != null){
                    movieDetailsView.showMovieDetails(movieDetailsWrapper);
                }
            }
        });
    }

    @Override
    public void onPersonClick(Person person) {
        movieDetailsView.openPersonDetails(person);
    }

    @Override
    public void onMovieClick(Movie movie) {
        movieDetailsView.openMovieDetails(movie);
    }

    @Override
    public void onScrollChange(boolean isScrolledPastThreshold) {
        if(isScrolledPastThreshold)
            movieDetailsView.showToolbarTitle();
        else
            movieDetailsView.hideToolbarTitle();
    }
    // endregion
}
