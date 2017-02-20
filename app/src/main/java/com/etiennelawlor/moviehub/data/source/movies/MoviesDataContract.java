package com.etiennelawlor.moviehub.data.source.movies;

import com.etiennelawlor.moviehub.data.model.MoviesModel;
import com.etiennelawlor.moviehub.data.remote.response.MoviesEnvelope;

import rx.Observable;

/**
 * Created by etiennelawlor on 2/13/17.
 */

public interface MoviesDataContract {

    interface Repository {
//        Restful VERB is the first part of method name GET , POST , DELETE, PUT
        Observable<MoviesModel> getPopularMovies(int currentPage);
    }

    interface LocalDateSource {

    }

    interface RemoteDateSource {
         Observable<MoviesEnvelope> getPopularMovies(int currentPage);
    }
}
