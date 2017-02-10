package com.etiennelawlor.moviehub.data.repository;

import retrofit2.Call;

/**
 * Created by etiennelawlor on 2/9/17.
 */

public interface MoviesRepository {

    Call getPopularMovies(int currentPage);
}
