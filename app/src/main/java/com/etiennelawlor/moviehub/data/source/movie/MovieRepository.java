package com.etiennelawlor.moviehub.data.source.movie;

import com.etiennelawlor.moviehub.data.source.movie.models.MovieDetailsWrapper;
import com.etiennelawlor.moviehub.data.source.movie.models.MoviesPage;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MovieRepository implements MovieDataSourceContract.Repository {

    // Load data from local and remote
    // http://blog.danlew.net/2015/06/22/loading-data-from-multiple-sources-with-rxjava/
//    https://github.com/millionsun93/HackerNews/blob/bd94c62ac658eb3281879c8018540f6dc2c2ec3d/app/src/main/java/com/innovatube/boilerplate/data/HackerNewsRepositoryImpl.java
//    https://github.com/4ndrik/takestock_android/blob/19038a57675cdc88547e9695a81de9269b01dc4e/app/src/main/java/com/devabit/takestock/data/source/DataRepository.java

    // Uses mapper to go from POJO to RealmObject
    // https://github.com/ihorvitruk/buddysearch/blob/master/library/src/main/java/com/buddysearch/android/library/data/mapper/BaseMapper.java
    // https://github.com/dcampogiani/Qwertee/blob/f71dbc318264bcc05a7f51c8cb8c40e54b53b57e/data/src/main/java/com/danielecampogiani/qwertee/data/local/model/MapperImpl.java

    // region Member Variables
    private MovieDataSourceContract.LocalDateSource movieLocalDataSource;
    private MovieDataSourceContract.RemoteDateSource movieRemoteDataSource;
    // endregion

    // region Constructors
    public MovieRepository(MovieDataSourceContract.LocalDateSource movieLocalDataSource, MovieDataSourceContract.RemoteDateSource movieRemoteDataSource) {
        this.movieLocalDataSource = movieLocalDataSource;
        this.movieRemoteDataSource = movieRemoteDataSource;
    }
    // endregion

    // region MovieDataSourceContract.Repository Methods
    @Override
    public Observable<MoviesPage> getPopularMovies(final int currentPage) {
        Observable<MoviesPage> local = movieLocalDataSource.getPopularMovies(currentPage)
                .filter(moviesPage -> !moviesPage.isExpired());
        Observable<MoviesPage> remote =
                movieRemoteDataSource.getPopularMovies(currentPage)
                        .doOnNext(moviesPage -> movieLocalDataSource.savePopularMovies(moviesPage));

        return Observable.concat(local, remote).first();
    }

    @Override
    public Observable<MovieDetailsWrapper> getMovieDetails(int movieId) {
        Observable<MovieDetailsWrapper> local = movieLocalDataSource.getMovieDetails(movieId);

        Observable<MovieDetailsWrapper> remote =
                movieRemoteDataSource.getMovieDetails(movieId)
                        .doOnNext(movieDetailsWrapper -> movieLocalDataSource.saveMovieDetails(movieDetailsWrapper));

        return Observable.concat(local, remote).first();
    }

//  Create an Observable that emits a particular item
//  Observable.just(List<Movie> movies)
//  Observable.just(MoviesModel movies)

//  Create an Observable that emits no items but terminates normally
//  Observable.empty();

    // endregion
}
