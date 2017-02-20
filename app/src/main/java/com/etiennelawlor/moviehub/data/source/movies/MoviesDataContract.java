package com.etiennelawlor.moviehub.data.source.movies;

import com.etiennelawlor.moviehub.data.viewmodel.MoviesViewModel;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface MoviesDataContract {

    interface Repository {
//        Observable<MoviesEnvelope> getMovies(int currentPage);
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Observable<MoviesViewModel> getPopularMovies(int currentPage);
    }

    interface LocalDateSource {

    }

    interface RemoteDateSource {
         Observable<MoviesViewModel> getPopularMovies(int currentPage);
    }


}
