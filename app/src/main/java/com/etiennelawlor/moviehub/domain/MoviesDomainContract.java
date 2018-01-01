package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.domain.models.MoviesDomainModel;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface MoviesDomainContract {

    interface UseCase {
        Single<MoviesDomainModel> getPopularMovies(int currentPage);
    }
}
