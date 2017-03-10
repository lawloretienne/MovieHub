package com.etiennelawlor.moviehub.data.source.moviedetails;

import com.etiennelawlor.moviehub.data.model.MovieDetailsWrapper;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface MovieDetailsDataSourceContract {

    interface Repository {
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Observable<MovieDetailsWrapper> getMovieDetails(int movieId);
    }

    interface LocalDateSource {
        Observable<MovieDetailsWrapper> getMovieDetails(int movieId);
        void saveMovieDetails(MovieDetailsWrapper movieDetailsWrapper);
    }

    interface RemoteDateSource {
         Observable<MovieDetailsWrapper> getMovieDetails(int movieId);
    }
}
