package com.etiennelawlor.moviehub.data.source.moviedetails;

import android.content.Context;

import com.etiennelawlor.moviehub.data.model.MovieDetailsWrapper;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public class MovieDetailsLocalDataSource implements MovieDetailsDataSourceContract.LocalDateSource {

    // region Constructors
    public MovieDetailsLocalDataSource(Context context) {
    }
    // endregion

    // region MovieDetailsDataSourceContract.LocalDateSource Methods

    @Override
    public Observable<MovieDetailsWrapper> getMovieDetails(int movieId) {
        //        Use mapper to convert from realm objects to POJOs
        return Observable.empty();
    }

    @Override
    public void saveMovieDetails(MovieDetailsWrapper movieDetailsWrapper) {
//        Use mapper to convert from POJOs to realm objects
    }

    // endregion
}
