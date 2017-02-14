package com.etiennelawlor.moviehub.data.source.movies;

import com.etiennelawlor.moviehub.data.remote.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.remote.MovieHubService;
import com.etiennelawlor.moviehub.data.remote.ServiceGenerator;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.repository.*;
import com.etiennelawlor.moviehub.data.repository.MoviesRepository;

import java.util.List;

import retrofit2.Call;
import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MoviesRemoteDataSource implements MoviesDataSource {

    // region Member Variables
    private MovieHubService movieHubService;
    private List<Call> calls;
    // endregion

    public MoviesRemoteDataSource() {
        movieHubService = ServiceGenerator.createService(
                MovieHubService.class,
                MovieHubService.BASE_URL,
                new AuthorizedNetworkInterceptor(getContext()));
    }

//    @Override
//    public void getMovies(int currentPage, MoviesRepository.GetMoviesCallback callback) {
//        movieHubService.getPopularMovies(currentPage, callback);
//    }

    @Override
    public void getMovies(int currentPage, GetMoviesCallback callback) {
        movieHubService.getPopularMovies(currentPage, callback);

        Call getPopularMoviesCall = movieHubService.getPopularMovies(currentPage);
        calls.add(getPopularMoviesCall);
        getPopularMoviesCall.enqueue(getPopularMoviesNextFetchCallback);
    }
}
