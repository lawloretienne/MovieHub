package com.etiennelawlor.moviehub.domain;

import com.etiennelawlor.moviehub.data.repositories.movie.models.MoviesPage;

import io.reactivex.Single;

/**
 * Created by etiennelawlor on 6/26/17.
 */

public interface MoviesDomainContract {

    interface UseCase {
        Single<MoviesPage> getPopularMovies(int currentPage);
    }
}
