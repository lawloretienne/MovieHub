package com.etiennelawlor.moviehub.data.source.movies;

import android.content.Context;

import com.etiennelawlor.moviehub.data.model.MoviesPage;
import com.etiennelawlor.moviehub.data.remote.AuthorizedNetworkInterceptor;
import com.etiennelawlor.moviehub.data.remote.MovieHubService;
import com.etiennelawlor.moviehub.data.remote.ServiceGenerator;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.MoviesEnvelope;

import java.util.Calendar;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MoviesRemoteDataSource implements MoviesDataSourceContract.RemoteDateSource {

    // region Constants
    private static final int PAGE_SIZE = 20;
    private static final int SEVEN_DAYS = 7;
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
    public Observable<MoviesPage> getPopularMovies(final int currentPage) {
        return movieHubService.getPopularMovies(currentPage)
                .flatMap(new Func1<MoviesEnvelope, Observable<List<Movie>>>() {
                    @Override
                    public Observable<List<Movie>> call(MoviesEnvelope moviesEnvelope) {
                        return Observable.just(moviesEnvelope.getMovies());
                    }
                })
                .map(new Func1<List<Movie>, MoviesPage>() {
                    @Override
                    public MoviesPage call(List<Movie> movies) {
                        boolean isLastPage = movies.size() < PAGE_SIZE ? true : false;
                        Calendar calendar = Calendar.getInstance();
                        calendar.add(Calendar.DATE, SEVEN_DAYS);
                        return new MoviesPage(movies, currentPage, isLastPage, calendar.getTime() );
                    }
                });
    }
    // endregion
}
