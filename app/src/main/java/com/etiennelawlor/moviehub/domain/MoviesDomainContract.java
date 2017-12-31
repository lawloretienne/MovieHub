package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.models.MoviesDataModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface MoviesDomainContract {

    interface UseCase {
        Single<MoviesDataModel> getPopularMovies(int currentPage);
    }
}
