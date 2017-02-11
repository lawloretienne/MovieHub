package com.etiennelawlor.moviehub.data.repository;

import com.etiennelawlor.moviehub.data.remote.MovieHubService;

import io.realm.Realm;
import retrofit2.Call;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public class MoviesLocalRepository implements MoviesRepository {

    private Realm realm;

    // region Constructors
    public MoviesLocalRepository(Realm realm){
        this.realm = realm;
    }
    // endregion

    @Override
    public Call getPopularMovies(int currentPage) {
        return null;
//        return realm.getPopularMovies(currentPage);
    }
}
