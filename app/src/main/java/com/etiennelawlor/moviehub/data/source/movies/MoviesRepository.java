package com.etiennelawlor.moviehub.data.source.movies;

import com.etiennelawlor.moviehub.data.model.MoviesModel;
import com.etiennelawlor.moviehub.data.remote.response.Movie;
import com.etiennelawlor.moviehub.data.remote.response.MoviesEnvelope;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MoviesRepository implements MoviesDataContract.Repository {

    // http://blog.danlew.net/2015/06/22/loading-data-from-multiple-sources-with-rxjava/

    // region Constants
    private static final int PAGE_SIZE = 20;
    // endregion

    // region Member Variables
    private MoviesDataContract.LocalDateSource moviesLocalDataSource;
    private MoviesDataContract.RemoteDateSource moviesRemoteDataSource;
    // endregion

    // region Constructors
    // Additionally i need to pass in configRemoteDataSource as
    public MoviesRepository(MoviesDataContract.LocalDateSource moviesLocalDataSource, MoviesDataContract.RemoteDateSource moviesRemoteDataSource) {
        this.moviesLocalDataSource = moviesLocalDataSource;
        this.moviesRemoteDataSource = moviesRemoteDataSource;
    }
    // endregion

    // region MoviesDataContract.Repository Methods
    @Override
    public Observable<MoviesModel> getPopularMovies(int currentPage) {

        return moviesRemoteDataSource.getPopularMovies(currentPage)
            .map(new Func1<MoviesEnvelope, MoviesModel>() {
                @Override
                public MoviesModel call(MoviesEnvelope moviesEnvelope) {
                    List<Movie> movies = moviesEnvelope.getMovies();
                    int currentPage = moviesEnvelope.getPage();
                    boolean isLastPage = moviesEnvelope.getMovies().size() < PAGE_SIZE ? true : false;

                    return new MoviesModel(movies, currentPage, isLastPage);
                }
            }).doOnNext(new Action1<MoviesModel>() {
                    @Override
                    public void call(MoviesModel moviesViewModel) {
                        // todo: update realm
                    }
                });
    }

    // endregion
}
