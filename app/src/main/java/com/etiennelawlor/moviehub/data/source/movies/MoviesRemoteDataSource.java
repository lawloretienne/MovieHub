package com.etiennelawlor.moviehub.data.source.movies;

import android.content.Context;

import com.etiennelawlor.moviehub.data.model.MoviesModel;
import com.etiennelawlor.moviehub.data.remote.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.remote.MovieHubService;
import com.etiennelawlor.moviehub.data.remote.ServiceGenerator;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.MoviesEnvelope;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MoviesRemoteDataSource implements MoviesDataSourceContract.RemoteDateSource {

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

    // region MoviesDataSourceContract.RemoteDateSource Methods
    @Override
    public Observable<List<Movie>> getPopularMovies(int currentPage) {
        return movieHubService.getPopularMovies(currentPage)
                .flatMap(new Func1<MoviesEnvelope, Observable<List<Movie>>>() {
                    @Override
                    public Observable<List<Movie>> call(MoviesEnvelope moviesEnvelope) {
                        return Observable.just(moviesEnvelope.getMovies());
                    }
                });
    }
    // endregion
}
