package com.etiennelawlor.moviehub.data.repository;

import com.etiennelawlor.moviehub.data.remote.response.MoviesEnvelope;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface MoviesRepository {

    Call getPopularMovies(int currentPage);
//    Call getPopularMovies(int currentPage, Callback<MoviesEnvelope> callback);

}
