package com.etiennelawlor.moviehub.data.source.movies;

import android.content.Context;

import com.etiennelawlor.moviehub.data.remote.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.remote.MovieHubService;
import com.etiennelawlor.moviehub.data.remote.ServiceGenerator;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.MoviesEnvelope;
import com.etiennelawlor.moviehub.data.viewmodel.MoviesViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MoviesRemoteDataSource implements MoviesDataSource {

    // region Constants
    private static final int PAGE_SIZE = 20;
    // endregion

    // region Member Variables
    private MovieHubService movieHubService;
    // endregion

    // region Constructors
    public MoviesRemoteDataSource(Context context) {
        movieHubService = ServiceGenerator.createService(
                MovieHubService.class,
                MovieHubService.BASE_URL,
                new AuthorizedNetworkInterceptor(context));
//        MovieHubApplication.getInstance().getApplicationContext();
    }
    // endregion

    // region MoviesDataSource Methods
    @Override
    public Observable getMovies(int currentPage) {
        return movieHubService.getPopularMovies(currentPage)
                .map(new Func1<MoviesEnvelope, MoviesViewModel>() {
                    @Override
                    public MoviesViewModel call(MoviesEnvelope moviesEnvelope) {
                        List<Movie> movies = moviesEnvelope.getMovies();
                        int currentPage = moviesEnvelope.getPage();
                        boolean isLastPage = moviesEnvelope.getMovies().size() < PAGE_SIZE ? true : false;

                        return new MoviesViewModel(movies, currentPage, isLastPage);
                    }
                });
    }
    // endregion
}
